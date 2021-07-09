<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="css/common.css">
 <link rel="stylesheet" href="css/navigator.css">
 <link rel="stylesheet" href="css/tables.css">
  <link rel="stylesheet" href="css/map.css">
  <link rel="stylesheet" href="css/caroussel.css">
 <link rel="stylesheet" type="text/css" href="/jqueryUI/jquery-ui.css">
<title>AYJM - Products</title>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>
<%
Client client = (Client)session.getAttribute("client");
Order cart = (Order)session.getAttribute("cart");

Address address = new Address();
Card card = new Card();
String number = "";
String censored = "";

ArrayList<Address> addresses = client.getAddresses();
ArrayList<Card> cards = client.getCards();

for(Address add : addresses){
	if(add.getId() == cart.getAddress_id())
		address=add;
}

for(Card c : cards){
	if(c.getId() == cart.getCard_id()){
		card=c;
		number=card.getNumber();
		censored="****-****-****-" + number.substring(number.length() - 4);
	}
}

double total = cart.getTotal() + 10.5;

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.setTime(new Date());
c.add(Calendar.DATE, 7);
String today = sdf.format(new Date());
String nextweek = sdf.format(c.getTime());

%>
<div id="container">
	<div class="main">
		<div class="table">
			<div class="header">Confirmation de la commande</div>
			<form class="content" method="post" action="placeOrder">
					<div class="row">
						<div class="left">Mode de paiement</div>
						<div class="right">
							<%= card.getType() + " " + censored %>
						</div>
					</div>
					<div class="row">
						<div class="left">Adresse de Livraison</div>
						<div class="right">
							<%= address.toString() %>
						</div>
					</div>
					<div class="row">
						<div class="left">Frais de livraison fixe</div>
						<div class="right">
							10.50 $
						</div>
					</div>
					<div class="row">
						<div class="left">Sous-total de la facture</div>
						<div class="right">
							<%=  String.format("%.2f", cart.getTotal())  + " $"%>
						</div>
					</div>
					<div class="row">
						<div class="left">Total de la facture</div>
						<div class="right">
							<%=  String.format("%.2f", total) + " $" %>
						</div>
					</div>
					<div class="row">
						<div class="left">Date de livraison prévue</div>
						<div class="right">
							<%=  nextweek %>
							<input type="hidden" name="today" value="<%= today %>"/>
							<input type="hidden" name="nextweek" value="<%= nextweek %>"/>
							<input type="hidden" name="order_id" value="<%= cart.getId() %>"/>
							<input type="hidden" name="total" value="<%= total %>"/>
						</div>
					</div>
					<input type="submit" value="Placer la commande"/>
				</form>
			
		</div>
	</div>
</div>

<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>