package br.jogos.modelos;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Resposta {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	private Timestamp dataPublicacao;
	
	@ManyToOne
	private Topico topico;
	
	@ManyToOne
	private Usuario autor;
	
	@OneToMany(mappedBy = "respostaDenunciada")
	private List<Denuncia> denuncias;
	
	public Resposta() {}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Timestamp getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Timestamp dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Denuncia> getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}
	
	
	
	
	
}
