package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import controller.XMLFile;

@XmlRootElement(name = "product")

public class Product implements Serializable {
	//static File productsXML = XMLFile.find();
	private static final long serialVersionUID = 1L;
	
	private int pcode, size, inventory;
	private double price, alcohol;
	private String name, description, country, region, producer, image, website, category, status;

	public Product(){}
	
	public Product(int pcode, int size, double price, double alcohol, String name, String description, String country,
			String region, String producer, String image, String website, String category, int inventory) {

		this.pcode = pcode;
		this.size = size;
		this.price = price;
		this.alcohol = alcohol;
		this.name = name;
		this.description = description;
		this.country = country;
		this.region = region;
		this.producer = producer;
		this.image = image;
		this.website = website;
		this.category = category;
		this.inventory = inventory;
	}


	public Product(int pcode, int size, double price, double alcohol, String name, String description, String country,
			String region, String producer, String image, String website, String category, int inventory, String status) {

		this.pcode = pcode;
		this.size = size;
		this.price = price;
		this.alcohol = alcohol;
		this.name = name;
		this.description = description;
		this.country = country;
		this.region = region;
		this.producer = producer;
		this.image = image;
		this.website = website;
		this.category = category;
		this.inventory = inventory;
		this.status = status;
	}
	
	public Product(String name, String category, String description, String country, String region, double alcohol, String producer,
			int size, String image,  int inventory, String website, double price, String status) {

		this.size = size;
		this.price = price;
		this.alcohol = alcohol;
		this.name = name;
		this.description = description;
		this.country = country;
		this.region = region;
		this.producer = producer;
		this.image = image;
		this.website = website;
		this.category = category;
		this.inventory = inventory;
		this.status = status;
	}
	
	public Product(int pcode ,String name, String category, String description, String country, String region, double alcohol, String producer,
			int size, String image,  int inventory, String website, double price, String status) {
		
		this.pcode = pcode;
		this.size = size;
		this.price = price;
		this.alcohol = alcohol;
		this.name = name;
		this.description = description;
		this.country = country;
		this.region = region;
		this.producer = producer;
		this.image = image;
		this.website = website;
		this.category = category;
		this.inventory = inventory;
		this.status = status;
	}

public static ArrayList<Product> get_SQLQueryJEAN_MARTIN(String query){
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con = DriverManager.getConnection(  
				"jdbc:mysql://50.62.149.28:3306/ayjml","administrateur","root2021**");  
				Statement stmt = con.createStatement();  
				ResultSet resultSet = stmt.executeQuery(query);  
				
				while (resultSet.next()) {
					
				    int pcode = resultSet.getInt("pcode");
					int size = resultSet.getInt("size");
					int inventory = resultSet.getInt("inventory");
					double price = resultSet.getDouble("price");
					double alcohol = resultSet.getDouble("alcohol");
					String name = resultSet.getString("name");
					String category = resultSet.getString("category");
					String description = resultSet.getString("description");
					String country = resultSet.getString("country");
					String region = resultSet.getString("region");
					String producer = resultSet.getString("producer");
					String image = resultSet.getString("image");
					String website = resultSet.getString("website");
					String status = resultSet.getString("status");
				    
					list.add(new Product(pcode, size, price, alcohol, name, description, country,
							 region, producer, image, website, category, inventory, status));
				}
				con.close();  
			}
		catch(Exception e){ 
			System.out.println(e);
			}  
		return list;
	}

public static Product get_SQLQueryProduct(String query){
	Product pr = new Product();
	try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con = DriverManager.getConnection(  
			"jdbc:mysql://50.62.149.28:3306/ayjml","administrateur","root2021**");  
			Statement stmt = con.createStatement();  
			ResultSet resultSet = stmt.executeQuery(query);  
			
			while (resultSet.next()) {
				
			    int pcode = resultSet.getInt("pcode");
				int size = resultSet.getInt("size");
				int inventory = resultSet.getInt("inventory");
				double price = resultSet.getDouble("price");
				double alcohol = resultSet.getDouble("alcohol");
				String name = resultSet.getString("name");
				String category = resultSet.getString("category");
				String description = resultSet.getString("description");
				String country = resultSet.getString("country");
				String region = resultSet.getString("region");
				String producer = resultSet.getString("producer");
				String image = resultSet.getString("image");
				String website = resultSet.getString("website");
				String status = resultSet.getString("status");
			    
				pr = new Product(pcode, size, price, alcohol, name, description, country,
						 region, producer, image, website, category, inventory, status);
			}
			con.close();  
		}
	catch(Exception e){ 
		System.out.println(e);
		}  
	return pr;
}


	public static ArrayList<Product> get_SQLQuery(String query){
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con = DriverManager.getConnection(  
				"jdbc:mysql://50.62.149.28:3306/ayjml","administrateur","root2021**");  
				Statement stmt = con.createStatement();  
				ResultSet resultSet = stmt.executeQuery(query);  
				
				while (resultSet.next()) {
					
				    int pcode = resultSet.getInt("pcode");
					int size = resultSet.getInt("size");
					int inventory = resultSet.getInt("inventory");
					double price = resultSet.getDouble("price");
					double alcohol = resultSet.getDouble("alcohol");
					String name = resultSet.getString("name");
					String category = resultSet.getString("category");
					String description = resultSet.getString("description");
					String country = resultSet.getString("country");
					String region = resultSet.getString("region");
					String producer = resultSet.getString("producer");
					String image = resultSet.getString("image");
					String website = resultSet.getString("website");
				    
					list.add(new Product(pcode, size, price, alcohol, name, description, country,
							 region, producer, image, website, category, inventory));
				}
				stmt.close();
				con.close();  
			}
		catch(Exception e){ 
			System.out.println(e);
			}  
		return list;
	}
	
	public static String getProductByName(String name) throws SAXException, IOException, ParserConfigurationException {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				File productsXML = XMLFile.find();
				Document doc = dbBuilder.parse(productsXML);
				
				doc.getDocumentElement().normalize();
				
				NodeList nlist = doc.getElementsByTagName("product");
				
				for(int i = 0; i < nlist.getLength(); i++) {
					Node nNode = nlist.item(i);
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) nNode;

						String pname = element.getElementsByTagName("name").item(0).getTextContent();
						
						if(pname.contentEquals(name)) {
							int pcode = Integer.valueOf(element.getElementsByTagName("pcode").item(0).getTextContent());
							int size = Integer.valueOf(element.getElementsByTagName("size").item(0).getTextContent());
							double price = Double.valueOf(element.getElementsByTagName("price").item(0).getTextContent());
							double alcohol = Double.valueOf(element.getElementsByTagName("alcohol").item(0).getTextContent());
							String description = element.getElementsByTagName("description").item(0).getTextContent();
							String country = element.getElementsByTagName("country").item(0).getTextContent();
							String region = element.getElementsByTagName("region").item(0).getTextContent();
							String producer = element.getElementsByTagName("producer").item(0).getTextContent();
							String image = element.getElementsByTagName("image").item(0).getTextContent();
							String website = element.getElementsByTagName("website").item(0).getTextContent();
							String category = element.getElementsByTagName("category").item(0).getTextContent();
							
							
							Product p = new Product(pcode, size, price, alcohol, pname, description, country,
								 region, producer, image, website, category, 0);
							return p.toString();
						}
					}
				}
					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "0;0;0;0;0;0;0;0;0;0;0;0;0";

			}
			return "0;0;0;0;0;0;0;0;0;0;0;0;0";
	}
	
	public static Product getProductByPcode(int pcode) throws SAXException, IOException, ParserConfigurationException {
		Product pr = new Product();
		try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con = DriverManager.getConnection(  
				"jdbc:mysql://50.62.149.28:3306/ayjml","administrateur","root2021**");  
				Statement stmt = con.createStatement();  
				ResultSet resultSet = stmt.executeQuery("select * from ayjml.product where pcode=" + pcode + ";");  
				
				while (resultSet.next()) {
					
					int size = resultSet.getInt("size");
					int inventory = resultSet.getInt("inventory");
					double price = resultSet.getDouble("price");
					double alcohol = resultSet.getDouble("alcohol");
					String name = resultSet.getString("name");
					String category = resultSet.getString("category");
					String description = resultSet.getString("description");
					String country = resultSet.getString("country");
					String region = resultSet.getString("region");
					String producer = resultSet.getString("producer");
					String image = resultSet.getString("image");
					String website = resultSet.getString("website");
					String status = resultSet.getString("status");
				    
					pr = new Product(pcode, size, price, alcohol, name, description, country,
							 region, producer, image, website, category, inventory, status);
				}
				stmt.close();
				con.close();  
			}
		catch(Exception e){ 
			System.out.println(e); 
			}  
		return pr;
	}

	public static ArrayList<Product> getAllProducts() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		ArrayList<Product> list = new ArrayList<Product>();

		try {
			DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFact.newDocumentBuilder();
			File productsXML = XMLFile.find();

			Document doc = dBuilder.parse(productsXML);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("product");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
	
					int pcode = Integer.valueOf(element.getElementsByTagName("pcode").item(0).getTextContent());
					int size = Integer.valueOf(element.getElementsByTagName("size").item(0).getTextContent());
					int inventory = Integer.valueOf(element.getElementsByTagName("inventory").item(0).getTextContent());
					double price = Double.valueOf(element.getElementsByTagName("price").item(0).getTextContent());
					double alcohol = Double.valueOf(element.getElementsByTagName("alcohol").item(0).getTextContent());
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String description = element.getElementsByTagName("description").item(0).getTextContent();
					String country = element.getElementsByTagName("country").item(0).getTextContent();
					String region = element.getElementsByTagName("region").item(0).getTextContent();
					String producer = element.getElementsByTagName("producer").item(0).getTextContent();
					String image = element.getElementsByTagName("image").item(0).getTextContent();
					String website = element.getElementsByTagName("website").item(0).getTextContent();
					String category = element.getElementsByTagName("category").item(0).getTextContent();
					
					list.add(new Product(pcode, size, price, alcohol, name, description, country,
						 region, producer, image, website, category, inventory));
				}
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getPcode() {
		return pcode;
	}

	public void setPcode(int pcode) {
		this.pcode = pcode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return pcode + ";" + size + ";" + price+ ";" +alcohol+ ";" + name + ";" + description + ";" + country + ";" + region + ";" + producer + ";" + image + ";" + website;
	}
	
	public void save(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		sql.executeUpdate("INSERT INTO `ayjml`.`product` VALUES(null,"
				+ "'" + name + "'," 
				+ "'" + category + "'," 
				+ "'" + description + "'," 
				+ "'" + country + "'," 
				+ "'" + region + "'," 
				+ "'" + alcohol + "'," 
				+ "'" + producer + "'," 
				+ "'" + size + "'," 
				+ "'" + image + "'," 
				+ "'" + inventory + "'," 
				+ "'" + website + "'," 
				+ "'" + price + "'," 
				+ "'" + status + "');");
		sql.closeConnection();
	}
	
	public void edit(DataSource dataSource) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		sql.executeUpdate("UPDATE `ayjml`.`product` SET `name` ='" + name + 
				"', `category` ='" + category + 
				"', `description`='" + description + 
				"', `country` ='" + country + 
				"', `region`= '" + region + 
				"', `alcohol`='" + alcohol + 
				"', `producer`='" + producer + 
				"', `size` ='" + size + 
				"', `image`='" + image + 
				"', `website`='" + website + 
				"', `price`='" + price + 
				"' WHERE (`pcode`='" + pcode + "');" );     
		sql.closeConnection();
	}
	
	public void modify(DataSource dataSource, String query) throws SQLException {
		SQLresultset sql = new SQLresultset(dataSource);
		sql.openConnection();
		int row = sql.executeUpdate(query);
		sql.closeConnection();
	}
}
