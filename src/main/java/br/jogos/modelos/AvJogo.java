package br.jogos.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AvJogo {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne 
	private Jogo jogo;

	private Integer nota;
	private String review;
	private Integer horasJogadas;	
	
	public AvJogo() {}


	public AvJogo(Integer nota, String review, Integer horasJogadas) {
		this.nota = nota;
		this.review = review;
		this.horasJogadas = horasJogadas;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Integer getNota() {
		return nota;
	}


	public void setNota(Integer nota) {
		this.nota = nota;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public Integer getHorasJogadas() {
		return horasJogadas;
	}


	public void setHorasJogadas(Integer horasJogadas) {
		this.horasJogadas = horasJogadas;
	}
	
	
}
