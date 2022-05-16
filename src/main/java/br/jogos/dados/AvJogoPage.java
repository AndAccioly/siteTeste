package br.jogos.dados;

import java.util.List;


import br.jogos.modelos.AvJogo;

public class AvJogoPage {
	List<AvJogo> avaliacoes;
	int totalPages;
	
	public AvJogoPage(List<AvJogo> avaliacoes, int totalPages) {
		super();
		this.avaliacoes = avaliacoes;
		this.totalPages = totalPages;
	}
	
	public List<AvJogo> getAvaliacoes() {
		return avaliacoes;
	}
	
	public void setAvaliacoes(List<AvJogo> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
}
