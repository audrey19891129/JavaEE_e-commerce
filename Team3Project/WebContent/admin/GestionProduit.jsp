<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.*"%>
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
	<h1 class="text-center text-white">GESTION DE PRODUIT</h1><br>>
	<div class="container jumbotron">
		<h2>Ajouter un Produit <svg xmlns="http://www.w3.org/2000/svg" width="45" height="45" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
</svg></h2>
		<br>
		<form action="../AddNewProduct" method="post">
			<div class="form-row">
				<div class="form-group col-md-4">
					<label>Nom du produit</label> 
					<input type="text" class="form-control" placeholder="nom" name="txtName" required="required">
				</div>
				<div class="form-group col-md-2">
					<label class="text-bold">Categories</label> 
					<input list="categories" name="txtCategories" class="form-control" required="required">
						<datalist id="categories">
							<% ArrayList<String> categories = Category.getAllCategories();
									for(String cat : categories){ %>
							<option value="<%=cat%>">
							<% } %>
						</datalist>
				</div>
				<div class="form-group col-md-6">
					<label>Description</label> 
					<input type="text" class="form-control" placeholder="description" name="txtDescription" required="required">
				</div>
				<br>
				<div class="form-group col-md-3">
					<label>Pays</label>
					<select class="form-control" propType="country" id="country" name="txtPays" required="required"></select>
				</div>
				<div class="form-group col-md-3">
					<label>Province</label>
					<select class="form-control" propType="province" id="state" name="txtProvince" required="required"></select>
				</div>
				<div class="form-group col-md-6">
					<label>Producteur</label>
					<input type="text" class="form-control" name="txtProducteur" placeholder="nom du producteur" required="required" />
				</div>
				<br>
				<div class="form-group col-md-1">
					<label>Alcool / %</label>
					<input class="form-control" type="number" min="0" max="100" step="0.1" name="txtAlcool"required="required" />
				</div>
				<div class="form-group col-md-1">
					<label>Millilitre/ml</label>
					<input class="form-control" type="number" min="0" name="txtMl" required="required" />
				</div>
				<div class="form-group col-md-2">
					<label>Prix / $</label>
					<input class="form-control" type="number" min="0" name="txtPrix" step="0.01" required="required"/>
				</div>
				<div class="w-100"></div>
				<div class="form-group col-md-6">
					<label>Image</label>
					<input type="text" class="form-control" name="txtImage" placeholder="l'image du produit" required="required" />
				</div>
				<div class="form-group col-md-6">
					<label>Site Web</label>
					<input type="text" class="form-control" name="txtSiteWeb" placeholder="site du produit" required="required" />
				</div>
				<div class="w-100"></div>
				<br>
				<div class="col-md-3">
					<button type="submit" class="form-control btn btn-success">Ajouter <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
  <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
</svg></button>
				</div>
			</div>
		</form>
	</div>
		<div class="container-fluid ">
			<div class="card text-white bg-success mb-3">
				<div class="card-header border-dark">Liste des Produits</div>
				<div class="card border-dark" style="overflow-y: auto;max-height: 500px;">
					<table class="table">
						<tbody class="text-center">
							<tr>
								<th>images</th>
								<th>Nom des Produits</th>
								<th>Categories</th>
								<th>Description</th>
								<th>Pays</th>
								<th>Province</th>
								<th>Taux % d'alcool</th>
								<th>Producteur</th>
								<th>millilitre / ml</th>
								<th>Site Web</th>
								<th>Prix</th>
							</tr>
							<% ArrayList<Product> list = Product.get_SQLQueryJEAN_MARTIN("SELECT * FROM `ayjml`.`product` WHERE (`status` = 'active') ORDER BY `pcode` DESC;"); 
									for(Product pr : list){	
							%>
							<tr>
								<td class="text-center"><img style="height: 8rem"
									src="<%=pr.getImage()%>"></td>
								<td><%=pr.getName() %></td>
								<td><%=pr.getCategory() %></td>
								<td><%=pr.getDescription() %></td>
								<td><%=pr.getCountry() %></td>
								<td><%=pr.getRegion() %></td>
								<td><%=pr.getAlcohol() %> %</td>
								<td><%=pr.getProducer() %></td>
								<td><%=pr.getSize() %> ml</td>
								<td><a href="<%=pr.getWebsite() %>">link</a></td>
								<td><%=pr.getPrice() %> $</td>
								<td><a href="ModifierProduit.jsp?pcode=<%=pr.getPcode()%>" class="btn btn-primary">Modifier <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
  <path d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
</svg></a></td>
								<% } %>
							</tr>							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</div>
		<div class="container-fluid ">
		<div class="card text-white bg-danger mb-3">
			<div class="card-header border-dark">Les Archives</div>
				<div class="card border-dark" style="overflow-y: auto;max-height: 500px;">
				<table class="table">
						<tbody class="text-center">
						<tr>
								<th>images</th>
								<th>Nom des Produits</th>
								<th>Categories</th>
								<th>Description</th>
								<th>Pays</th>
								<th>Province</th>
								<th>Taux % d'alcool</th>
								<th>Producteur</th>
								<th>millilitre / ml</th>
								<th>Site Web</th>
								<th>Prix</th>
							</tr>
							<% ArrayList<Product> listpr = Product.get_SQLQueryJEAN_MARTIN("SELECT * FROM `ayjml`.`product` WHERE (`status` = 'inactive');"); 
									for(Product prd : listpr){																			
							%>
							<tr>
								<td class="text-center"><img style="height: 8rem"
									src="<%=prd.getImage()%>"></td>
								<td><%=prd.getName() %></td>
								<td><%=prd.getCategory() %></td>
								<td><%=prd.getDescription() %></td>
								<td><%=prd.getCountry() %></td>
								<td><%=prd.getRegion() %></td>
								<td><%=prd.getAlcohol() %> %</td>
								<td><%=prd.getProducer() %></td>
								<td><%=prd.getSize() %> ml</td>
								<td><a href="<%=prd.getWebsite() %>">link</a></td>
								<td><%=prd.getPrice() %> $</td>
							<% } %>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	</body>
<script>

  populateCountries("country", "state");
  populateStates("country", "state");

</script>
</html>