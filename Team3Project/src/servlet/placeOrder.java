package servlet;

import java.io.File;
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
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.XMLFile;
import model.Address;
import model.Client;
import model.Entry;
import model.Gmail;
import model.Order;
import model.Product;

/**
 * Servlet implementation class placeOrder
 */
@WebServlet("/placeOrder")
public class placeOrder extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public placeOrder() {
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

			Client client = (Client)session.getAttribute("client");
			int orderId = Integer.parseInt(request.getParameter("order_id"));
			double total = Double.parseDouble(request.getParameter("total"));
			String today = request.getParameter("today");
			String nextweek = request.getParameter("nextweek");
			
			
			int x = (int)(Math.floor(Math.random() * 1001) + 100);
			int y = (int)(Math.floor(Math.random() * 1000001) + 10000);
			
			int a = (int)(Math.floor(Math.random() * 101) + 10);
			int b = (int)(Math.floor(Math.random() * 1001) + 100);
			int c = (int)(Math.floor(Math.random() * 1001) + 100);
			String transaction = "TRANS" + x + "-" + y;
			String tracking = "T" + a + "-" + b + "-" + c;
			
			
			Order prev = (Order) session.getAttribute("cart");
			prev.setDate(today);
			prev.setDeliverydate(nextweek);
			prev.setTracking(tracking);
			prev.setTransaction(transaction);
			session.setAttribute("cart", prev);
			
			
			
			try {
				ArrayList<Entry> entries = prev.getEntries();
				for(Entry entry : entries) {
					Product pr = entry.getProduct();
					System.out.println(pr.toString());
					String query = "update ayjml.product set inventory=(product.inventory -"+ entry.getQuantity() + ") where pcode =" + pr.getPcode() + ";";
					System.out.println(query);
					pr.modify(dataSource, query);
					
				}

				int addressId = prev.getAddress_id();
				System.out.println(addressId);
				ArrayList<Address> addresses = client.getAddresses();
				for(Address address: addresses) {
					if(address.getId() == addressId) {
						Gmail.sendEmail(client.getEmail(), orderId, total, nextweek, address);
						break;
					}
				}
				String query = "update ayjml.order set date='" + today + "', deliverydate='"+ nextweek + "', tracking='"+tracking+"', transaction='"+transaction+"', totalcost="+total+", status='paid' where id=" + prev.getId();
				prev.modify(dataSource, query);
				XMLFile.refreshXMLFile();
				Order newOrder = new Order(0, client.getId(),"ongoing");
				newOrder.save(dataSource);
				Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
				session.setAttribute("client", newclient); 
				System.out.println(newclient.toString());
				ArrayList<Order> orders = newclient.getOrders();
				for(Order order : orders) {
					if(order.getStatus().contentEquals("ongoing")) {
						session.setAttribute("cart", order); 
					}
				}
				response.sendRedirect("CartPage.jsp?status=sent");
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
				
			} catch (SAXException e) {
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
