function verificarPlataforma(){
	var tudoLabel = $("#lbTudoSelector");
	var pcLabel = $("#lbPcSelector");
	var xboxLabel = $("#lbXboxSelector");
	var playLabel = $("#lbPsSelector");
	var nintendoLabel = $("#lbNintendoSelector");
	var mobileLabel = $("#lbMobileSelector");
	
	if(pcLabel.hasClass("active")){
		return "PC";
	}else if(playLabel.hasClass("active")){
		return "Playstation";
	}else if(tudoLabel.hasClass("active")){
		return "Tudo";
	}else if(xboxLabel.hasClass("active")){
		return "Xbox";
	}else if(nintendoLabel.hasClass("active")){
		return "Nintendo";
	}else if(mobileLabel.hasClass("active")){
		return "Mobile";
	}
}

function alterarSelectorPlataforma(plataforma){
	var pcLabel = $("#lbPcSelector");
	var playLabel = $("#lbPsSelector");
	var xboxLabel = $("#lbXboxSelector");
	var nintendoLabel = $("#lbNintendoSelector");
	var mobileLabel = $("#lbMobileSelector");
	var tudoLabel = $("#lbTudoSelector");

	if(pcLabel.hasClass("active")){
		pcLabel.removeClass("active");
	}else if(playLabel.hasClass("active")){
		playLabel.removeClass("active");
	}else if(xboxLabel.hasClass("active")){
		xboxLabel.removeClass("active");
	}else if(nintendoLabel.hasClass("active")){
		nintendoLabel.removeClass("active");
	}else if(mobileLabel.hasClass("active")){
		mobileLabel.removeClass("active");
	}else if(tudoLabel.hasClass("active")){
		tudoLabel.removeClass("active");
	}
	
	if(plataforma === "Xbox"){
		xboxLabel.addClass('active');
		
	} else if (plataforma === "PC"){
		pcLabel.addClass('active');
		
	} else if (plataforma === "Tudo"){
		tudoLabel.addClass('active');
		
	}else if(plataforma === "Playstation"){
		playLabel.addClass('active');
	
	}else if(plataforma === "Nintendo"){
		nintendoLabel.addClass('active');
		
	}else if(plataforma === "Mobile"){
		mobileLabel.addClass('active');
	}
}

function verificarOrdem(ordem){
	console.log("Verificando ordem");
	var alfabeticaLabel = $("#lbAlfabeticaSelector");
	var melhoresLabel = $("#lbMelhoresSelector");
	var lancamentosLabel = $("#lbLancamentosSelector");
	var popularesLabel = $("#lbPopularesSelector");
	
	if(melhoresLabel.hasClass("active")) {
		return "nota";
	} else if(lancamentosLabel.hasClass("active")) {
		return "dtLancamento";
	} else if(popularesLabel.hasClass("active")){
		//return "popularidade";
		return "nome";
	} else if(alfabeticaLabel.hasClass("active")){
		return "nome";
	} else {
		return "nome"
	}
}