var advertisement = ["img/banner/camerises.png", "img/banner/cassis.png", "img/banner/erable.png", "img/banner/fraises.png", "img/banner/framboises.png", "img/banner/griottes.png", "img/banner/houblon.png", "img/banner/miel.png", "img/banner/poires.png", "img/banner/pommesgelees.png",
"img/banner/prommes.png", "img/banner/prunes.png", "img/banner/raisins.png", "img/banner/saguenay.png", "img/banner/bleuets.png"];
var index = 0;
var timer = setInterval(function () {
				index++;
				showProduct();
			}, 6000);

setMenu();

$(advertisement).each(function (index) {
	$("#dotsHolder").append('<div class="dot" value="' + index + '"></div>');
});

var dots = $(".dot");
dots.eq(0).css("background-color", "white");

function checkInput(value){

	var reg = new RegExp("^[0-9]+$");
	if(reg.test(value)){
		if(value> 0 && value <=10){
			return true;
		}
	}
	else
		return false;
}

/*---------------------------Animates mini-menu----------------------------*/

	$("#miniNavigator").on("click", function () {

		if ($("#navigator2").is(":hidden"))
			$("#navigator2").show();
		else
			$("#navigator2").hide();
	});


	/*---------------------sets navigator on window resize----------------------------*/

	$(window).on("resize", function (event) {
		setMenu();
	});
	
	
	//--------------------------------------------------------------------------------------
	//	name: setMenu
	//	input: none
	//	how: Checks window width on resize (event call)
	//	why: initiates navigator bar: full size or mini-menu
	//	output: none
	//--------------------------------------------------------------------------------------

	function setMenu() {

		var w = parseInt($("nav").css("width"), 10);

		if (w < 1200) {

			if ($("#navigator2").is(':empty')) {
				$("#navigator2").prepend($("#IndexNav"), $("#productsNav"),$("#contactNav"),$("#accountNav"), $("#cartNav"));
				$("#productsNavUl").attr("class", "subNav2");
				$("#accountNavUl").attr("class", "subNav2");
				$("#miniNavigator").show();
				$("#miniNavigator").effect("highlight", { color: 'green' }, 500);
				$("#navigator2").hide();
			}
		}
		else {
			$("#navigator").prepend($("#IndexNav"), $("#productsNav"),$("#contactNav"),$("#accountNav"), $("#cartNav"));
			$("#productsNavUl").attr("class", "subNav");
			$("#accountNavUl").attr("class", "subNav");
			$("#miniNavigator").hide();
			}
	}
/*---------------------Carousel buttons functions----------------------------*/

	$("div").on("click", ".m1 , .m3", function (event) {
		event.stopPropagation();
		var v = $(this).attr("value");
		index += parseInt(v);
		showProduct();
	});

	$("div").on("click", ".dot", function (event) {
		event.stopPropagation();
		var v = $(this).attr("value");
		index = parseInt(v);
		showProduct();
	});
	
/*---------------------Carousel buttons functions END----------------------------*/	
	
	
	$("#addCardBtn").on("click", function (event) {
		event.stopPropagation();
		validateCardFields("#formAddCard")
	});
	
	$("#addAccCardBtn").on("click", function (event) {
		event.stopPropagation();
		validateCardFields("#formAccAddCard")
	});
	
	$(".modAccCardBtn").on("click", function (event) {
		event.stopPropagation();
		var index = $(this).attr("xvalue");
		var holdername = $("#holdername" + index).val().trim();
		var expirydate = $("#expirydate" + index).val().trim();
		bool = true;

			if(!validateInput("name", holdername)){
				$("#holdername").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("date", expirydate)){
				$("#expirydate").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(bool){
				$("#formAccModCard" + index).submit();
			}
	});
	
	$("#modPasswordBtn").on("click", function (event) {
	event.stopPropagation();
	var pass1 = $("#password1").val().trim();
	var pass2 = $("#password2").val().trim();
	bool = true;
	
	if(!validateInput("pass", pass1)){
		bool=false;
	}
	else if(!validateInput("pass", pass2)){
		bool=false;
	}
	else if(bool == true){

		if(pass1 != pass2){
			alert("Les mots de passe saisis ne sont pas identiques.")
		}
		else{
			$("#formModPassword").submit();
		}
	}
});


	function validateCardFields(form){
		var cardnumber = $("#cardnumber").val().trim(),
			holdername = $("#holdername").val().trim(),
			securitycode = $("#securitycode").val().trim(),
			expirydate = $("#expirydate").val().trim();
			bool = true;
			
			if(!validateInput("card", cardnumber)){
				$("#cardnumber").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("name", holdername)){
				$("#holdername").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("pin", securitycode)){
				$("#securitycode").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("date", expirydate)){
				$("#expirydate").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(bool){
				$(form).submit();
			}
	}
	
	$("#addAccAddressBtn").on("click", function (event){
		event.stopPropagation();
		validateAddressFields("#formAccAddAddress");
	});
	

	
	$(".SendEmail").on("click", function (event){
		event.stopPropagation();
		var name = $("#name").val().trim(),
			email = $("#email").val().trim(),
			message = $("#message").val().trim();
			var bool = true;
			
			if(!validateInput("name", name)){
				$("#name").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("email", email)){
				$("#email").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(message == ""){
				$("#message").attr("placeholder", "Champ obligatoire");
				alert("Vous devez saisir un message.");
				bool=false;
			}
			else if(bool){
				$("#frmContact").submit();
			}
	});
	
	function validateAddressFields(form){
		var city = $("#city").val().trim(),
			civicnumber = $("#civicnumber").val().trim(),
			street = $("#street").val().trim(),
			appartment = $("#appartment").val().trim();
			zipcode = $("#zipcode").val().trim();
			bool = true;
			
			
			if(!validateInput("num", civicnumber)){
				$("#civicnumber").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("name", street)){
				$("#street").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("name", city)){
				$("#city").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if($("#country").prop('selectedIndex') < 1){
				alert("Vous devez selectionner un pays");
				bool=false;
			}
			else if($("#state").prop('selectedIndex') < 1){
				alert("Vous devez selectionner une province");
				bool=false;
			}
			else if(!validateInput("zip", zipcode)){
				$("#zipcode").attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(bool){
				if(appartment == null || appartment == ""){
					$("#appartment").val("0");}
					$(form).submit();

			}
	}

	$("#addAddressBtn").on("click", function (event) {
		event.stopPropagation();
		validateAddressFields("#formAddAddress");
	});
	
	
	$(".modAddressBtn").on("click", function (event) {
		event.stopPropagation();
		var index = $(this).attr("xvalue");

		var city = $("#city" + index).val().trim(),
			civicnumber = $("#civicnumber" + index).val().trim(),
			street = $("#street" + index).val().trim(),
			appartment = $("#appartment" + index).val().trim();
			zipcode = $("#zipcode" + index).val().trim();
			bool = true;
			
			
			if(!validateInput("num", civicnumber)){
				$("#civicnumber" + index).attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("name", street)){
				$("#street" + index).attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(!validateInput("name", city)){
				$("#city" + index).attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if($("#country" + index).prop('selectedIndex') < 1){
				alert("Vous devez selectionner un pays");
				bool=false;
			}
			else if($("#state" + index).prop('selectedIndex') < 1){
				alert("Vous devez selectionner une province");
				bool=false;
			}
			else if(!validateInput("zip", zipcode)){
				$("#zipcode" + index).attr("placeholder", "Champ obligatoire");
				bool=false;
			}
			else if(bool){
				if(appartment == null || appartment == ""){
				$("#appartment" + index).val("0");}
				$("#formModAddress" + index).submit();
				
			}
			
	});
	

	$("#registerBtn").on("click", function (event) {
		event.stopPropagation();
		
		var name = $("#firstname").val().trim();
		var lastname = $("#lastname").val().trim();
		var birthday = $("#birthday").val().trim();
		var email = $("#email").val().trim();
		var pass1 = $("#password1").val().trim();
		var pass2 = $("#password2").val().trim();
		d = new RegExp('[0-9]{4}[-][0-9]{2}[-][0-9]{2}');
		bool = true;
		
		if(!validateInput("name", name)){
			$("#firstname").attr("placeholder", "Champ obligatoire");
			bool=false;
		}
		else if(!validateInput("name", lastname)){
			$("#lastname").attr("placeholder", "Champ obligatoire");
			bool=false;
		}
		else if(!(d.test(birthday))){
			$("#birthday").attr("placeholder", "Champ obligatoire");
			alert("La date fournie n'est pas conforme au format 'yyyy-mm-dd'");
			bool=false;
		}
		else if(!validateInput("email", email)){
			$("#lastname").attr("placeholder", "Champ obligatoire");
			bool=false;
		}
		else if(!validateInput("pass", pass1)){
			bool=false;
		}
		else if(!validateInput("pass", pass2)){
			bool=false;
		}
		else if(bool == true){
	
			if(pass1 != pass2){
				alert("Les mots de passe saisis ne sont pas identiques.")
			}
			else{
				validateAddressFields("#formRegister");
			}
		}
		
	});

	//--------------------------------------------------------------------------------------
	//	name: showProduct
	//	input: none
	//	how: sets the background image of the carousel division
	//	why: Animates Carousel
	//	output: none
	//--------------------------------------------------------------------------------------
	function showProduct() {

		if (index > advertisement.length - 1)
			index = 0;

		if (index < 0)
			index = advertisement.length - 1;

		var src = "url('" + advertisement[index] + "')";

		var dots = $(".dot");
		dots.each(function () { $(this).css("background-color", ""); });
		dots.eq(index).css("background-color", "white");
		$("#Carousel").css("background-image", src).fadeIn();
	}
	
	
	//--------------------------------------------------------------------------------------
	//	name: validateInput
	//	input: name, fname, dob, email, username, password
	//	how: check if input matches pattern
	//	why: to create user
	//	output: boolean
	//--------------------------------------------------------------------------------------
	function validateInput(type, value) {

		var n = new RegExp("^[a-zA-Z\-_ ’'‘ÆÐƎƏƐƔĲŊŒẞÞǷȜæðǝəɛɣĳŋœĸſßþƿȝĄƁÇĐƊĘĦĮƘŁØƠŞȘŢȚŦŲƯY̨Ƴąɓçđɗęħįƙłøơşșţțŧųưy̨ƴÁÀÂÄǍĂĀÃÅǺĄÆǼǢƁĆĊĈČÇĎḌĐƊÐÉÈĖÊËĚĔĒĘẸƎƏƐĠĜǦĞĢƔáàâäǎăāãåǻąæǽǣɓćċĉčçďḍđɗðéèėêëěĕēęẹǝəɛġĝǧğģɣĤḤĦIÍÌİÎÏǏĬĪĨĮỊĲĴĶƘĹĻŁĽĿʼNŃN̈ŇÑŅŊÓÒÔÖǑŎŌÕŐỌØǾƠŒĥḥħıíìiîïǐĭīĩįịĳĵķƙĸĺļłľŀŉńn̈ňñņŋóòôöǒŏōõőọøǿơœŔŘŖŚŜŠŞȘṢẞŤŢṬŦÞÚÙÛÜǓŬŪŨŰŮŲỤƯẂẀŴẄǷÝỲŶŸȲỸƳŹŻŽẒŕřŗſśŝšşșṣßťţṭŧþúùûüǔŭūũűůųụưẃẁŵẅƿýỳŷÿȳỹƴźżžẓ]{2,40}$"),
			e = new RegExp('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,50}$'),
			p = new RegExp('^[A-Za-z0-9 -@&$]{8,15}$'),
			s = new RegExp('[0-9]{3}'),
			num = new RegExp('[0-9]{1,}'),
			d = new RegExp('[0-9]{4}[-][0-9]{2}[-][0-9]{2}'),
			z = new RegExp('[A-Za-z]+[0-9]+[A-Za-z]+[-]+[0-9]+[A-Za-z]+[0-9]'),
			c = new RegExp("[0-9]{4}[-][0-9]{4}[-][0-9]{4}[-][0-9]{4}"),
			u = new RegExp('http(s?)(:\/\/)(?:(?:[a-zA-Z0-9]+-?)*[a-zA-Z0-9]+)(?:\.(?:[a-zA-Z0-9]+-?)*[a-zA-Z0-9]+)*(?:\.(?:[a-zA-Z]{2,}))(?::\d{2,5})?(?:\/[^\s]*)?');

		switch(type){
			
			case "name" : if (!(n.test(value))) {alert("Le nom doit contenir au minimum 2 caracteres.");return false;}break;
			case "pin" : if (!(s.test(value))) {alert("Le code de securite ne peut contenir que 3 chiffres sans espaces.");return false;}break;
			case "num" : if (!(num.test(value))) {alert("Le numero civique ne peut contenir que des chiffres.");return false;}break;
			case "add" : if (!(n.test(value))) {alert("L'adresse doit contenir au minimum 2 caracteres.");return false;}break;
			case "zip" : if (!(z.test(value))) {alert("Le code postal ne correspond pas au format X0X-0X0");return false;}break;
			case "desc" : if (!(value.length > 1 && value.length < 201)) {alert("Le description doit contenir entre 2 et 200 caracteres.");return false;}break;
			case "email":if (!(e.test(value))) {alert("L'adresse courriel n'est pas valide.");return false;}break;
			case "pass":if (!(p.test(value))) {alert("Le mot de passe doit contenir entre 8 et 15 caracteres");return false;}break;
			case "url":if (!(u.test(value))){alert("L'adresse url n'est pas conforme au format demande.");return false;}break;
			case "date":
							if (!(d.test(value))){
								alert("La date fournie n'est pas conforme au format 'yyyy-mm-dd'");
								return false;
							}
							else{
								var d = new Date();
								var str = d.getFullYear() +  "-" + d.getMonth() + "-" + d.getDate();
								var d2 = new Date(str);
								var d1 = new Date(value);
								if(d1 < d2){
									alert("La date d'expiration ne peut etre inferieure a la date d'aujourd'hui");
									return false;
								}
									
							}
							break;
			case "card":if (!(c.test(value))){alert("Le numero de la carte fourni n'est pas valide'");return false;}break;
			default:return false;
		}

		return true;
	}
	
	
	$(".item").on("click", function (event) {
		event.stopPropagation();
		var data = $(this).attr("obj");
		location.replace("Detailed.jsp?Product=" + data);
	});
	
	
	
