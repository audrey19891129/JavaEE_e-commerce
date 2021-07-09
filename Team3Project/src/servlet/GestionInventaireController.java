package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Product;
import model.SQLresultset;

/**
 * Servlet implementation class GestionInventaireController
 */
@WebServlet(name = "GestionInventaireController", urlPatterns = "admin/Gestion")
public class GestionInventaireController extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("action"));
		
		String action = request.getParameter("action");
		switch(action) {
		case "INVENTAIRE" : inventory(request, response); break;
		case "CHANGE" : changeStatus(request, response); break;
		case "EDIT" : editInventory(request, response); break;
		}
	}
	
	public void changeStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWritter;
		try {
			printWritter = response.getWriter();	
			SQLresultset sql = new SQLresultset(dataSource);
			sql.openConnection();
				if(request.getParameter("status").equals("active")) {
					sql.executeUpdate("UPDATE `ayjml`.`product` SET `status` = 'inactive' WHERE (`pcode`='" + request.getParameter("pcode") +"');");
					printWritter.println("<script type=\"text/javascript\">");
					printWritter.println("alert('Produit est rendu [inactive] avec succès');");
					printWritter.println("location='GestionInventaire.jsp';");
					printWritter.println("</script>");
				}else {
					sql.executeUpdate("UPDATE `ayjml`.`product` SET `status` = 'active' WHERE (`pcode`='" + request.getParameter("pcode") +"');");
					printWritter.println("<script type=\"text/javascript\">");
					printWritter.println("alert('Produit est rendu [active] avec succès');");
					printWritter.println("location='GestionInventaire.jsp';");
					printWritter.println("</script>");
				}
		sql.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		}
	}
	
	public void inventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product pr = Product.get_SQLQueryProduct("SELECT * FROM `ayjml`.`product` WHERE (`pcode`='" + request.getParameter("pcode") + "');");
		request.setAttribute("productInv", pr);
		RequestDispatcher distpatcher = request.getRequestDispatcher("Inventaire.jsp");
		distpatcher.forward(request, response);
	}
	
	public void editInventory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter printWritter;
		try {
			printWritter = response.getWriter();
			SQLresultset sql = new SQLresultset(dataSource);
			sql.openConnection();
			sql.executeUpdate("UPDATE `ayjml`.`product` SET `inventory` = '" + request.getParameter("txtInventory") + "' WHERE (`pcode`='" + request.getParameter("txtPcode") + "');");
			printWritter.println("<script type=\"text/javascript\">");
			printWritter.println("alert('Modification de l`inventaire avec succès');");
			printWritter.println("location='GestionInventaire.jsp';");
			printWritter.println("</script>");
			sql.closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
			response.sendRedirect("Error.jsp");
		}
	}

}
