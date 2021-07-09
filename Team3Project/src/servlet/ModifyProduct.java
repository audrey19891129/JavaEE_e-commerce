package servlet;

import java.io.IOException;
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
 * Servlet implementation class ModifyProduct
 */
@WebServlet("/ModifyProduct")
public class ModifyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/ayjml")
    static DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			request.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html");
			
			int pcode = Integer.parseInt(request.getParameter("pcode"));
			String nom = request.getParameter("txtName");
			String categories = request.getParameter("txtCategories");
			String description = request.getParameter("txtDescription");
			String pays = request.getParameter("txtPays");
			String province = request.getParameter("txtProvince");
			double alcool = Double.parseDouble(request.getParameter("txtAlcool"));
			String producteur = request.getParameter("txtProducteur");		
			int millilitre = Integer.parseInt(request.getParameter("txtMl"));
			String image = request.getParameter("txtImage");
			String web = request.getParameter("txtSiteWeb");
			double prix = Double.parseDouble(request.getParameter("txtPrix"));		
			
			Product pr = new Product(pcode,nom,categories,description,pays,province,alcool,producteur,millilitre,image,0,web,prix,"");
			pr.edit(dataSource);
			response.sendRedirect("admin/GestionProduit.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("admin/Error.jsp");
			} catch (IOException e) {
				e.printStackTrace();
				response.sendRedirect("admin/Error.jsp");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("admin/Error.jsp");
			}
	}
}
