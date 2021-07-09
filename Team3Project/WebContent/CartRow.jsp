<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.text.DecimalFormat" %>
<% 
Object obj = request.getAttribute("entry");
Entry e = (Entry) obj;
Product p = e.getProduct();
%>

<div class='row'>
	
		<div class='rowItem'><img src ='<%=p.getImage()%>' class='img'></div>
		<div class='rowItem'><%=p.getName()%></div>
		<div class='rowItem'><%=String.format("%.2f", p.getPrice()) + " $"%></div>
		<div class='rowItem quantRow'>
			<form method="post" action="modifyCart" >
				<input class='txtBoxQuant' type='number' min='0' max='10' name='quantity' value='<%=e.getQuantity()%>' onchange="if(checkInput(this.value)){submit()}"></input>
				<input type="hidden" name="product" value="<%=p.toString()%>"/>
			</form>	
			<form method="post" action="removeFromCart" >
				<input type="hidden" name="product"  value="<%=p.toString()%>"/>
				<input type="submit" class='removeItem' value="&times;"/>
			</form>
		</div>
		<div class='rowItem'><%=String.format("%.2f", (p.getPrice() * e.getQuantity())) + " $"%></div>
</div>