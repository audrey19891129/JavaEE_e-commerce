package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Product;

public class XMLFile {

	static File xml, xmlCompare;
	
	public static boolean fileExists() {
		xml = new File("..\\..\\..\\Downloads\\products.xml");
		if(xml.exists()){return true;}
		return false;
	}
	
	public static File find() {
		xmlCompare = new File("..\\..\\..\\Downloads\\products.xml");
		if(xmlCompare.exists()){
			xml = xmlCompare;
		}
		return xml;
	}
	
	public static void DeleteFile(File file) {
	    if (file.delete()) { 
	      System.out.println("Deleted the file: " + file.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    } 
	}
	
	public static void refreshXMLFile() throws ParserConfigurationException, TransformerException {
		boolean exists = XMLFile.fileExists();
		File productsXML;
		if(exists) {
			productsXML= XMLFile.find();
			XMLFile.DeleteFile(productsXML);
		}
		ArrayList<Product> pList = Product.get_SQLQuery("select * from ayjml.products");
		XMLFile.rebuild(pList);
	}
	
	public static void rebuild(ArrayList<Product> products) throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElement("catalogue");
		doc.appendChild(root);
		
		for(Product pr : products) {
			Element product = doc.createElement("product");
			root.appendChild(product);
			
			Element pcode = doc.createElement("pcode");
			pcode.appendChild(doc.createTextNode(String.valueOf(pr.getPcode())));
			product.appendChild(pcode);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(pr.getName()));
			product.appendChild(name);
			
			Element category = doc.createElement("category");
			category.appendChild(doc.createTextNode(pr.getCategory()));
			product.appendChild(category);
			
			Element description = doc.createElement("description");
			description.appendChild(doc.createTextNode(pr.getDescription()));
			product.appendChild(description);
			
			Element country = doc.createElement("country");
			country.appendChild(doc.createTextNode(pr.getCountry()));
			product.appendChild(country);
			
			Element region = doc.createElement("region");
			region.appendChild(doc.createTextNode(pr.getRegion()));
			product.appendChild(region);
			
			Element alcohol = doc.createElement("alcohol");
			alcohol.appendChild(doc.createTextNode(String.valueOf(pr.getAlcohol())));
			product.appendChild(alcohol);
			
			Element producer = doc.createElement("producer");
			producer.appendChild(doc.createTextNode(pr.getProducer()));
			product.appendChild(producer);
			
			Element size = doc.createElement("size");
			size.appendChild(doc.createTextNode(String.valueOf(pr.getSize())));
			product.appendChild(size);
			
			Element image = doc.createElement("image");
			image.appendChild(doc.createTextNode(pr.getImage()));
			product.appendChild(image);
			
			Element inventory = doc.createElement("inventory");
			inventory.appendChild(doc.createTextNode(String.valueOf(pr.getInventory())));
			product.appendChild(inventory);
			
			Element website = doc.createElement("website");
			website.appendChild(doc.createTextNode(pr.getWebsite()));
			product.appendChild(website);
			
			Element price = doc.createElement("price");
			price.appendChild(doc.createTextNode(String.valueOf(pr.getPrice())));
			product.appendChild(price);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		if(XMLFile.fileExists()) {
			StreamResult result = new StreamResult((XMLFile.find()));
			transformer.transform(source, result);
		}
		else {
			StreamResult result = new StreamResult(new File("..\\..\\..\\Downloads\\products.xml"));
			transformer.transform(source, result);
		}

		System.out.println("fichier enregistré avec succès => products.xml <=");
		
	}
}
