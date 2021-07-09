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
<body class="bg-primary">
	<div class="text-center">
		<img
			src="https://www.freelogodesign.org/file/app/client/thumb/6b48ba4d-cd1d-4a4f-ae6e-c15b1d4d463b_200x200.png?1612550970043">
	</div>
			<h1 class="text-center text-white">Modifier <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
  <path d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
</svg></h1>
		<br>
	<div class="container jumbotron">
		<form action="../ModifyProduct" method="get">
		<%ArrayList<Product> list = Product.get_SQLQueryJEAN_MARTIN("SELECT * FROM `ayjml`.`product` WHERE `pcode`='" + request.getParameter("pcode") + "'"); 
			for(Product pr : list){
		%>
			<img class="rounded mx-auto d-block" style="height: 15rem; width: 10rem;" src="<%=pr.getImage()%>">
			<div class="form-row">							
				<div class="form-group col-md-4">
					<label>Nom du produit</label> 
					<input type="text" class="form-control" placeholder="nom" name="txtName" value="<%=pr.getName()%>" required="required">
				</div>
				<div class="form-group col-md-2">
					<label class="text-bold">Categories</label> 
					<input list="categories" name="txtCategories" class="form-control" value="<%=pr.getCategory()%>" required="required">
						<datalist id="categories">
							<% ArrayList<String> categories = Category.getAllCategories();
									for(String cat : categories){ %>
							<option value="<%=cat%>">
							<% } %>
						</datalist>
				</div>
				<div class="form-group col-md-6">
					<label>Description</label> 
					<input type="text" class="form-control" placeholder="description" name="txtDescription" value="<%=pr.getDescription()%>" required="required">
				</div>
				<br>
				<div class="form-group col-md-3">
					<label>Pays</label>
					<select class="form-control" propType="country" id="country<%=pr.getPcode()%>" name="txtPays" value="<%=pr.getCountry()%>"  required="required"></select>
				</div>
				<div class="form-group col-md-3">
					<label>Province</label>
					<select class="form-control" propType="province" id="state<%=pr.getPcode()%>" name="txtProvince" value="<%=pr.getRegion()%>" required="required"></select>
				</div>
				<div class="form-group col-md-6">
					<label>Producteur</label>
					<input type="text" class="form-control" name="txtProducteur" placeholder="nom du producteur" value="<%=pr.getProducer()%>" required="required" />
				</div>
				<br>
				<div class="form-group col-md-1">
					<label>Alcool / %</label>
					<input class="form-control" type="number" min="0" max="100" step="0.1" name="txtAlcool" value="<%=pr.getAlcohol()%>" required="required" />
				</div>
				<div class="form-group col-md-1">
					<label>Millilitre/ml</label>
					<input class="form-control" type="number" min="0" name="txtMl" value="<%=pr.getSize()%>" required="required" />
				</div>
				<div class="form-group col-md-2">
					<label>Prix / $</label>
					<input class="form-control" type="number" min="0" name="txtPrix" step="0.01" value="<%=pr.getPrice()%>" required="required"/>
				</div>
				<div class="w-100"></div>
				<div class="form-group col-md-6">
					<label>Image</label>
					<input type="text" class="form-control" name="txtImage" placeholder="l'image du produit" value="<%=pr.getImage()%>" required="required" />
				</div>
				<div class="form-group col-md-6">
					<label>Site Web</label>
					<input type="text" class="form-control" name="txtSiteWeb" placeholder="site du produit" value="<%=pr.getWebsite()%>" required="required" />
				</div>
				<div class="w-100"></div>
				<br>
				<div class="col-md-3">
					<a href="GestionProduit.jsp" class="form-control btn btn-dark"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
  					<path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z"/>
					</svg> Retour</a>
				</div>
				<div class="col-md-3">
					<button type="submit" name="pcode" class="form-control btn btn-primary" value="<%=pr.getPcode()%>">Modifier</button>
				</div>
			</div>
			<script type="text/javascript">
					populateCountries("country<%= pr.getPcode() %>", "state<%= pr.getPcode() %>");
					$("#country<%= pr.getPcode() %>").val("<%= pr.getCountry() %>");
					var index = $("#country<%= pr.getPcode() %>").prop('selectedIndex');
					autoPopulateStates(index, "state<%= pr.getPcode() %>");
					$("#state<%= pr.getPcode() %>").val("<%= pr.getRegion() %>");
				</script>
			<% } %>
		</form>
	</div>
</body>
<script>
  populateCountries("country", "state");
  populateStates("country", "state");
</script>
</html>