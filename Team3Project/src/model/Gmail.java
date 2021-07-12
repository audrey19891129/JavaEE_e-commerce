package model;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Gmail {

	 public static void sendEmail(String email, int orderId, double total, String delivDate, Address delivAddress) {

	        final String username = "lachapelle.audrey.1989@gmail.com";
	        final String password = "PASSWORD";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); 
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("lachapelle.audrey.1989@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(email)
	            );
	            message.setSubject("Confirmation de réception de la commande : " + orderId);
	            message.setContent(
	            		 "<table width='700px' style='background-color:#f2f2f2;border:1.5px solid green;font-size:20px;'>" +
	            	                "<thead>" +
	            	                    "<tr style='background-color: green;'>" +
	            	                        "<th colspan='2' height='90px' style='text-align:center; vertical-align:central'><span style='font-size:25pt; color:white; font-weight:bold;'>CONFIRMATION DE COMMANDE</span></th>" +
	            	                    "</tr>" +
	            	                "</thead>" +
	            	                "<tbody>" +
	            	                    "<tr><td width='50%' style='padding-left:15px; padding-top:10px'>Numéro de commande :</td><td>"+orderId+"</td></tr>" +
	            	                    "<tr><td width='50%' style='padding-left:15px; padding-top:10px'>Frais de livraison fixe :</td><td>10.50 $</td></tr>" +
	            	                    "<tr><td width='50%' style='padding-left:15px; padding-top:10px'>Total :</td><td>"+String.format("%.2f",total)+" $</td></tr>" +
	            	                    "<tr><td width='50%' style='padding-left:15px; padding-top:10px'>Date de livraison prévue :</td><td>" + delivDate + "</td></tr>" +
	            	                    "<tr><td width='50%' style='padding-left:15px; padding-top:10px'>Adresse de livraison :</td><td>"+delivAddress.toString()+"</td></tr>" +
	            	                    "<tr><td td colspan='2'></td></tr>" +
	            	                "</tbody>" +
	            	                "<tfoot>" +
	            	                    "<tr><td style='padding-top:20px;text-align:center;'  td colspan='2'>Merci de faire affaire avec AYJML!</td></tr>" +
	            	                    "<tr><td colspan='2' style='text-align:center;'>Pour toute question ou commentaire vous pouvez téléphoner au :<br/> 1-800-123-4567</td></tr>" +
	            	                "</tfoot>" +
	            	            "</table>", "text/html"
	            		);

	            Transport.send(message);

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void sendContactEmail(String email, String name, String content) {

	        final String username = "lachapelle.audrey.1989@gmail.com";
	        final String password = "PASSWORD";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); 
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("lachapelle.audrey.1989@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(email)
	            );
	            message.setSubject("Confirmation de réception de courriel");
	            message.setContent(
	            		 "<table width='700px' style='background-color:#f2f2f2;border:1.5px solid green;font-size:20px;'>" +
	            	                "<thead>" +
	            	                    "<tr style='background-color: green;'>" +
	            	                        "<th height='90px' style='text-align:center; vertical-align:central'><span style='font-size:25pt; color:white; font-weight:bold;'>CONFIRMATION DE RÉCEPTION</span></th>" +
	            	                    "</tr>" +
	            	                "</thead>" +
	            	                "<tbody>" +
	            	                    "<tr><td width='100%' style='padding-left:15px; padding-top:10px'>"+
	            	                    	"Cher/Chère " + name + ",<br/>" +
	            	                    	"Nous avons bien reçu votre question/commentaire/suggestion.<br/>" +
	            	                    	"Merci pour l'intéret que vous portez pour notre entreprise et, surtout, à nos producteurs et artistes locaux!<br/>" +
	            	                    	"Nous communiquerons d'avantage avec vous sous peu.<br/>" +
	            	                    "</td></tr>" +
	            	                    "<tr><td width='100%' style='padding-left:15px; padding-top:10px'>"+
            	                    	"<i>" +
	            	                    	content +
										"</i>" +
            	                    "</td></tr>" +
	            	                    "<tr><td></td></tr>" +
	            	                "</tbody>" +
	            	                "<tfoot>" +
	            	                    "<tr><td style='padding-top:20px;text-align:center;'  td colspan='2'>Merci de faire affaire avec AYJML!</td></tr>" +
	            	                    "<tr><td td colspan='2' style='text-align:center;'>Pour toute question ou commentaire vous pouvez téléphoner au :<br/> 1-800-123-4567</td></tr>" +
	            	                "</tfoot>" +
	            	            "</table>", "text/html"
	            		);

	            Transport.send(message);

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}
