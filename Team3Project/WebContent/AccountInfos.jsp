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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>


<div id="container">
	<div class="main">
<% 
if(session.getAttribute("client") != null){
	
	Client client = (Client)session.getAttribute("client");
%>
		<div class="table"  style="margin:auto;">
			<div class="header">Informations sur le compte</div>
			<div class="content">
				<div class="row">
					<div class="left">Prénom</div>
					<div class="right">
						<%= client.getName() %>
					</div>
				</div>
				<div class="row">
					<div class="left">Nom</div>
					<div class="right">
						<%= client.getLastname() %>
					</div>
				</div>
				<div class="row">
					<div class="left">Date de naissance</div>
					<div class="right">
						<%= client.getBirthday() %>
					</div>
				</div>
				<div class="row">
					<div class="left">Adresse courriel</div>
					<div class="right">
						<%= client.getEmail() %>
					</div>
				</div>
			</div>
			<div class="full header accordion">Modification du mot de passe</div>
			<form class="content panel" id="formModPassword" action="changePassword" method="post">
				<div class="row">
					<div class="left">Nouveau mot de passe</div>
					<div class="right">
						<input class="full" id="password1" type="password" name="password"/>
					</div>
				</div>
				<div class="row">
					<div class="left">Confirmation du nouveau mot de passe</div>
					<div class="right">
						<input class="full" id="password2" type="password"/>
					</div>
				</div>
				<input id="modPasswordBtn" type="button" value="Enregistrer les modifications"/>
			</form>
		</div>
		<%
			}
			else{
				%>
				<h1>Vous n'êtes pas connecté</h1>
				<% 
			}
		%>
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