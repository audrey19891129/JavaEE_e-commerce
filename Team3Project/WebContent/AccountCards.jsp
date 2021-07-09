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
			<div class="header">Cartes</div>
			<div class="content">
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
						<div class="row accordion">
							<div class="left"><%= card.getType().toUpperCase() %></div>
							<div class="right"><%= censored %></div>
						</div>
						<form id="formAccModCard<%= card.getId() %>" method="post" action="modifyCard" class="content panel">
							<div class="row">
								<div class="left">Nom du détenteur</div>
								<div class="right">
									<input class="full" type="text" id="holdername<%= card.getId() %>" name="holdername" value="<%= card.getHoldername() %>"/>
									<input type="hidden" name="card_id" value="<%= card.getId() %>"/>
								</div>
							</div>
							
							<div class="row">
								<div class="left">Date d'expiration</div>
								<div class="right">
									<input class="full" type="text" id="expirydate<%= card.getId() %>" name="expirydate" value="<%= card.getExpiry() %>"/>
								</div>
							</div>
							<input class="modAccCardBtn" type="button" xvalue="<%= card.getId() %>" value="Enregistrer les modifications"/>
						</form>
						<%
					}
					
					%>
					<div class="header accordion">Ajouter une autre carte</div>
			<form id="formAccAddCard" method="post" action="addNewCard" class="content panel">
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
					<input id="addAccCardBtn" type="button" value="Enregistrer la nouvelle carte"/>
				</form>
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
			else{
				%>
				<div class="row">
					<div class="full">Vous devez être connecté(e) pour voir ces informations.</div>
				</div>
				<%
			}
			%>
			</div>
			
			
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

</script>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>