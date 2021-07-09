package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import model.Address;
import model.Client;
import model.Employee;
import model.Entry;
import model.Gmail;
import model.Order;
import model.Product;

@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		Service service = new Service();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email.contains("@")) {
			try {
				Client client = new Client(email,password,dataSource);
				
				if(client.getId() == 0) { 
					response.sendRedirect("LogIn.jsp?status=authenticationfail");
				}

				else { // <-------------------- CLIENT EXISTS

					if(session.getAttribute("cart") != null) {		// <--------------------------------STUFF IS IN SESSION CART
						Order cart = (Order) session.getAttribute("cart");
						System.out.println("session cart :" + cart.toString());
						Order prevcart = new Order();   // <--------------------------------CLIENT HAS ONGOING ORDER
						//check Ongoing cart exists
						

						boolean boo = false; 		
						ArrayList<Order> list = client.getOrders();
						
						for(Order order : list) {
							if(order.getStatus().contentEquals("ongoing")) {
								//session.setAttribute("cart", order);
								prevcart = order;
								System.out.println("content of prevcart" + prevcart.toString());
								boo = true;
								break;
							}
						}
						if(boo == true) { // <--------------------------------------------------------------- ongoing cart
						
							ArrayList<Entry> Modify = new ArrayList<Entry>();
							ArrayList<Entry> Add = new ArrayList<Entry>();
							ArrayList<Entry> Pentries = prevcart.getEntries(); //<------- stuff in previous cart
							ArrayList<Entry> Nentries = cart.getEntries();		//<------- new stuff in session cart

							// sort out which entries need to be added; if product exists = need modify , if not need add
							
							for(Entry ent1 : Nentries) {
								
								Product p1 = ent1.getProduct();
								boolean exists = false;
								
								for(Entry ent2 : Pentries) {
									
									Product p2 = ent2.getProduct();
									
									if(p1.getPcode() == p2.getPcode()) {
										int quant =  Service.within0100(ent1.getQuantity() + ent2.getQuantity());
										ent1.setQuantity(quant);
										ent1.setId(ent2.getId());
										exists = true;
									}
								}
								if(exists) {
									Modify.add(ent1);
								}
								else if(!(exists)){
									Add.add(ent1);
								}
							}
							
							// Add new Entries to ongoing cart
							
							for(Entry e : Add) {
								e.setOrderid(prevcart.getId());
								e.save(dataSource);
							}
							
							for(Entry e : Modify) {
								e.setOrderid(prevcart.getId());
								System.out.println(e.toString());
								e.modify(dataSource);
							}
							
							//reset session client and session cart
							
							Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
							session.setAttribute("client", newclient); // <------------------- set SESSION CLIENT
							ArrayList<Order> x = newclient.getOrders();
							
							for(Order order : x) {
								if(order.getStatus().contentEquals("ongoing")) {
									session.setAttribute("cart", order);// <------------------- set SESSION CLIENT
									break;
								}
							}
							System.out.println(newclient.toString());
							response.sendRedirect("ProductsList.jsp");
						}
						else if(boo == false) { 				// <---------------------------------------- no ongoing cart
							
							//create ongoing cart
							
							Order NewCart = new Order(0, client.getId(),"ongoing");
							int cartId = NewCart.saveN(dataSource);
							System.out.println("cart id = " + + cartId);
							NewCart.setId(cartId);
							
							System.out.println("saved cart" + NewCart.toString());
							//write each new entry in database 
							
							ArrayList<Entry> entries = cart.getEntries();
							
							for(Entry entry : entries) {
								
								entry.setOrderid(cartId);
								System.out.println("entries in session cart" + entry.toString());
								entry.save(dataSource);
							}
							
							//reset session client and session cart
							
							Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
							session.setAttribute("client", newclient);  // <------------------- set SESSION CLIENT
							ArrayList<Order> x = newclient.getOrders();
							
							for(Order order : x) {
								if(order.getStatus().contentEquals("ongoing")) {
									session.setAttribute("cart", order);// <------------------- set SESSION CLIENT
									break;
								}
							}
							System.out.println(newclient.toString());
							response.sendRedirect("ProductsList.jsp");
						}
					
					}

					//======================PART 2================================
					
					else {	// <----------------------------------------------------------------------------SESSION CART IS EMPTY
						//check Ongoing cart exists
						Order cart = new Order();;
						boolean boo = false; // <------------------- check if ongoing cart
						ArrayList<Order> list = client.getOrders();
						
						for(Order order : list) {
							if(order.getStatus().contentEquals("ongoing")) {
								//session.setAttribute("cart", order);
								cart = order;
								System.out.println(order.toString());
								boo = true;
								break;
							}
						}
						System.out.println("cart = " + cart.toString());
						if(boo == false) { // <------------------- no ongoing cart
							cart = new Order(0, client.getId(),"ongoing");
							int cartId = cart.saveN(dataSource);
							cart.setId(cartId);
						}
						Client newclient = new Client(client.getEmail(),client.getPassword(),dataSource);
						session.setAttribute("client", newclient); // <------------------- set SESSION CLIENT
						System.out.println(newclient.toString());
						session.setAttribute("cart", cart);			 // <------------------- set SESSION CART
						response.sendRedirect("ProductsList.jsp");
					}
				}
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
		}else {
			Employee employee = new Employee(email,password,dataSource);
				if(employee.getId() == 0) { 
					response.sendRedirect("LogIn.jsp?status=authenticationfail");
					}
				else { 
					response.getWriter().println(employee.toString()); 
					session.setAttribute("employee", employee); 
					response.sendRedirect("admin/index.jsp"); // <= il faut un ADMIN page
					System.out.println(employee.toString());// <= Verifier dans la console pour voir le résultat de l'employer obtenue <= <= <=
				}
		}
	}

}
