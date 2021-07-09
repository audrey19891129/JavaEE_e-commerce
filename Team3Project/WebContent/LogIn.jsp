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
<%
	if(request.getParameter("status") != null){
		String status = request.getParameter("status");
		if(status.contentEquals("registered")){
			%>
			<script type="text/javascript">
				alert("Votre compte a été créé.");
			</script>
			<%
		}
		else if(status.contentEquals("authenticationfail")){
			%>
			<script type="text/javascript">
				alert("Votre compte compte n'a pas pu être authentifié. Vérifiez que vos informations sont valides.");
			</script>
			<%
		}
	}
	
%>
<div id="container">
	<div class="main">
		<div class="table"  style="max-width:500px; margin:auto;">
			<div class="header">Connection</div>
			<form class="content" method="post" action="LogIn">
				<div class="row">
					<div class="left">Courriel :</div>
					<div class="right"><input name="email" type="text" class="full"/></div>
				</div>
				<div class="row">
					<div class="left">Password :</div>
					<div class="right"><input name="password" type="password" class="full"/></div>
				</div>
				<input type="submit" value="Se connecter"/>
				<a href="Register.jsp">Créer un compte!</a>
			</form>
		</div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>