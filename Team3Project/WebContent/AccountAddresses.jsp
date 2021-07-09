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
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>AYJM - Products</title>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>

<div id="container">
	<div class="main">
		<div class="table">
			<div class="header">Adresses</div>
			<div class="content">
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
						<div class="row accordion">
							<div class="full"><%= address.toString() %></div>
						</div>
						<form id="formModAddress<%= address.getId() %>" method="post" action="modifyAddress" class="content panel">
					<div class="row">
						<div class="left">Numéro civique </div>
						<div class="right">
							<input class="full" id="civicnumber<%= address.getId() %>" type="text" name="civicnumber" value="<%= address.getCivicnumber() %>"/>
							<input type="hidden" name="client_id" value="<%= id %>"/>
							<input type="hidden" name="address_id" value="<%= address.getId() %>"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Appartement</div>
						<div class="right">
							<input class="full" type="text" id="appartment<%= address.getId() %>" name="appartment" value="<%= address.getAppartment() %>"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Rue</div>
						<div class="right">
							<input class="full" type="text" id="street<%= address.getId() %>" name="street" value="<%= address.getStreet() %>"/>
						</div>
					</div>
					
					<div class="row">
						<div class="left">Ville</div>
						<div class="right">
							<input class="full" type="text" id="city<%= address.getId() %>" name="city" value="<%= address.getCity() %>"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Province</div>
						<div class="right">
							<select class="full" propType="province" id="state<%= address.getId() %>" name="province" value="<%= address.getProvince() %>"></select>
						</div>
					</div>
					<div class="row">
						<div class="left">Pays</div>
						<div class="right">
							<select class="full" propType="country" id="country<%= address.getId() %>" name="country" value="<%= address.getCountry() %>"></select>
						</div>
					</div>
					<div class="row">
						<div class="left">Code postal</div>
						<div class="right">
							<input class="full" type="text" id="zipcode<%= address.getId() %>" name="zipcode" value="<%= address.getZipcode() %>"/>
						</div>
					</div>
					<input class="modAddressBtn" type="button"  xvalue="<%= address.getId() %>" value="Enregistrer les modifications"/>
				</form>
				<script type="text/javascript">
					populateCountries("country<%= address.getId() %>", "state<%= address.getId() %>");
					$("#country<%= address.getId() %>").val("<%= address.getCountry() %>");
					var index = $("#country<%= address.getId() %>").prop('selectedIndex');
					autoPopulateStates(index, "state<%= address.getId() %>");
					$("#state<%= address.getId() %>").val("<%= address.getProvince() %>");
				</script>
						<%
					}
					
					%>
					
					<div class="header accordion">Ajouter une autre adresse</div>
			<form id="formAccAddAddress" method="post" action="addNewAddress" class="content panel">
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
					<input id="addAccAddressBtn" type="button" value="Enregistrer la nouvelle adresse"/>
				</form>
					
					
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
populateCountries("country", "state");
populateStates("country", "state");

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