package br.jogos.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categoria {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	@ManyToMany
	private List<Jogo> jogos = new ArrayList<Jogo>();

	public Categoria() {}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public String toString() {
		return "{Categoria: id=" + id +
				" nome = " + nome +
				"}";
	}
	
}
