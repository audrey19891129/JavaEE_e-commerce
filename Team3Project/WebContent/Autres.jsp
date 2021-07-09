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
 <link rel="stylesheet" href="css/navigator.css">
 <link rel="stylesheet" href="css/productspage.css">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/map.css">
<link rel="stylesheet" href="css/caroussel.css">
 <link rel="stylesheet" href="css/common.css">
<title>Vins</title>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>
<div class="full" style="background-color:#f5f5f5;padding-top:25px;">
		<form id="searchBar" class="searchBar ui-widget" action="getProductByName" method="post">
		<div class="searchContainer">
			<input id="tags" name="name">
			<div class="searchBtn">Rechercher</div>
		</div>
	</form>
</div>
<div id="container">
	<div id="wines">
				
			<% 
				ArrayList<String> productsNames = new ArrayList<String>();
				ArrayList<String> categories = Category.getAllCategories();
				Service service = new Service();
				for(String cat : categories){
					if(!(cat.contentEquals("vin") || cat.contentEquals("cidre") || cat.contentEquals("hydromel"))){
					Category c = service.category(cat); 
					ArrayList<Product> List = c.getProducts();
						if(!(List.isEmpty())){
						%>
						<h2 style="margin:50px;">LES <%=(cat).toUpperCase()%>S</h2>
						<%
							for (Product p : List) 
							{ 
								if(!(productsNames.contains(p.getName()))){
									productsNames.add('"'+ p.getName() + '"');
								}
								%>
								<% request.setAttribute("product", p); %>
								<jsp:include page="ProductCard.jsp"></jsp:include>
								<%
													
							}
						}
					}
				}
				
				 %>
				
		</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
<script type="text/javascript">
var productTitles = <%= productsNames %>;

$("#tags").autocomplete({
	source: function (request, response) {
		var results = $.ui.autocomplete.filter(productTitles, request.term);
		response(results.slice(0, 10));
	}
});

$("#tags").on("click", function (event) {
	event.stopPropagation();
	productTitles = productTitles.sort();
	$("#tags").val("");
});

$(".searchBtn").on("click", function (event) {
	event.stopPropagation();

	if ($("#tags").val().trim() !== "") {
		$("#searchBar").submit();
	}
	else {
		$("#tags").val("Sorry, this keyword or title does not match any product");
    }

});
</script>
</body>
</html>