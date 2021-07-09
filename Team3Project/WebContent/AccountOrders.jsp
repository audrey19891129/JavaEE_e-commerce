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
   <style>
   .bill{
   	text-align:right; padding-right:25px;
   }
   </style>
<title>AYJM - Products</title>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>

<div id="container">
	<div class="main">
		<div class="table">
			<div class="header">Commandes</div>
			<div class="content">
			<% 
			
			int id = 0;

			if(session.getAttribute("client") != null){
				
				Client client = (Client)session.getAttribute("client");
				
				ArrayList<Order> orders = client.getOrders();
				if(!(orders.isEmpty())){
					for (Order order : orders) 
					{ 
						if(!(order.getStatus().contentEquals("ongoing"))) {
						%>
						<div class="row accordion" style="background-color:PaleGreen;">
							<div class="full">Commande numéro: <%= order.getId() %></div>
						</div>
						<div class="content panel">
						 <%	
							ArrayList<Entry> entries = order.getEntries();
							for(Entry entry: entries){
								Product product = entry.getProduct();
						 %>
							
							<div class="row">
								<div class="other"><img style="height:60px;" src="<%= product.getImage() %>"/></div>
								<div class="other"><%= product.getName() %></div>									
								<div style="width:40%;"><%= product.getDescription() %></div>							
								<div class="other"><%= entry.getQuantity() %></div>										
								<div class="other bill"><%= String.format("%.2f",entry.getPrice()) %> $</div>										
							</div>
								
							<%	
							}
							%>
							<div class="full" style="display:flex; flex-direction:row;">
									<div class="note"></div>
									<div class="left">Frais de livraison :</div>
									<div class="left bill">10.50 $</div>
									
							</div>
							<div class="full" style="display:flex; flex-direction:row;">
									<div class="note"></div>
									<div class="left">Total de la commande :</div>
									<div class="left bill"><%= String.format("%.2f",order.getTotal()) %> $</div>
									
							</div>
							<div class="full" style="display:flex; flex-direction:row;">
									<div class="note"></div>
									<div class="left">Commandé le :</div>
									<div class="left bill"><%= order.getDate() %></div>
								
							</div>
							<div class="full" style="display:flex; flex-direction:row;">
									<div class="note"></div>
									<div class="left">Date de livraison :</div>
									<div class="left bill"><%= order.getDeliverydate() %></div>
								
							</div>
						</div>
						<%
						}
					}
				
				}
				else{
					%>
					<div class="row">
						<div class="full">Il n'y a aucune commande associée à ce compte.</div>
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