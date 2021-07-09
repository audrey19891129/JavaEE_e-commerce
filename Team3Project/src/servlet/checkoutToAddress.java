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

import model.Client;
import model.Order;

/**
 * Servlet implementation class checkoutToAddress
 */
@WebServlet("/checkoutToAddress")
public class checkoutToAddress extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkoutToAddress() {
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

			int cardId = Integer.parseInt(request.getParameter("cardId"));
			
			HttpSession session = request.getSession();
			Order prev = (Order) session.getAttribute("cart");
			double total = prev.getTotal();
			prev.setCard_id(cardId);
			session.setAttribute("cart", prev);
			String query = "update ayjml.order set card_id=" + cardId + ", totalcost="+ total + " where id=" + prev.getId();
			try {
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
				response.sendRedirect("checkoutAddress.jsp");
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
