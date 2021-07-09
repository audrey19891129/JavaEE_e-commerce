package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String input = request.getParameter("btn");
			if(session.getAttribute("client") != null) {
				if(input == null){
					response.sendRedirect("ProductsList.jsp");
				}
				else if(input.equals("Oui")){
					session.removeAttribute("client");
					session.removeAttribute("cart");
					response.sendRedirect("ProductsList.jsp");
				}
			}else {
				if(input != null){
					session.removeAttribute("employee");
					response.sendRedirect("index.jsp");
				}				
			}
	}

}
