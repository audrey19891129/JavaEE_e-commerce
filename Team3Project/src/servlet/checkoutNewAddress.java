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

import model.*;

/**
 * Servlet implementation class checkoutNewAddress
 */
@WebServlet("/checkoutNewAddress")
public class checkoutNewAddress extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkoutNewAddress() {
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
		
		HttpSession session = request.getSession();
		if(session.getAttribute("client") != null) {
		
			int client_id = Integer.parseInt(request.getParameter("client_id"));
			String country = request.getParameter("country");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			int civicnumber = Integer.parseInt(request.getParameter("civicnumber"));
			String street = request.getParameter("street");
			String appartment = request.getParameter("appartment");
			String zipcode = request.getParameter("zipcode");
	
			
			Address address  = new Address(0, client_id, country, province, city, civicnumber, street, appartment, zipcode);
			try {
				int AddId = address.save(dataSource);
				
				if(AddId != 0) {
					
					Order prev = (Order) session.getAttribute("cart");
					prev.setAddress_id(AddId);
					session.setAttribute("cart", prev);
					String query = "update ayjml.order set address_id=" + AddId + " where id=" + prev.getId();

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
				response.sendRedirect("OrderConfirmation.jsp");
				
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
