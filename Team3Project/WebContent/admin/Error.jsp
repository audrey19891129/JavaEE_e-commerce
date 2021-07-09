<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
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
<title>ERROR</title>
<jsp:include page="NavAdmin.jsp"></jsp:include>
</head>
<body class="bg-danger text-center text-white">
<div class="container">
<h1>ERROR</h1>
		<h3>Désolé, une erreur est survenue lors de cette requête.</h1><br><br>
		<img src="https://wpklik.com/wp-content/uploads/2019/03/A-404-Page-Best-Practices-and-Design-Inspiration.jpg" width="650px" height="500px">
	<br><br>
	</div>
	<a class="btn btn-warning" href="index.jsp">Retour à la page d'acceuil</a>
</body>
</html>