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
<%
if(request.getParameter("status") != null){
	%>
	<script type='text/javascript'>alert('Desole, un compte appartenant a cette adresse courriel existe deja.')</script>);
	<%
}
%>
<div id="container">
	<div class="main">
		<div class="table"  style="max-width:600px; margin:auto;">
			<div class="header">Nouveau compte</div>
			<form class="content" method="post" action="Register" id="formRegister">
					<div class="row">
						<div class="left">Prénom</div>
						<div class="right">
							<input class="full" id="firstname" type="text" name="firstname"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Nom</div>
						<div class="right">
							<input class="full" id="lastname" type="text" name="lastname"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Date de naissance</div>
						<div class="right">
							<input class="full" id="birthday" type="text" name="birthday" placeholder="AAAA-MM-JJ"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Adresse courriel</div>
						<div class="right">
							<input class="full" id="email" type="email" name="email"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Mot de passe</div>
						<div class="right">
							<input class="full" id="password1" type="password" name="password"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Confirmation du mot de passe</div>
						<div class="right">
							<input class="full" id="password2" type="password"/>
						</div>
					</div>
					<div class="row">
						<div class="left">Numéro civique </div>
						<div class="right">
							<input class="full" id="civicnumber" type="text" name="civicnumber"/>
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
							<input class="full" type="text" id="zipcode" name="zipcode" placeholder="A0B-1C3"/>
						</div>
					</div>
					<input id="registerBtn" type="button" value="S'enregistrer"/>
				</form>
			
		</div>
	</div>
</div>
<script>

  populateCountries("country", "state");
  populateStates("country", "state");

</script>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>