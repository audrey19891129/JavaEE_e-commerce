package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.XMLFile;
import model.*;


public class Service {
	public Service(){
			
		}

	public boolean authenticateUser(String email, String pswd) {

		if(email.contentEquals("x@gmail.com") & pswd.contentEquals("pass")) {
			return true;
		}
	return false;
	}


public Category category(String c) throws IOException, SAXException, ParserConfigurationException {
	
	Category cat = new Category(c);
	ArrayList<Product> array = new ArrayList<Product>();
	
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

					String category = element.getElementsByTagName("category").item(0).getTextContent();
					
					if(category.contentEquals(c)) {
						int pcode = Integer.valueOf(element.getElementsByTagName("pcode").item(0).getTextContent());
						int size = Integer.valueOf(element.getElementsByTagName("size").item(0).getTextContent());
						double price = Double.valueOf(element.getElementsByTagName("price").item(0).getTextContent());
						double alcohol = Double.valueOf(element.getElementsByTagName("alcohol").item(0).getTextContent());
						String name = element.getElementsByTagName("name").item(0).getTextContent();
						String description = element.getElementsByTagName("description").item(0).getTextContent();
						String country = element.getElementsByTagName("country").item(0).getTextContent();
						String region = element.getElementsByTagName("region").item(0).getTextContent();
						String producer = element.getElementsByTagName("producer").item(0).getTextContent();
						String image = element.getElementsByTagName("image").item(0).getTextContent();
						String website = element.getElementsByTagName("website").item(0).getTextContent();
						
						array.add(new Product(pcode, size, price, alcohol, name, description, country,
							 region, producer, image, website, website, size));
					}
				}
			}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cat.setProducts(array);
		return cat;	
}

public  ArrayList<Entry> addToCart(int Quant, Product newProd, ArrayList<Entry> cart, int order_id, DataSource dataSource) throws SQLException {

		boolean bool = false;
		int index = 0;
		
		for(int i = 0; i < cart.size(); i++) {
			Entry e = cart.get(i);
			
	    	if(e.getProduct().toString().contentEquals(newProd.toString())) {
	    		bool = true;
	    		index = i;
	    	}
	    	System.out.println(e.toString());
	    }
		if(bool == false) {
			Entry e = new Entry(0, newProd, order_id, 1, newProd.getPrice());
			cart.add(e);
			if(order_id != 0) 
			e.save(dataSource);

		}
		else{

			Entry e = cart.get(index);
			cart.remove(index);
			int quant =  within0100(e.getQuantity() + Quant);
			e.setQuantity(quant);
			cart.add(e);
			if(e.getId() != 0)
			e.modify(dataSource);
		}
	
	return cart;
}
	
	
	
public  ArrayList<Entry> modifyOrder(int Quant, Product newProd, ArrayList<Entry> cart, DataSource dataSource) throws SQLException {

	
	for(int i = 0; i < cart.size(); i++) {
		Entry e = cart.get(i);
		
    	if(e.getProduct().toString().contentEquals(newProd.toString())) {
			
    		cart.remove(i);
    		e.setQuantity(within0100(Quant));
			cart.add(e);
			if(e.getId() != 0)
				e.modify(dataSource);
    	}
    }

return cart;
}
	
public  ArrayList<Entry> removeFromOrder(Product newProd, ArrayList<Entry> cart, DataSource dataSource) throws SQLException {

		for(int i = 0; i < cart.size(); i++) {
			Entry e = cart.get(i);
			
	    	if(e.getProduct().toString().contentEquals(newProd.toString())) {
	    		cart.remove(i);
		    	if(e.getId() != 0)
					e.delete(dataSource);
	    	}
	    }
return cart;
}
	
public  ArrayList<Entry> emptyCart(ArrayList<Entry> cart, DataSource dataSource) throws SQLException {

	for(Entry e : cart) {
		System.out.println(e.getId());
    	if(e.getId() != 0)
			e.delete(dataSource);
    }
	ArrayList<Entry> entries = cart;
	cart.removeAll(entries);
return cart;
}

public static int within0100(int i) {

	int quant = i;
	if (quant < 0)
		quant = 0;
	else if (quant > 10)
		quant = 10;
	
	return quant;
}
	


	
}
