<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<% 
session = request.getSession(false);
if(session.getAttribute("employee") == null){
	response.sendRedirect("../index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="Resources.jsp"></jsp:include>
<title>Administrateur</title>
<jsp:include page="NavAdmin.jsp"></jsp:include>
</head>
<body class="bg-info">
<div class="text-center">
	<img src="https://www.freelogodesign.org/file/app/client/thumb/6b48ba4d-cd1d-4a4f-ae6e-c15b1d4d463b_200x200.png?1612550970043">
</div>
<div class="container-fluid">
<form action="" method="POST" class="form-row">
<div class="col-md-1"></div>
	<div class="card text-white bg-primary col-md-4">
	  <div class="card-header">Gestion de produit</div>
	  <div class="card-body text-center">
	    <h5 class="card-title">Gestion de Produit</h5>
	    <p class="card-text">Vous pouvez ajouter un nouveau produit.</p>
	    <p class="card-text">Vous pouvez modifier les détails du produit.</p>
	    <p class="card-text">Voir la liste complète des produits</p>
	    <p class="card-text">Voir les archives concernant les produits</p>
	    
	    <a class="btn btn-primary border-dark form-control" href="GestionProduit.jsp">Enter</a>
	  </div>
	</div>
		  <div class="col-md-2"></div>
	<div class="card text-white bg-success col-md-4">
  <div class="card-header">Gestion d'inventaire</div>
  <div class="card-body text-center">
    <h5 class="card-title">Gestion d'inventaire</h5>
    	<p class="card-text">Vous pouvez ajouter de l'inventaire sur un produit.</p>
	    <p class="card-text">Vous pouvez réduire l'inventaire sur un produit.</p>
	    <p class="card-text">Vous pouvez changer le statut d'un produit</p>
	    <p class="card-text">Voir la liste complète des produits avec leur inventaire</p>
    <a class="btn btn-success border-dark form-control" href="GestionInventaire.jsp">Enter</a>
  </div>
</div>
</form>
</div>
<br>
<footer class="text-center text-white">
<div>© 2021 ALYLJML Tous droits réservés Conditions d'utilisation | Politique de confidentialité</div>
</footer>
</body>
</html>