package br.jogos.modelos;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Denuncia {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String entidadeDenunciado;
	private Timestamp dataDenuncia;
	
	@ManyToOne
	private Topico topicoDenunciado;
	
	@ManyToOne
	private Resposta respostaDenunciada;
	
	@ManyToOne
	private Usuario autorDenuncia;
	
	public Denuncia() {}
	
	public Denuncia(Integer idDenunciado, String entidadeDenunciado) {
		this.entidadeDenunciado = entidadeDenunciado;	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntidadeDenunciado() {
		return entidadeDenunciado;
	}

	public void setEntidadeDenunciado(String entidadeDenunciado) {
		this.entidadeDenunciado = entidadeDenunciado;
	}

	public Timestamp getDataDenuncia() {
		return dataDenuncia;
	}

	public void setDataDenuncia(Timestamp dataDenuncia) {
		this.dataDenuncia = dataDenuncia;
	}

	public Usuario getAutorDenuncia() {
		return autorDenuncia;
	}

	public void setAutorDenuncia(Usuario autorDenuncia) {
		this.autorDenuncia = autorDenuncia;
	}

	public Topico getTopicoDenunciado() {
		return topicoDenunciado;
	}

	public void setTopicoDenunciado(Topico topicoDenunciado) {
		this.topicoDenunciado = topicoDenunciado;
	}

	public Resposta getRespostaDenunciada() {
		return respostaDenunciada;
	}

	public void setRespostaDenunciada(Resposta respostaDenunciada) {
		this.respostaDenunciada = respostaDenunciada;
	}	
	
	
	
	
}
