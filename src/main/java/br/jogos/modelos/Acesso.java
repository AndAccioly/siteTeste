package br.jogos.modelos;

import java.time.Duration;
import java.time.LocalDateTime;

public class Acesso {
	private String path;
	private LocalDateTime data;
	private Duration duracao;
	
	public Acesso(){}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Duration getDuracao() {
		return duracao;
	}
	
	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
	}
	
	
}
