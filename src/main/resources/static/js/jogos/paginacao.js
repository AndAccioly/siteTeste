var carregarPaginaAnterior = $("#carregarPaginaAnterior");
var carregarPaginaProxima = $("#carregarPaginaProxima");
var paginaAtual = $("#valorPaginaAtual");
var ehUltimaPagina = $("#ehUltimaPagina");

var sortUp = $("#sortUpSelector");
var sortDown = $("#sortDownSelector");

var plataforma;

$(function(){ });

carregarPaginaProxima.click(function(){
	var pagina;
	if(ehUltimaPagina.text() != 0){
		pagina = parseInt(paginaAtual.text()) + 1
		carregarPagina(pagina);
	}
});

carregarPaginaAnterior.click(function(){
	var pagina;
	if(parseInt(paginaAtual.text()) != 0){
		pagina = parseInt(paginaAtual.text()) - 1;
		carregarPagina(pagina);
	}
});

function carregarPagina(pagina){
	var ordem = $("#selecionar-ordem").val();
	var categoria = $("#selecionar-categoria");
	var nomeJogo = $("#jogoAutoInput");
	var sort;
	
	ordem = verificarOrdem(ordem);
	plataforma = verificarPlataforma();
	
	if(plataforma === "undefined"){
		return "Tudo";
	}
	
	if($('#sortUpSelector:visible').length == 0) {
		sort = "&sentido=desc"
	} else {
		sort = "&sentido=asc"
	}
	
	var url = "/jogosPlataforma?plataforma=" + plataforma + "&page=" + pagina + sort;
	if(nomeJogo.val() !== ""){
		url = url.concat("&nome=" + nomeJogo.val().split(" ").join("+"));
	}
	
	if(categoria.val() !== "selecioneCategoria"){
		url = url.concat("&categoria=" + categoria.val().split(" ").join("+"));
	}
	
	if(ordem !== "selecioneOrdem"){
		url = url.concat("&ordem=" + ordem);
	}
	
	
	console.log("clicado " + url);
	$('#replace_div').load(url);
}
