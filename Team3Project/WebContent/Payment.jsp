<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.text.DecimalFormat" %>
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

<div id="container">
	<div class="main">
		<div class="table">
			<div class="header">Mode paiement</div>
			<form class="content" method="post" action="checkoutToAddress">
			<% 
			
			int id = 0;
			
			if(session.getAttribute("client") != null){
				
				Client client = (Client)session.getAttribute("client");
				id = client.getId();
				ArrayList<Card> cards = client.getCards();
				if(cards.size() > 0){
					for (Card card : cards) 
					{ 
						String number = card.getNumber();
						String censored = "****-****-****-" + number.substring(number.length() - 4);
						%>

						<div class="row">
							<div class="right"><%= card.getType() + " " + censored %></div>
							<div class="left"><input type="radio" class="full" name="cardId" value="<%= card.getId() %>"/></div>
						</div>
						<%
					}
				%>
				<input id="btnProceed" type="submit" value="Continuer"/>
				<%
				}
				else{
					%>
					<div class="row">
						<div class="full"> Il n'y a aucune carte associée à ce compte.</div>
					</div>
					<%
				}
			}
			
			%>
			</form>
			<div class="header accordion">Utiliser un autre mode de paiement</div>
			<form id="formAddCard" method="post" action="checkoutNewCard" class="content panel">
					<div class="row">
						<div class="left">Numéro de la carte : </div>
						<div class="right">
							<input class="full" id="cardnumber" type="text" name="cardnumber"/>
							<input type="hidden" name="client_id" value="<%= id %>"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Numéro de sécurité</div>
						<div class="right">
							<input class="full" type="text" id="securitycode" name="securitycode"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Nom du détenteur</div>
						<div class="right">
							<input class="full" type="text" id="holdername" name="holdername"/>
						</div>
					</div>
					
					<div class="row">
						<div class="left">Date d'expiration</div>
						<div class="right">
							<input class="full" type="text" id="expirydate" name="expirydate"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Type</div>
						<div class="right">
							<select class="full" name="type">
								<option value="Visa">Visa</option>
								<option value="Mastercard">Mastercard</option>
							</select>
						</div>
					</div>
					<input id="addCardBtn" type="button" value="Continuer"/>
				</form>
			
		</div>
	</div>
</div>
<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}

var buttons = document.getElementsByName("cardId");
if(buttons.length >0){
	var button = buttons[0];
	button.checked = true;
}
</script>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>