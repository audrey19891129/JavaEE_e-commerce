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

import Resources.Service;
import model.Client;
import model.Entry;
import model.Order;
import model.Product;

/**
 * Servlet implementation class removeFromCart
 */
@WebServlet("/removeFromCart")
public class removeFromCart extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;

    public removeFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String product = request.getParameter("product");
		String[] P = product.split(";");
		Product newProd = new Product(Integer.parseInt(P[0]), Integer.parseInt(P[1]), Double.parseDouble(P[2]), Double.parseDouble(P[3]),P[4], P[5], P[6], P[7], P[8], P[9], P[10], "", 0);
		
		HttpSession session = request.getSession();
		
		Service service = new Service();
		 
		Order cart = (Order) session.getAttribute("cart");

		ArrayList<Entry> entries = cart.getEntries();
		ArrayList<Entry> newCart;
		try {
			newCart = service.removeFromOrder(newProd, entries, dataSource);
			cart.setEntries(newCart);
			session.setAttribute("cart", cart);  
			
			if((Client) session.getAttribute("client") != null) {
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

			response.sendRedirect("CartPage.jsp");
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
