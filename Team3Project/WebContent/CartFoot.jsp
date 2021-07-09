<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.text.DecimalFormat" %>
<% 
Order cart = (Order) session.getAttribute("cart");
ArrayList<Entry> entries = cart.getEntries();

double  AmountBtaxes = 0, 
GST = 0,
QST = 0,
total = 0;

for(Entry e : entries){
	Product p = e.getProduct();
	AmountBtaxes += (p.getPrice() * e.getQuantity());
}
GST = (AmountBtaxes * 0.05);
QST = (AmountBtaxes * 0.09975);
total = (AmountBtaxes + GST + QST);

Order newCart = cart;
newCart.setTotal(total);
session.setAttribute("cart", newCart);
%>

</div>
<div id='tableFoot'>
<div class='cartFoot'>Amount before taxes : <%=String.format("%.2f", AmountBtaxes)%> $</div>
	<div class='cartFoot'>GST :  <%=String.format("%.2f", GST)%> $</div>
	<div class='cartFoot'>QST :  <%=String.format("%.2f", QST)%> $</div>
	<div class='cartFoot'>TOTAL : <%=String.format("%.2f", total)%> $</div>
	<div class='cartFoot'>
	<form method="post" action="checkout" id="frmCheckout">
		<a href="emptycart">Empty cart</a>
		<input type='submit' id='buyBtn' class='buyBtn' value="Proceed to checkout"/>
		<input type="hidden" name="total" value="<%= total %>"/>
	</form>	
	</div>
</div>
</div>