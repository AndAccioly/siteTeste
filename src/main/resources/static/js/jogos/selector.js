var tudoSelector = $("#tudoSelector");
var pcSelector = $("#pcSelector");
var xboxSelector = $("#xboxSelector");
var playSelector = $("#psSelector");
var nintendoSelector = $("#nintendoSelector");
var mobileSelector = $("#mobileSelector");

var pcLabel = $("#lbPcSelector");
var playLabel = $("#lbPsSelector");
var xboxLabel = $("#lbXboxSelector");
var nintendoLabel = $("#lbNintendoSelector");
var mobileLabel = $("#lbMobileSelector");
var tudoLabel = $("#lbTudoSelector");

var alfabeticaLabel = $("#lbAlfabeticaSelector");
var melhoresLabel = $("#lbMelhoresSelector");
var lancamentosLabel = $("#lbLancamentosSelector");
var popularesLabel = $("#lbPopularesSelector");

var alfabeticaSelector = $("#alfabeticaSelector");
var melhoresSelector = $("#melhoresSelector");
var lancamentosSelector = $("#lancamentosSelector");
var popularesSelector = $("#popularesSelector");

var categoriaListener = $("#selecionar-categoria");
var nomeJogoListener = $("#jogoAutoInput");
var sortUp = $("#sortUpSelector");
var sortDown = $("#sortDownSelector");

$(function(){ 
	sortDown.hide();
});

sortUp.click(function(){
	sortUp.hide();
	sortDown.show();
	montarUrl(verificarPlataforma());
});

sortDown.click(function(){
	sortUp.show();
	sortDown.hide();
	montarUrl(verificarPlataforma());
});

categoriaListener.on("change", function(){
	var p = verificarPlataforma();
	montarUrl(p);
});

nomeJogoListener.on("change", function(){
	var p = verificarPlataforma();
	montarUrl(p);
});

alfabeticaSelector.click(function() {
	console.log("alfabetico");
	alfabeticaLabel.addClass("active");
	melhoresLabel.removeClass("active");
	lancamentosLabel.removeClass("active");
	popularesLabel.removeClass("active");
	
	montarUrl(verificarPlataforma());
});

melhoresSelector.click(function() {
	console.log("melhores");
	alfabeticaLabel.removeClass("active");
	melhoresLabel.addClass("active");
	lancamentosLabel.removeClass("active");
	popularesLabel.removeClass("active");
	
	montarUrl(verificarPlataforma());
});

lancamentosSelector.click(function() {
	console.log("lancamentos");
	alfabeticaLabel.removeClass("active");
	melhoresLabel.removeClass("active");
	lancamentosLabel.addClass("active");
	popularesLabel.removeClass("active");
	
	montarUrl(verificarPlataforma());
});

popularesSelector.click(function() {
	console.log("populares");
	alfabeticaLabel.removeClass("active");
	melhoresLabel.removeClass("active");
	lancamentosLabel.removeClass("active");
	popularesLabel.addClass("active");
	
	montarUrl(verificarPlataforma());
});


tudoSelector.click(function(){
	montarUrl("Tudo");
});

xboxSelector.click(function(){
	montarUrl("Xbox");
});

pcSelector.click(function(){
	montarUrl("PC");
});

playSelector.click(function(){
	montarUrl("Playstation");
});

nintendoSelector.click(function(){
	montarUrl("Nintendo");
});

mobileSelector.click(function(){
	montarUrl("Mobile");
});



function montarUrl(plataforma){
	console.log("Montando url");
	var categoria = $("#selecionar-categoria");
	var nomeJogo = $("#jogoAutoInput");
	var sort;
	var ordem;
	
	alterarSelectorPlataforma(plataforma);
	ordem = verificarOrdem(ordem);
	
	if(plataforma === "undefined"){
		plataforma = "Tudo";
	}
	
	if($('#sortUpSelector:visible').length == 0) {
		sort = "&sentido=desc"
	} else {
		sort = "&sentido=asc"
	}
	
	
	url = "/jogosPlataforma?page=0&plataforma=" + plataforma + sort;
	if(nomeJogo.val()!==""){
		url = url.concat("&nome=" + nomeJogo.val().split(" ").join("+"));
	}
	if(categoria.val()!=="selecioneCategoria"){
		url = url.concat("&categoria=" + categoria.val().split(" ").join("+"));
	}
	if(ordem !=="selecioneOrdem"){
		url = url.concat("&ordem=" + ordem);
	}
	
	console.log("clicado aqui " + url);
	$('#replace_div').load(url);
}




