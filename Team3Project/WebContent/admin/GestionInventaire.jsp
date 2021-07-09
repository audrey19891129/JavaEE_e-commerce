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
<body class="bg-success">
<div class="text-center">
	<img src="https://www.freelogodesign.org/file/app/client/thumb/6b48ba4d-cd1d-4a4f-ae6e-c15b1d4d463b_200x200.png?1612550970043">
</div>

		<div class="container-fluid ">
			<div class="card text-white bg-success mb-3">
				<div class="card-header border-dark">Liste des Produits dans l'inventaire</div>
				<div class="card border-dark">
					<table class="table">
						<tbody class="text-center">
							<tr>
								<th>images</th>
								<th>Nom des Produits</th>
								<th>Categories</th>								
								<th>Taux % d'alcool</th>
								<th>millilitre / ml</th>
								<th>Prix</th>
								<th>Status</th>
								<th>Inventaire</th>
							</tr>
							<% ArrayList<Product> list = Product.get_SQLQueryJEAN_MARTIN("select * from `ayjml`.`product` ORDER BY `inventory`"); 
									for(Product pr : list){																			
							%>							
							<tr>
								<td class="text-center"><img style="height: 8rem"
									src="<%=pr.getImage()%>"></td>
								<td><%=pr.getName() %></td>
								<td><%=pr.getCategory() %></td>
								<td><%=pr.getAlcohol() %></td>
								<td><%=pr.getSize() %></td>
								<td><%=pr.getPrice() %></td>
								<%if(pr.getStatus().equals("active")){%>
								<td class="font-weight-bold h4 text-success"><%=pr.getStatus() %></td>
								<%}else{ %>
								<td class="font-weight-bold h4 text-danger"><%=pr.getStatus() %></td>
								<%} %>
								<td class="font-weight-bold h4"><%=pr.getInventory() %></td>
								<td><form action="Gestion" method="post">
								<input type="hidden" name="pcode" value="<%=pr.getPcode()%>"/>
								<input type="hidden" name="status" value="<%=pr.getStatus()%>"/>
								<input type="hidden" name="inventory" value="<%=pr.getInventory()%>"/>
								<button type="submit" name="action" class="btn btn-primary" value="INVENTAIRE" >Inventaire </button>
								<%if(pr.getStatus().equals("active")){%>
								<button type="submit" name="action" class="btn btn-danger" value="CHANGE" >Inactive</button>
								<%}else{ %>				
								<button type="submit" name="action" class="btn btn-success" value="CHANGE" >Active</button>	
								<%} %>				
								</form></td>												
							</tr>
							<% } %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
</body>
</html>