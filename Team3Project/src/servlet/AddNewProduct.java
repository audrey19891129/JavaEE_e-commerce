package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Product;

/**
 * Servlet implementation class AddNewProduct
 */
@WebServlet("/AddNewProduct")
public class AddNewProduct extends HttpServlet {
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProduct() {
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
		try {
			PrintWriter printWritter = response.getWriter();
			request.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html");
			
			String nom = request.getParameter("txtName");
			String categories = request.getParameter("txtCategories");
			String description = request.getParameter("txtDescription");
			String pays = request.getParameter("txtPays");
			String province = request.getParameter("txtProvince");
			double alcool = Double.parseDouble(request.getParameter("txtAlcool"));
			String producteur = request.getParameter("txtProducteur");		
			int millilitre = Integer.parseInt(request.getParameter("txtMl"));
			String image = request.getParameter("txtImage");
			int inventaire = 0;
			String web = request.getParameter("txtSiteWeb");
			double prix = Double.parseDouble(request.getParameter("txtPrix"));
			String status = "active";
			
			Product pr = new Product(nom,categories,description,pays,province,alcool,producteur,millilitre,image,inventaire,web,prix,status);
			pr.save(dataSource);
			printWritter.println("<script type=\"text/javascript\">");
			printWritter.println("alert('Le nouveau Produit est ajouté avec [ succès ] Veuillez ajouter de l`inventaire a ce nouveau produit dans la section [ Gestion d`inventaire ]');");
			printWritter.println("location='admin/GestionProduit.jsp';");
			printWritter.println("</script>");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("admin/Error.jsp");
		}catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("admin/Error.jsp");
		}
	}

}
