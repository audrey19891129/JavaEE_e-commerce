package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class Order {

	private int id, client_id, address_id, card_id;
	private String date, status, transaction, tracking, deliverydate;
	private ArrayList<Entry> entries;
	private double total;
	

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	public Order() {
		super();
		this.entries = new ArrayList<Entry>();
	}
	
	public Order(int id, int client_id, String status) {
		super();
		this.id = id;
		this.status = status;
		this.client_id = client_id;
		this.entries = new ArrayList<Entry>();
		
	}
	
	public Order(int id, String status, ArrayList<Entry> entries) {
		super();
		this.id = id;
		this.status = status;
		this.entries = entries;
	}

	public Order(int id, String status, String transaction) {
		super();
		this.id = id;
		this.status = status;
		this.transaction = transaction;
	}
	

public Order(int id, int client_id, int address_id, String status, String transaction, double total, String tracking) {
		super();
		this.id = id;
		this.address_id = address_id;
		this.status = status;
		this.transaction = transaction;
		this.total = total;
		this.tracking = tracking;
	}

public static ArrayList<Order> getOrderListByClientId(int id, DataSource dataSource) throws SQLException, SAXException, IOException, ParserConfigurationException{
		
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
        ResultSet ordersClient = sql.executeQuery("select * from ayjml.order where client_id=" + id);
	    ArrayList<Order> orders = new  ArrayList<Order>();
	    try {
	    	while (ordersClient.next()) {
	    		Order order = new Order();
	    		order.id =  ordersClient.getInt("id");
	    		order.client_id = ordersClient.getInt("client_id");
	    		order.status = ordersClient.getString("status");
	    		
	    		if(!(order.status.contentEquals("ongoing"))) {
	    			order.address_id = ordersClient.getInt("address_id");
	    			order.card_id = ordersClient.getInt("card_id");
	    			order.transaction = ordersClient.getString("transaction");
	    			order.total = ordersClient.getDouble("totalcost");
	    			order.tracking = ordersClient.getString("tracking");
	    			java.sql.Date dbSqlDate = ordersClient.getDate("date");
	    			java.sql.Date dbSqlDeliveryDate = ordersClient.getDate("deliverydate");
	    			order.date = dbSqlDate.toString();	
	    			order.deliverydate = dbSqlDeliveryDate.toString();
	    		}
	    		else {
	    			
	    			try {
	    				order.address_id = ordersClient.getInt("address_id");
	    				order.total = ordersClient.getDouble("totalcost");
	    			}
	    			catch(NullPointerException n) {
	    				order.address_id = 0;
		    			order.total = 0;
	    			}
	    			try {
	    				order.card_id = ordersClient.getInt("card_id");
	    			}
	    			catch(NullPointerException n) {
	    				order.card_id = 0;
	    			}
	    			order.transaction = "";
	    			order.tracking = "";
	    			order.date = "";
	    			order.deliverydate = "";
	    		}
	    		orders.add(order);
		    }
	    	ordersClient.close();
		    sql.closeConnection();
		    
		    if(!(orders.isEmpty())) {
		    	for (Order order : orders) 
		    	order.entries = Entry.getEntryListByOrderId(order.id, dataSource);
		    }
		    return orders;
	    }catch (SQLException e) {
	    	ordersClient.close();
		    sql.closeConnection();
	    	return orders;
	    }
	    catch(NullPointerException n) {
	    	ordersClient.close();
		    sql.closeConnection();
	    	return orders;
	    }
	}

	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	public ArrayList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<Entry> entries) {
		this.entries = entries;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", client_id=" + client_id + ", address_id=" + address_id + ", card_id=" + card_id
				+ ", date=" + date + ", status=" + status + ", transaction=" + transaction + ", tracking=" + tracking
				+ ", deliverydate=" + deliverydate + ", entries=" + entries + ", total=" + total + "]";
	}

	public void save(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate("insert into ayjml.order (id, client_id, status) values(null," + client_id + ", 'ongoing');");
		sql.closeConnection();
	}
	
	public int saveN(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int id = 0;
		sql.executeUpdate("insert into ayjml.order (id, client_id, status) values(null," + client_id + ", 'ongoing');");
		ResultSet set = sql.executeQuery("SELECT LAST_INSERT_ID();");
		 while (set.next()) {
	        	id = set.getInt("LAST_INSERT_ID()");
	        }
		 set.close();
		 sql.closeConnection();
		 return id;
	}
	
	public void modify(DataSource dataSource, String query) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate(query);
		sql.closeConnection();
	}
}
