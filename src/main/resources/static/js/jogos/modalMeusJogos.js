

console.log("Deu");

$("#modal-salvar").click(function(){
	var horas = $("#horasAvEditar").val();
	var nota = $("#notaAvEditar").val();
	var id = idEditar;
	var review = $("#reviewAvEditar").val();
	enviarAvAlterar(id, nota, horas, review);
});


function enviarAvAlterar(id, notaNova, horas, review){
	console.log("Enviando: Horas " + horas + " nota " + notaNova + " id " + id + " review " + review);
	
	$.ajax({
		  type:    "POST",
		  url:     "/alterarAvaliacaoJogo",
		  data:    
		  {
			id: id,
			review: review,
			nota: notaNova,
			horasJogadas: horas
		  },
		  success: function(data) {
			  var pagina = parseInt(paginaAtual.text())
			  $('#mensagem-sucesso').addClass("alert-success");
			  $('#mensagem-sucesso').removeClass("alert-danger");
			  $('#mensagem-sucesso').text("Jogo alterado com sucesso!");
			  $('#mensagem-sucesso').show();
			  $('#replace_div').load("/meusJogosPagina?page=" + pagina);
		  },
		  error:   function() {
		        console.log("Error");
		        $('#mensagem-sucesso').removeClass("alert-success");
				$('#mensagem-sucesso').addClass("alert-danger");
		        $('#mensagem-sucesso').text("Erro ao alterar avaliação!");
				$('#mensagem-sucesso').show();
		  }
		});
}