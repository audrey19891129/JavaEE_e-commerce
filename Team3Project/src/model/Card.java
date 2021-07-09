package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Card {
	private int id, client_id;
	private String type, number,securitycode, holdername, expiry;
	
	
	
	public Card(int id, int client_id, String securitycode, String type, String number, String holdername, String expiry) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.securitycode = securitycode;
		this.type = type;
		this.number = number;
		this.holdername = holdername;
		this.expiry = expiry;
	}
	
	

	public Card(int id, int client_id, String securitycode, String type, String number, String holdername) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.securitycode = securitycode;
		this.type = type;
		this.number = number;
		this.holdername = holdername;
	}


	public static ArrayList<Card> getCardListByClientId(int id, DataSource dataSource) throws SQLException{
		
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
        ResultSet cardsClient = sql.executeQuery("select * from ayjml.card where client_id=" + id);
	    ArrayList<Card> cards = new  ArrayList<Card>();
    	while (cardsClient.next()) {
	    	Card card = new Card(
	    			cardsClient.getInt("id"),
	    			cardsClient.getInt("client_id"),
	    			cardsClient.getString("securitycode"),
	    			cardsClient.getString("type"),
	    			cardsClient.getString("cardnumber"),
	    			cardsClient.getString("holdername")
	    			);
	    	java.sql.Date dbSqlDate = cardsClient.getDate("expirydate");
		    card.setExpiry(dbSqlDate.toString());				    
	    	cards.add(card);
	    }  
	    cardsClient.close();
	    sql.closeConnection();
	    return cards;
	}

	public Card() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHoldername() {
		return holdername;
	}

	public void setHoldername(String holdername) {
		this.holdername = holdername;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return id + ";" + client_id + ";" + securitycode + ";" + type
				+ ";" + number + ";" + holdername + ";" + expiry;
	}
	
	public int save(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int id = 0;
		sql.executeUpdate("insert into ayjml.card values(null, " + client_id + ", '" + type + "', '" + number + "', '" + securitycode + "', '" + holdername + "', '" + expiry + "');");
		ResultSet set = sql.executeQuery("SELECT LAST_INSERT_ID();");
		 while (set.next()) {
	        	id = set.getInt("LAST_INSERT_ID()");
	        }
		 set.close();
		 sql.closeConnection();
		 return id;
	}
	
	public void modify(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate("update ayjml.card set holdername='" + holdername + "', expirydate='" + expiry + "' where id=" + id);
		sql.closeConnection();
	}
	
	
}
