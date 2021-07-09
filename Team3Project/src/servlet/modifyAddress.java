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

import model.Address;
import model.Client;
import model.Order;

/**
 * Servlet implementation class modifyAddress
 */
@WebServlet("/modifyAddress")
public class modifyAddress extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyAddress() {
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
			int address_id = Integer.parseInt(request.getParameter("address_id"));
			String country = request.getParameter("country");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			int civicnumber = Integer.parseInt(request.getParameter("civicnumber"));
			String street = request.getParameter("street");
			String appartment = request.getParameter("appartment");
			String zipcode = request.getParameter("zipcode");
	
			
			Address address  = new Address(address_id, client_id, country, province, city, civicnumber, street, appartment, zipcode);
			try {
				Client client = (Client)session.getAttribute("client");
				String query = "update ayjml.address set country='" + country + "', province='" + province + "', city='" + city + "', civicnumber=" + civicnumber + ", street='" + street + "', appartment='" + appartment + "', zipcode='" + zipcode + "' where id=" + address_id;
				address.modify(dataSource, query);

				Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
				session.setAttribute("client", newclient); 
				ArrayList<Order> list = newclient.getOrders();
				for(Order order : list) {
					if(order.getStatus().contentEquals("ongoing")) {
						session.setAttribute("cart", order); 
						break;
					}
				}
				response.sendRedirect("AccountAddresses.jsp");
				
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
