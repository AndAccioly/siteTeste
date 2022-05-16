package br.jogos.dados;

import java.util.List;

import br.jogos.modelos.Jogo;


public class JogoPage {

	List<Jogo> jogos;
	int totalPages;
	
	public JogoPage(List<Jogo> jogos, int totalPages) {
		super();
		this.jogos = jogos;
		this.totalPages = totalPages;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}
	
	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
	
	
}
