package model;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Client {

	private int id;
	private String email, password, name, lastname, birthday;
	private ArrayList<Address> addresses;
	private ArrayList<Order> orders;
	private ArrayList<Card> cards;
	
	
	public Client() {
		this.orders = new ArrayList<Order>();
		this.addresses = new ArrayList<Address>();
	}
	public Client(int id, String email, String password, String name, String lastname, String birthday) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.birthday = birthday;
		this.addresses = new ArrayList<Address>();
		this.orders = new ArrayList<Order>();
		this.cards = new ArrayList<Card>();
	}
	
	public boolean exists(String email, DataSource dataSource) {
		
		SQLresultset sql = new SQLresultset(dataSource);
    	sql.openConnection();
    	
        try {
        	ResultSet resultSet = sql.executeQuery("select * from ayjml.client where email='" + email + "';");
        	Integer id = null;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
				resultSet.close();
				sql.closeConnection();
				if(id == null)
			    	return false;
				
		} catch (SQLException e) {
			sql.closeConnection();
			return false;
		}  
        return true;
	}

	
	public Client(String email, String password, DataSource dataSource) throws SQLException, SAXException, IOException, ParserConfigurationException {
		SQLresultset sql = new SQLresultset(dataSource);
    	sql.openConnection();

    	
        try {
        	ResultSet resultSet = sql.executeQuery("select * from ayjml.client where email='" + email + "' and ayjml.client.password='" + password + "' ;");
        	
        	Integer id = null;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			    this.id = resultSet.getInt("id");
			    this.name = resultSet.getString("name");
			    this.email = email;
			    this.password = password;
			    this.lastname = resultSet.getString("lastname");
			    java.sql.Date dbSqlDate = resultSet.getDate("birthday");
			    this.birthday = dbSqlDate.toString();		    
			}
				resultSet.close();
				sql.closeConnection();
					
				if(id != null) {
					try {
						ArrayList<Address> addresses = Address.getAddressListByClientId(this.id, dataSource);
						this.addresses = addresses;
					} catch (SQLException e) {
						
						ArrayList<Address> addresses = new ArrayList<Address>();
						this.addresses = addresses;
					}
					
					try {
						ArrayList<Card> cards = Card.getCardListByClientId(this.id, dataSource);
						this.cards = (cards);
					} catch (SQLException e) {
						ArrayList<Card> cards = new ArrayList<Card>();
						this.cards = (cards);
					}
					
					try {
						ArrayList<Order> orders = Order.getOrderListByClientId(this.id, dataSource);
						this.orders = (orders);
					} catch (SQLException e) {
						ArrayList<Order> orders = new ArrayList<Order>();
						this.orders = (orders);
					}
				}
					
		} catch (SQLException e) {
			this.id = 0;
		    this.name = "";
		    this.email = "";
		    this.lastname = "";
		    this.birthday = "";
		    this.addresses = new ArrayList<Address>();
			this.orders = new ArrayList<Order>();
			this.cards = new ArrayList<Card>();
			sql.closeConnection();
		}   
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastname="
				+ lastname + ", birthday=" + birthday + ", addresses=" + addresses + ", orders=" + orders + ", cards="
				+ cards + "]";
	}
	
	
	public int save(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int id = 0;
		sql.executeUpdate("insert into ayjml.client values(null, '" + email + "', '" + password + "', '" + name + "', '" + lastname + "', '" + birthday + "');");
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


