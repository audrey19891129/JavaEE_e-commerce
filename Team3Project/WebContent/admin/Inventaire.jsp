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
<body class="bg-warning">
<br>
	<div class="container">
  <div class="row text-center">
  <div class="col-md-2"></div>
    <div class="col-md-4">
      <img src="${productInv.image}" style="height:100%; max-height: 400px;"/><br>
      <% Product pr = (Product) request.getAttribute("productInv");
      		if(pr.getStatus().equals("active")){ %>
      <label class="badge badge-success text-uppercase text-wrap" style="font-size: 100%">Status : <label>${productInv.status}</label></label><br>
      <%}else{ %>
      <label class=" badge badge-danger text-uppercase text-wrap" style="font-size: 100%">Status : <label>${productInv.status}</label></label><br>
      <%} %>
    </div>
    <div class="col-md-4 text-left">
      <label>Nom du produit : <label class="text-primary font-weight-bold"> ${productInv.name}</label></label><br>
      <label>Categories : <label class="text-primary font-weight-bold">${productInv.category}</label></label><br>
      <label>Description : <label class="text-primary font-weight-bold">${productInv.description}</label></label><br>
      <label>Pays : <label class="text-primary font-weight-bold">${productInv.country}</label></label><br>
      <label>Province : <label class="text-primary font-weight-bold">${productInv.region}</label></label><br>
      <label>Producteur : <label class="text-primary font-weight-bold">${productInv.producer}</label></label><br>
      <label>Alcool : <label class="text-primary font-weight-bold">${productInv.alcohol} %</label></label><br>
      <label>Taille : <label class="text-primary font-weight-bold">${productInv.size} ml</label></label><br>
      <label>Prix : <label class="text-primary font-weight-bold">${productInv.price} $</label></label><br>
      <label>Site Web : <a class="font-weight-bold" href="${productInv.website }">${productInv.website}</a></label><br>
    </div>
    <div class="col-md-2"></div>
  </div>
</div>
<br>
	<div class="container">	
	<form action="GestionInventaireController" method="post">
		<input type="hidden" name="action" value="EDIT" />
		<input type="hidden" name="txtPcode" value="${productInv.pcode}" />
		<div class="form-row">
			<div class="form-group col-md-4"></div>
				<div class="form-group col-md-2 text-center">
					<label class="text-primary font-weight-bold">Inventaire Actuel </label>
					<input class="form-control font-weight-bold text-primary text-center" value="${productInv.inventory}" />
				</div>
				<div class="form-group col-md-2  text-center">
					<label >Nouveau Inventaire </label>
						<input class="form-control text-center" type="number" min="0" step="1" name="txtInventory" value="${productInv.inventory}" required="required"/>
				</div>
				<div class="form-group col-md-4"></div>
				<div class="form-group col-md-4"></div>
				<div class="form-group col-md-2">
					<a href="GestionInventaire.jsp" class="form-control btn btn-dark"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
					<path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z"/>
					</svg> Retour</a>
				</div>
				<div class="form-group col-md-2">
					<input class="btn btn-primary form-control" type="submit" value="Enregistrer"/>
				</div>
				<div class="form-group col-md-4"></div>
			</div>
		</form>
	</div>
</body>
</html>
