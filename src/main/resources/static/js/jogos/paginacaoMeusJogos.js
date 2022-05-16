var carregarPaginaAnterior = $("#carregarPaginaAnterior");
var carregarPaginaProxima = $("#carregarPaginaProxima");
var paginaAtual = $("#valorPaginaAtual");
var ehUltimaPagina = $("#ehUltimaPagina");
var botaoRemover = $(".botao-remover");
var botaoEditar = $(".botao-editar");
var idEditar;

$(function(){ });

carregarPaginaProxima.click(function(){
	var pagina;
	if(ehUltimaPagina.text() != 0){
		pagina = parseInt(paginaAtual.text()) + 1;
		var id = "";
		carregarPagina(pagina, id);
	}
});

carregarPaginaAnterior.click(function(){
	var pagina;
	if(paginaAtual.text() != 0){
		pagina = parseInt(paginaAtual.text()) - 1;
		var id = "";
		carregarPagina(pagina, id);
	}
});

botaoRemover.click(function(){
	var idRemover = $(this).attr("id").replace("remover", "");
	carregarPagina(parseInt(paginaAtual.text()), idRemover);
});

botaoEditar.click(function(){
	$('#mensagem-sucesso').text("");
	$('#mensagem-sucesso').hide();
	idEditar = $(this).attr("id").replace("editar", "");
	console.log("clicar " + idEditar);
});


function carregarPagina(pagina, idRemover){
	var url = "/meusJogosPagina?page=" + pagina;
	if(idRemover !== ""){
		url = url + "&id=" + idRemover;
	}
	console.log("clicado " + url);
	$('#replace_div').load(url);
}
