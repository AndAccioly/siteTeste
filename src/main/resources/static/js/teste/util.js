var sucesso = $("#mensagem-sucesso");



//$("#modalEx").on('shown.bs.modal', function () {
//});

$("#modal-salvar").click(function(){
	var horas = $("#horasAvEditar").val();
	var nota = $("#notaAvEditar").val();
	var id = "";
	var review = "";
	console.log("Horas " + horas + " nota " + nota + " id " + id + " review " + review);
	enviar(id, nota, horas, review);
	
	
	
//	requisicao post
});


function enviar(id, notaNova, horas, review){
	console.log("Enviando...");
	$.post("/alterarAvaliacaoJogo",
			{
		id: 1,
		review: "Duckburg",
		nota: notaNova,
		horasJogadas: horas
			},
			function(data, status){
				console.log("Data: " + data + "\nStatus: " + status);
				
			});
}