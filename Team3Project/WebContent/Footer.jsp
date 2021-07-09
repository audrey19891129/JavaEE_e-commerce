<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
<div id="map">
	<div id="maparea">
		<div id="anchorMap"></div>
		<div id="floating-panel">
		<strong>Starting point:</strong>
	<select id="start">
	<option value="" selected>Current position</option>
	<option value="1473 Chemin Principal, Saint-Joseph-du-Lac, QC J0N 1M0">Domaine Lafrance</option>
	<option value="649 Chemin Principal, Saint-Joseph-du-Lac, QC J0N 1M0">Verger Lacroix</option>
	<option value="1944 Rang du Domaine, Saint-Joseph-du-Lac, QC J0N 1M0">Domaine du Petit Saint-Joseph</option>
	<option value="3018 Chemin Craig, Saint-Patrice-de-Beaurivage, QC G0S 1B0">Miellerie St-Patrice</option>
	<option value="1225 Chemin Royal, Saint-Pierre, QC G0A 4E0">Cassis Mona & Filles</option>
	<option value="5455 Chemin St André, Jonquière, QC G7X 7V4">Domaine le Cageot</option>
	<option value="37 Rang Saint Joseph, Notre-Dame-de-Lorette, QC G0W 1B0">Domaine L'Orée Des Bois</option>
	<option value="210 Route des Seigneurs, Saint-André-d'Argenteuil, QC J0V 1X0">Aux Cassis d'Argenteuil</option>
	<option value="5 Rte 137, Sainte-Cécile-de-Milton, Quebec J0E 2C0">Cidrerie Milton</option>
	</select>
	<br>
	<strong>Ending point :</strong>
	<select id="end">
	<option value="" selected disabled hidden>Choose store location</option>
	<option value="1473 Chemin Principal, Saint-Joseph-du-Lac, QC J0N 1M0">Domaine Lafrance</option>
	<option value="649 Chemin Principal, Saint-Joseph-du-Lac, QC J0N 1M0">Verger Lacroix</option>
	<option value="1944 Rang du Domaine, Saint-Joseph-du-Lac, QC J0N 1M0">Domaine du Petit Saint-Joseph</option>
	<option value="3018 Chemin Craig, Saint-Patrice-de-Beaurivage, QC G0S 1B0">Miellerie St-Patrice</option>
	<option value="1225 Chemin Royal, Saint-Pierre, QC G0A 4E0">Cassis Mona et Filles</option>
	<option value="5455 Chemin St André, Jonquière, QC G7X 7V4">Domaine le Cageot</option>
	<option value="37 Rang Saint Joseph, Notre-Dame-de-Lorette, QC G0W 1B0">Domaine L'Orée Des Bois</option>
	<option value="210 Route des Seigneurs, Saint-André-d'Argenteuil, QC J0V 1X0">Aux Cassis d'Argenteuil</option>
	<option value="5 Rte 137, Sainte-Cécile-de-Milton, Quebec J0E 2C0">Cidrerie Milton</option>
	</select>
			</div>
			<div id="right-panel"></div>
		</div>
	</div>
</footer>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAdRO5ZFd9gjeZDzJ8RrpOEMZ-z50IKsWQ"></script>
<script type="text/javascript" src="scripts.js"></script>
	
<script type="text/javascript">
InitializeMap();

$("#maparea").on('click', function (event) {

	if ($("#right-panel").html() != "") {
		if (!$(event.target).closest('#right-panel').length) {
			$("#right-panel").toggle("slide");
			$("#right-panel").html("");
		}
	}
});

function InitializeMap() {
	$("#right-panel").hide();
	if (!navigator.geolocation) {
		$("#anchorMap").html("GEO IS OFF");
		return false;
	}
	else {
		navigator.geolocation.getCurrentPosition(setPosition);
	}
}

function setPosition(position) {
	var latitude = position.coords.latitude,
		longitude = position.coords.longitude;
	var directionsDisplay = new google.maps.DirectionsRenderer;
	var directionsService = new google.maps.DirectionsService;
	var centerGMap = new google.maps.LatLng(latitude, longitude);
	var optionsGMap = {
		zoom: 12,
		center: centerGMap,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};

	var Newmap = new google.maps.Map(document.querySelector("#anchorMap"), optionsGMap);
	directionsDisplay.setMap(Newmap);
	directionsDisplay.setPanel(document.querySelector("#right-panel"));

	var markUser = new google.maps.Marker({
		position: { lat: latitude, lng: longitude },
		map: Newmap,
		title: "You are here",
		icon: 'img/marker.png'
	});
	var commentUser = "<div style='color:green;'><h1>You are here</h1></div>";
	var windowUser = new google.maps.InfoWindow({
		content: commentUser
	});
	google.maps.event.addListener(markUser, "click", function () {
		windowUser.open(Newmap, markUser);
	});

	var markLafrance = new google.maps.Marker({
		position: { lat: 45.5408599, lng: -74.015875 },
		map: Newmap,
		title: "Domaine Lafrance"
	});
	var commentLafrance = "<div style='color:green;'><h1>Domaine Lafrance</h1></div>";
	var windowLafrance = new google.maps.InfoWindow({
		content: commentLafrance
	});
	google.maps.event.addListener(markLafrance, "click", function () {
		windowLafrance.open(Newmap, markLafrance);
	});
	
	var markLacroix = new google.maps.Marker({
		position: { lat: 45.5259338, lng: -73.9965257 },
		map: Newmap,
		title: "Verger Lacroix"
	});
	var commentLacroix = "<div style='color:green;'><h1>Verger Lacroix</h1></div>";
	var windowLacroix = new google.maps.InfoWindow({
		content: commentLacroix
	});
	google.maps.event.addListener(markLacroix, "click", function () {
		windowLacroix.open(Newmap, markLacroix);
	});

	var markStJoseph = new google.maps.Marker({
		position: { lat: 45.5316057, lng: -74.0538295 },
		map: Newmap,
		title: "Domaine du Petit Saint-Joseph"
	});
	var commentStJoseph = "<div style='color:green;'><h1>Domaine du Petit Saint-Joseph</h1></div>";
	var windowStJoseph = new google.maps.InfoWindow({
		content: commentStJoseph
	});
	google.maps.event.addListener(markStJoseph, "click", function () {
		windowStJoseph.open(Newmap, markStJoseph);
	});

	var markStPatrice = new google.maps.Marker({
		position: { lat: 46.4448938, lng: -71.3325361 },
		map: Newmap,
		title: "Miellerie St-Patrice"
	});
	var commentStPatrice = "<div style='color:green;'><h1>Miellerie St-Patrice</h1></div>";
	var windowStPatrice = new google.maps.InfoWindow({
		content: commentStPatrice
	});
	google.maps.event.addListener(markStPatrice, "click", function () {
		windowStPatrice.open(Newmap, markStPatrice);
	});
	
	var markMona = new google.maps.Marker({
		position: { lat: 46.8769591, lng: -71.096289 },
		map: Newmap,
		title: "Cassis Mona & Filles"
	});
	var commentMona = "<div style='color:green;'><h1>Cassis Mona & Filles</h1></div>";
	var windowMona = new google.maps.InfoWindow({
		content: commentMona
	});
	google.maps.event.addListener(markMona, "click", function () {
		windowMona.open(Newmap, markMona);
	});
	
	var markCageot = new google.maps.Marker({
		position: { lat: 48.4650379, lng: -71.3622785 },
		map: Newmap,
		title: "Domaine le Cageot"
	});
	var commentCageot = "<div style='color:green;'><h1>Domaine le Cageot</h1></div>";
	var windowCageot = new google.maps.InfoWindow({
		content: commentCageot
	});
	google.maps.event.addListener(markCageot, "click", function () {
		windowCageot.open(Newmap, markCageot);
	});
	
	var markBois = new google.maps.Marker({
		position: { lat: 49.0694417, lng: -72.317705 },
		map: Newmap,
		title: "Domaine L'Orée Des Bois"
	});
	var commentBois = "<div style='color:green;'><h1>Domaine L'Orée Des Bois</h1></div>";
	var windowBois = new google.maps.InfoWindow({
		content: commentBois
	});
	google.maps.event.addListener(markBois, "click", function () {
		windowBois.open(Newmap, markBois);
	});
	
	var markArgenteuil = new google.maps.Marker({
		position: { lat: 45.6006894, lng: -74.3483283 },
		map: Newmap,
		title: "Aux Cassis d'Argenteuil"
	});
	var commentArgenteuil = "<div style='color:green;'><h1>Aux Cassis d'Argenteuil</h1></div>";
	var windowArgenteuil = new google.maps.InfoWindow({
		content: commentArgenteuil
	});
	google.maps.event.addListener(markArgenteuil, "click", function () {
		windowArgenteuil.open(Newmap, markArgenteuil);
	});
	
	var markMilton = new google.maps.Marker({
		position: { lat: 45.4834748, lng: -72.761802 },
		map: Newmap,
		title: "Cidrerie Milton"
	});
	var commentMilton = "<div style='color:green;'><h1>Cidrerie Milton</h1></div>";
	var windowMilton = new google.maps.InfoWindow({
		content: commentMilton
	});
	google.maps.event.addListener(markMilton, "click", function () {
		windowMilton.open(Newmap, markMilton);
	});
	
	var control = document.querySelector("#floating-panel");
	control.style.display = "block";
	Newmap.controls[google.maps.ControlPosition.TOP_CENTER].push(control);

	var onChangeHandler = function () {
		var startIndex = document.querySelector("#start").selectedIndex;
		var endIndex = document.querySelector("#end").selectedIndex;

		var startpoint = "";
		if (startIndex == 0) {
			startpoint = position.coords.latitude + ", " + position.coords.longitude;
		}
		else {
			startpoint = document.querySelector("#start").value;
		}
		var endingpoint = "";
		if (endIndex == 0) {
			endingpoint = startpoint;
		}
		else {
			endingpoint = document.querySelector("#end").value;
		}

		directionsService.route(
			{
				origin: startpoint,
				destination: endingpoint,
				travelMode: "DRIVING"
			}, function (response, status) {
				if (status === "OK") {
					$("#right-panel").toggle("slide");
					directionsDisplay.setDirections(response);
				}
				else {
					window.alert("Directions request failed due to " + status);
				}
			});
	}
	document.querySelector("#start").addEventListener("change", onChangeHandler);
	document.querySelector("#end").addEventListener("change", onChangeHandler);

}
</script>