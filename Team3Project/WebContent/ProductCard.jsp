<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ page import="model.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.text.DecimalFormat" %>
<% 
Object obj = request.getAttribute("product");
Product p = (Product) obj;
String price = String.format("%.2f", p.getPrice()); 
%>


<form method="post" action="addToCart" class='item' obj="<%=p.toString()%>">
<div>
	<div class='innerTop'>
		<img class='imageItem' src="<%=p.getImage()%>"/>
	</div>
	<div class='innerBtm'>
		<div class='cartInnerTop'>
			<div class='description'>
			<i><%=p.getName()%></i><br>
			<%=p.getDescription()%><br>
			<%=p.getSize()%> ml</div>
		</div>
		<div class='cartInnerBtm'>
			<div class='price'><%=price%> $</div>
			<input type="hidden" name="product"  value="<%=p.toString()%>"/>
		</div>
	</div>
	<input type="submit" class='addToCart' value="Add to cart"/>
</div>
</form>

