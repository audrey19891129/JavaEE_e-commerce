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
 <style>
 	.detailpic{
 		max-height:400px;
 		max-width:100%;
 		margin:auto;
 	}
 	.w60{width:60%;}
 </style>
<title>AYJM - Products</title>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>
<%
	String str = request.getParameter("Product");
	String product[] = str.split(";");
	Double alcohol = Double.parseDouble(product[3]);
	Double price = Double.parseDouble(product[2]);
%>
<div id="container">

	<div class="main">
		<%
			if(!(product[0].contentEquals("0"))){
		%>
		<div class="table" style="margin:auto;">
			<div class="header">fiche détaillée du produit</div>
			<div class="content">
				<div class="row">
					<div class=note><img src="<%= product[9] %>" class="detailpic"/></div>
					<div class="w60">
						<div class="row">
							<div class="left">Code du produit</div>
							<div class="right"><%= product[0] %></div>
						</div>
						<div class="row">
							<div class="left">Nom du produit</div>
							<div class="right"><%= product[4] %></div>
						</div>
						<div class="row">
							<div class="left">Description du produit</div>
							<div class="right"><%= product[5] %></div>
						</div>
						<div class="row">
							<div class="left">Provenance</div>
							<div class="right"><%=product[7] + " (" + product[6].toUpperCase() + ")" %></div>
						</div>
						<div class="row">
							<div class="left">Taux d'alcool</div>
							<div class="right"><%=String.format("%.2f", alcohol)%>%</div>
						</div>
						<div class="row">
							<div class="left">Nom du producteur</div>
							<div class="right"><%= product[8] %></div>
						</div>
						<div class="row">
							<div class="left">Site web du producteur</div>
							<div class="right"><a style="color:green;" href="<%= product[10]%>"><%= product[8] %></a></div>
						</div>
						<div class="row">
							<div class="left">Taille</div>
							<div class="right"><%= product[1] + "ml"%></div>
						</div>
						<div class="row">
							<div class="left">Prix</div>
							<div class="right"><%=String.format("%.2f", price)%> $</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
			}
			else{
				%>
					<h2>Désolé, aucun produit ne correspond à votre recherche.</h2>
				<% 
			}
		%>
		
	</div>

</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>

