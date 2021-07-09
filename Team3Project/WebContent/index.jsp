<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ page import="Resources.*" %>
<%@ page import="java.text.DecimalFormat" %>
<% 
	XMLFile.refreshXMLFile();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="/css/common.css">
 <link rel="stylesheet" href="/css/navigator.css">
 <link rel="stylesheet" href="/css/tables.css">
  <link rel="stylesheet" href="/css/map.css">
  <link rel="stylesheet" href="/css/caroussel.css">
 <link rel="stylesheet" type="text/css" href="/jqueryUI/jquery-ui.css">
<title>AYJM</title>
<style>
.lorem{
	margin:auto; 
	padding:25px;
	text-align:justify;
	line-heigh:1.5;
}
</style>
</head>
<body>
<jsp:include page="Navigator.jsp"></jsp:include>

<div id="container">

	<div class="main">
	<div class="main" style="background-color: gray; align-content: center">
	 <img src="https://www.freelogodesign.org/file/app/client/thumb/6b48ba4d-cd1d-4a4f-ae6e-c15b1d4d463b_200x200.png?1612550970043" style="width: 25%">
	</div>
				<h2 style="font-style:italic;">Bienvenue chez AYJML</h2>
				<p class="lorem">
					&emsp;Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vel ipsum ac neque pellentesque maximus. Donec nec tincidunt risus. Aliquam mattis elit mauris, sodales semper velit malesuada vitae. Nulla at nulla vitae lacus tristique pharetra et eget ligula. Cras eu sapien justo. Integer a commodo nibh, ac volutpat turpis. Ut cursus nisl eu augue ornare consequat. Mauris pellentesque varius blandit. Curabitur vestibulum lobortis sapien. Vestibulum eget dolor sem. In hac habitasse platea dictumst.

Proin finibus elementum neque sit amet luctus. Sed et eros non erat dignissim maximus vitae sed magna. Donec gravida, nisi in dapibus consectetur, massa mauris tempus nulla, in imperdiet tortor leo non velit. Nullam ullamcorper tristique nulla in venenatis. Aliquam sollicitudin lorem sit amet efficitur laoreet. Aliquam lacinia nisi felis, ullamcorper cursus est fermentum et. Phasellus sagittis tortor augue, non vehicula turpis varius a. Morbi commodo viverra nulla, at eleifend metus luctus sit amet.<br/><br/> &emsp;Phasellus non nunc ullamcorper, pharetra felis quis, varius diam. Integer bibendum ultricies blandit. Cras turpis elit, fermentum vel luctus et, dictum a tellus.

Ut eget libero arcu. Sed eros tortor, interdum eget sollicitudin consequat, facilisis sit amet lacus. Aliquam dictum massa eget massa dignissim sagittis. Nunc in ante et magna tincidunt maximus. Cras dictum, nibh vitae vulputate blandit, nunc diam finibus nulla, vel maximus metus dui nec mi. Etiam vitae scelerisque mi. Curabitur eget pretium libero. Proin maximus magna ut porttitor ultricies. Proin fringilla ante in est laoreet, nec pulvinar libero rhoncus. Donec molestie eleifend sem, pellentesque venenatis ipsum ultrices nec. Morbi ac dignissim leo.

Sed maximus ultrices tempor. Sed placerat erat sed vehicula pulvinar. Aenean ultrices tortor at sagittis interdum. Cras sed mi in erat mollis bibendum quis id libero. Morbi quis nulla sed sapien ullamcorper facilisis. Donec eget malesuada augue, eget convallis magna. Fusce sed rutrum mauris, vel dignissim ipsum. In neque nulla, auctor eu quam sed, vehicula pharetra nisl. Phasellus malesuada elit id ex pellentesque, vel malesuada diam congue. Proin ac ipsum sed dolor aliquam consectetur vitae at arcu.

Nam eleifend dignissim congue. Aenean ante tortor, eleifend et blandit ac, sollicitudin id nisi. Morbi purus tellus, rhoncus nec feugiat in, ornare sed orci. Aliquam elementum felis vitae imperdiet facilisis. Aliquam ipsum risus, accumsan cursus ultricies in, imperdiet et ipsum. Vivamus cursus tellus ac risus interdum, quis commodo diam vulputate. Duis maximus dolor et mauris pharetra scelerisque. Morbi a nisl vitae felis ultricies luctus sit amet in metus. Etiam volutpat elit justo. Sed tincidunt tellus diam, a viverra velit aliquet vitae. Nullam commodo congue magna, sed dictum urna rutrum vitae. Maecenas eget lacus in orci sodales fringilla. Nulla efficitur nisl ultricies, ullamcorper mi eget, consequat felis. Nullam eget orci egestas, efficitur risus ut, ultricies odio. Maecenas quis lacus sit amet elit fermentum imperdiet ut ut urna.
				
				</p>


	</div>

</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>

