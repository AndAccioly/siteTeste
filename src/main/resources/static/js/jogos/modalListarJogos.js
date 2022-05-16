var nomeJogo;

$("#modal-adicionar-jogo-salvar").click(function(){
	var horas = $("#horasAvEditar").val();
	var nota = $("#notaAvEditar").val();
	var review = $("#reviewAvEditar").val();
	var nome = nomeJogo;
	enviarAvAdicionar(nome, nota, horas, review);
});

$(".adicionar-jogo").click(function(){
	$('#mensagem-sucesso').text("");
	$('#mensagem-sucesso').hide();
	nomeJogo= $(this).attr("id");
	console.log("clicado " + nomeJogo);
});


function enviarAvAdicionar(nome, notaNova, horas, review){
	console.log("Enviando: Horas " + horas + " nota " + notaNova + " nome " + nome + " review " + review);
	
	$.ajax({
		  type:    "POST",
		  url:     "/adicionarAvJogo",
		  data:    
		  {
			nome: nome,
			review: review,
			nota: notaNova,
			horasJogadas: horas
		  },
		  success: function(data) {
			  var pagina = parseInt(paginaAtual.text())
			  $('#mensagem-sucesso').text("Jogo adicionado com sucesso!");
			  $('#mensagem-sucesso').show();
		  },
		  error:   function(jqXHR, textStatus, errorThrown) {
		        console.log("Error, status = " + textStatus + ", " +
		              "error thrown: " + errorThrown
		        );
		  }
		});
}