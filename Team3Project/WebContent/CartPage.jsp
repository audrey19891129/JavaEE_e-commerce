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
 <link rel="stylesheet" href="css/cartpage.css">
   <link rel="stylesheet" href="css/map.css">
     <link rel="stylesheet" href="css/caroussel.css">
<title>Cart</title>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>
<%
if(request.getParameter("status") != null){
		%>
		<script type="text/javascript">
			alert("Votre commande a été envoyée!");
		</script>
		<%
	}
%>
<div id="container">
<div class="main" id="idCartPage">

<div class="width100"><br><h1>PANIER</h1><br></div>

<%

request.setCharacterEncoding("UTF-8");

if(session.getAttribute("cart") != null){
	
	Order cart = (Order) session.getAttribute("cart");
	ArrayList<Entry> entries = cart.getEntries();	
	if(entries.size() >0){
	%>
	<jsp:include page="CartHead.jsp"></jsp:include>
	<%
	
	for(Entry e : entries){
	
		Product p = e.getProduct();
		%>
		<% 
			request.setAttribute("entry", e); 
		%>
		<jsp:include page="CartRow.jsp"></jsp:include>
		<%
	}
	%>
	<jsp:include page="CartFoot.jsp"></jsp:include>
	<%
	}
	else{
		out.write("<div class='width100'><br><h2>Votre panier est vide.</h2></div>");
	}
}
else{
	 out.write("<div class='width100'><br><h2>Votre panier est vide.</h2></div>");
}
%>

</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>