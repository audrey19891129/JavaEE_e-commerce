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
<style>
		.ContactForm>input[type="text"], .ContactForm>input[type="email"], textarea{
			margin:5px;
			font-size:110%;
			padding:2px;
			border:1.5px solid green;
		}
		.ContactForm{
			padding:10px;
			background-color:#f2f2f2;
		}
		textarea{
			font-family:roboto;
			resize: none;
			height:300px;
		}

</style>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>

<div id="container">
<%
	if(request.getParameter("status") != null){
		%>
		<script type="text/javascript">
			alert("Votre message a été envoyé!");
		</script>
		<%
	}
%>
	<div class="main">
	<p>
		Vous connaissez des producteurs ou des produits locaux ne faisant pas encore partie de notre plateforme? <br/>
		Vous avez des commentaires ou des suggestions à nous faire part?<br/>
		N'hésitez -pas à nous contacter via ce formulaire!
	</p>
		<form id="frmContact" method="post" action="sendEmail" class="table">
			<div class="header">Contactez-nous</div>
			<div class="content ContactForm">
				
			<input id="name" type="text" name="name" placeholder="Votre Nom Complet">
			<input  id="email" type="email" name="email" placeholder="Votre adresse Courriel"/><br>
			<textarea  id="message" name="message"placeholder="Ecrivez votre courriel."></textarea><br>
			<input type="button" class="SendEmail" value="Envoyer"/><br>
			</div>
		</form>
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