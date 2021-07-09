package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class Address {
	
	private int id, client_id, civicnumber;
	private String country, province, city, street, appartment, zipcode;
	
	public Address(){
		
	}

	public Address(int id, int client_id, String country, String province, String city, int civicnumber, String street,
			String appartment, String zipcode) {
		super();
		this.id = id;
		this.client_id = client_id;
		this.civicnumber = civicnumber;
		this.country = country;
		this.province = province;
		this.city = city;
		this.street = street;
		this.appartment = appartment;
		this.zipcode = zipcode;
	}
	
	public static ArrayList<Address> getAddressListByClientId(int id, DataSource dataSource) throws SQLException{
		
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
        ResultSet addressesClient = sql.executeQuery("select * from ayjml.address where client_id=" + id);
	    ArrayList<Address> ads = new  ArrayList<Address>();
	    try {
	    	while (addressesClient.next()) {
		    	Address add = new Address(
		    			addressesClient.getInt("id"),
		    			addressesClient.getInt("client_id"),
		    			addressesClient.getString("country"),
		    			addressesClient.getString("province"),
		    			addressesClient.getString("city"),
		    			addressesClient.getInt("civicnumber"),
		    			addressesClient.getString("street"),
		    			addressesClient.getString("appartment"),
		    			addressesClient.getString("zipcode"));
		    	ads.add(add);
		    }
		    addressesClient.close();
		    sql.closeConnection();
		    return ads;
	    }catch (SQLException e) {
	    	addressesClient.close();
		    sql.closeConnection();
	    	return ads;
	    }
	}

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

	public int getCivicnumber() {
		return civicnumber;
	}

	public void setCivicnumber(int civicnumber) {
		this.civicnumber = civicnumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAppartment() {
		return appartment;
	}

	public void setAppartment(String appartment) {
		this.appartment = appartment;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return civicnumber + ", app." + appartment + ", " + street + ", " + city + ", " + province + ", " + country + ", " + zipcode.toUpperCase();
	}
	
	public int save(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int id = 0;
		sql.executeUpdate("insert into ayjml.address values(null, " + client_id + ", '" + country + "', '" + province + "', '" + city + "', " + civicnumber + ", '" + street + "', '" + appartment + "', '" + zipcode + "');");
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
