<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ page import="model.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.util.ArrayList" %>
<% 

    int items = 0;

    if(session.getAttribute("cart") !=null){
    	 Order cart = (Order) session.getAttribute("cart");
    	 ArrayList<Entry> entries = cart.getEntries();
    	 
    	for(Entry e : entries){
        	items += e.getQuantity();
        }
    }
    	%>
    
<!DOCTYPE html>
<script type="text/javascript" src="countries.js"></script>
<header>
<div id="Carousel">
	<div class="innerLayout">
		<div class="m1" value="-1">
			<div></div>
			<div class="arrow">&#9664;</div>
		</div>
		<div class="m2">
			<div></div>
			<div id="dotsHolder"></div>
		</div>
		<div class="m3" value="1">
			<div></div>
			<div class="arrow">&#9654;</div>
		</div>
	</div>
</div>
</header>
<nav>
<div>
<div id="miniNavigator" class="miniNavigator">
		&#9776;
		<ul id="navigator2"></ul>
	</div>
</div>
<ul id="navigator">
			<%
				if(session.getAttribute("client") != null){
					Client client = (Client)session.getAttribute("client");
			%>
				<li class="lvl1">Bienvenue <%= client.getName() %></li>
			<%
				}
			%>
			<li id="IndexNav" class="lvl1 seeCart"><a class="seeCart" href="index.jsp">ACCUEIL</a></li>
			<li id="productsNav" class="lvl1">
				Produits
				<ul id="productsNavUl" class="subNav">
					<li class="lvl2"><a id="" href="ProductsList.jsp">Tous les produits</a></li>
					<li class="lvl2"><a id="" href="Vins.jsp">Vins</a></li>
					<li class="lvl2"><a id="" href="Cidres.jsp">Cidres</a></li>
					<li class="lvl2"><a id="" href="Hydromels.jsp">Hydromels</a></li>
					<li class="lvl2"><a id="" href="Autres.jsp">Autres</a></li>
				</ul>
			</li>
			<li id="contactNav" class="lvl1"><a id="" href="Contact.jsp">Contactez-nous</a></li>
			<li id="accountNav" class="lvl1">
				Mon Compte
				<ul id="accountNavUl" class="subNav">
					<li class="lvl2"><a id="snInfos"  href="AccountInfos.jsp">Informations</a></li>
					<li class="lvl2"><a id="snOrders" href="AccountOrders.jsp">Commandes</a></li>
					<li class="lvl2"><a id="snPay" href="AccountCards.jsp">Modes de paiement</a></li>
					<li class="lvl2"><a id="snAddr" href="AccountAddresses.jsp">Adresses</a></li>
					<%
				if(session.getAttribute("client") != null){
					%>
					<li class="lvl2"><a href="LogOut.jsp">Se déconnecter</a></li>
					<%
				}else{
					%>
					<li class="lvl2"><a id="snLogin" href="LogIn.jsp">Se connecter</a></li>
					<%
				}
					%>
				</ul>
			</li>
			<li id="cartNav" class="lvl1 seeCart"><a class="seeCart" href="CartPage.jsp">PANIER articles: <%=items%></a></li>
		</ul>
</nav>