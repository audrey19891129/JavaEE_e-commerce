<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="table"  style="width:95%;margin:auto;max-width:400px;display:inline-block;">
			<div class="header" style="background-color:red;">Déconnection</div>
			<form class="content" method="post" action="LogOut">
				<div class="row">
					<div class="full" style="font-weight:bold;">
						Etes-vous sûr(e) de vouloir vous déconnecter?
					</div>
				</div>
				<div class="row">
					<div style="width:50%"><input name="btn" value="Oui" type="submit" class="full"/></div>
					<div style="width:50%"><input value="Non" type="submit" class="full"/></div>
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>