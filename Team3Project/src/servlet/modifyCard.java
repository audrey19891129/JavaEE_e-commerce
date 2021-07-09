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
 * Servlet implementation class modifyCard
 */
@WebServlet("/modifyCard")
public class modifyCard extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyCard() {
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
			
			Client client = (Client)session.getAttribute("client");
			ArrayList<Card> cards = client.getCards();
			int card_id = Integer.parseInt(request.getParameter("card_id"));
			String holdername = request.getParameter("holdername");
			String expirydate = request.getParameter("expirydate");
			
			for(Card c : cards) {
				if(c.getId() == card_id) {
					
					try {
						
						c.setExpiry(expirydate);
						c.setHoldername(holdername);
						c.modify(dataSource);

						Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
						session.setAttribute("client", newclient); 
						ArrayList<Order> list = newclient.getOrders();
						for(Order order : list) {
							if(order.getStatus().contentEquals("ongoing")) {
								session.setAttribute("cart", order); 
								break;
							}
						}
						response.sendRedirect("AccountCards.jsp");
						break;
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
			}
		}
		else {
			response.sendRedirect("LogIn.jsp");
		}
	}
}
