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

@WebServlet("/Register")
public class Register extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		
			String name = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String birthday = request.getParameter("birthday");

			String country = request.getParameter("country");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			int civicnumber = Integer.parseInt(request.getParameter("civicnumber"));
			String street = request.getParameter("street");
			String appartment = request.getParameter("appartment");
			String zipcode = request.getParameter("zipcode");

			Client Nclient = new Client(0, email, password, name, lastname, birthday);
			try {
				if(!(Nclient.exists(email, dataSource))) {
				Integer Clientid = Nclient.save(dataSource);
				
					if(Clientid != null || Clientid != 0) {
						Address address  = new Address(0, Clientid, country, province, city, civicnumber, street, appartment, zipcode);
						int AddId = address.save(dataSource);
							if(AddId !=0) {
								response.sendRedirect("LogIn.jsp?status=registered");
							}
							else {
								response.sendRedirect("Error.jsp");
							}
					}
				}
				else {
					response.sendRedirect("Register.jsp?status=registeringfail");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("Error.jsp");
			}
	}
}
