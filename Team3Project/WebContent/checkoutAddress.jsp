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
			<div class="header">Adresse de livraison</div>
			<form class="content" method="post" action="checkoutToConfirm">
			<% 
			
			int id = 0;
			
			if(session.getAttribute("client") != null){
				
				Client client = (Client)session.getAttribute("client");
				id = client.getId();
				ArrayList<Address> addresses = client.getAddresses();
				if(addresses.size() > 0){
					for (Address address : addresses) 
					{ 
						%>
						<div class="row">
							<div class="right"><%= address.toString() %></div>
							<div class="left"><input type="radio" class="full"  name="address_id" value="<%= address.getId() %>"/></div>
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
						<div class="full"> Il n'y a aucune adresse associée à ce compte.</div>
					</div>
					<%
				}
			}
			
			%>
			</form>
			<div class="header accordion">Utiliser une autre adresse de livraison</div>
			<form id="formAddAddress" method="post" action="checkoutNewAddress" class="content panel">
					<div class="row">
						<div class="left">Numéro civique </div>
						<div class="right">
							<input class="full" id="civicnumber" type="text" name="civicnumber"/>
							<input type="hidden" name="client_id" value="<%= id %>"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Appartement</div>
						<div class="right">
							<input class="full" type="text" id="appartment" name="appartment"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Rue</div>
						<div class="right">
							<input class="full" type="text" id="street" name="street"/>
						</div>
					</div>
					
					<div class="row">
						<div class="left">Ville</div>
						<div class="right">
							<input class="full" type="text" id="city" name="city"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Province</div>
						<div class="right">
							<select class="full" propType="province" id="state" name="province"></select>
						</div>
					</div>
					<div class="row">
						<div class="left">Pays</div>
						<div class="right">
							<select class="full" propType="country" id="country" name="country"></select>
						</div>
					</div>
					<div class="row">
						<div class="left">Code postal</div>
						<div class="right">
							<input class="full" type="text" id="zipcode" name="zipcode"/>
						</div>
					</div>
					<input id="addAddressBtn" type="button" value="Continuer"/>
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
  
  var buttons = document.getElementsByName("cardId");
  if(buttons.length >0){
  	var button = buttons[0];
  	button.checked = true;
  }
  
  populateCountries("country", "state");
  populateStates("country", "state");
  
  var buttons = document.getElementsByName("address_id");
  if(buttons.length >0){
  	var button = buttons[0];
  	button.checked = true;
  }
}
</script>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>