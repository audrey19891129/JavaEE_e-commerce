package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Entry {

	private int id, orderid, quantity;
	private Product product;
	private double price;
	
	public Entry() {}

	public Entry(int id, Product product, int orderid, int quantity, double price) {
		super();
		this.id = id;
		this.product = product;
		this.orderid = orderid;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Entry(int id, int orderid, int quantity, double price) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.quantity = quantity;
		this.price = price;
	}
	
public static ArrayList<Entry> getEntryListByOrderId(int id, DataSource dataSource) throws SQLException, SAXException, IOException, ParserConfigurationException{
		
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
        ResultSet entriesClient = sql.executeQuery("select * from ayjml.entry where order_id=" + id);
	    ArrayList<Entry> entries = new  ArrayList<Entry>();
	    try {
	    	while (entriesClient.next()) {
		    	Entry entry = new Entry(
		    			entriesClient.getInt("id"),
		    			entriesClient.getInt("order_id"),
		    			entriesClient.getInt("quantity"),
		    			entriesClient.getDouble("price")
		    			);
		    	//
		    	int pcode = entriesClient.getInt("pcode");
		    	entry.product = Product.getProductByPcode(pcode);
		    	entries.add(entry);
		    }
	    	entriesClient.close();
		    sql.closeConnection();
		    return entries;
	    }catch (SQLException e) {
	    	entriesClient.close();
		    sql.closeConnection();
	    	return entries;
	    }
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Entry [id=" + id + ", orderid=" + orderid + ", quantity=" + quantity + ", product=" + product
				+ ", price=" + price + "]";
	}
	
	
	public void save(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate("insert into ayjml.entry values(null, " + this.getProduct().getPcode() + ", " + orderid + ", " + quantity + ", " + price + ");");
		sql.closeConnection();
	}
	
	public void modify(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate("update ayjml.entry set quantity=" + quantity + " where id=" + id + ";");
		sql.closeConnection();
	}
	
	public void delete(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate("delete ayjml.entry from ayjml.entry where id=" + id + ";");
		sql.closeConnection();
	}

}
