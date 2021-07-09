package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Card;
import model.Client;
import model.Order;

/**
 * Servlet implementation class addNewCard
 */
@WebServlet("/addNewCard")
public class addNewCard extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("client") != null) {
			
		int client_id = Integer.parseInt(request.getParameter("client_id"));
		String type = request.getParameter("type");
		String cardnumber = request.getParameter("cardnumber");
		String securitycode = request.getParameter("securitycode");
		String holdername = request.getParameter("holdername");
		String expirydate = request.getParameter("expirydate");


		Card card  = new Card(0, client_id, securitycode, type, cardnumber, holdername, expirydate);
		try {
			int cardId = card.save(dataSource);
			
			if(cardId != 0) {
				Order prev = (Order) session.getAttribute("cart");
				prev.setCard_id(cardId);
				session.setAttribute("cart", prev);
				String query = "update ayjml.order set card_id=" + cardId + " where id=" + prev.getId();

				prev.modify(dataSource, query);
				Client client = (Client) session.getAttribute("client");
				Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
				session.setAttribute("client", newclient); 
				ArrayList<Order> list = newclient.getOrders();
				for(Order order : list) {
					if(order.getStatus().contentEquals("ongoing")) {
						session.setAttribute("cart", order); 
					}
				}
			}
			response.sendRedirect("AccountCards.jsp");
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		} catch (SAXException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		}catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		}
	}
	else {
		response.sendRedirect("LogIn.jsp");
	}
	}

}
