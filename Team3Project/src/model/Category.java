package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class Category {

	private String name;
	private ArrayList<Product> products;
	
	public Category() {
		this.products = new ArrayList<Product>();
	}
	
	public Category(String name) {
		super();
		this.name = name;
		this.products = new ArrayList<Product>();
	}

	public Category(String name, ArrayList<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public static ArrayList<String> getAllCategories() throws SQLException, ClassNotFoundException{
		ArrayList<String> list = new ArrayList<String>();
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con = DriverManager.getConnection(  
		"jdbc:mysql://50.62.149.28:3306/ayjml","administrateur","root2021**");  
		Statement stmt = con.createStatement();  
		ResultSet query = stmt.executeQuery("SELECT category FROM ayjml.product GROUP BY category");
    	
    	while(query.next()) {
    		String category = query.getString("category");
    		list.add(category);
    	}
    	con.close();
    	stmt.close();
    	query.close();
    	return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	
}
