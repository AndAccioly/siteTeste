package br.jogos.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Jogo {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String descricao;
	
	@Column(precision=4, scale=2)
	private BigDecimal nota;
	//private String imgUrl;
	private Integer horasZeramento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dtLancamento;
	
	private String publisher;
	
	@ManyToMany (mappedBy="jogos", fetch = FetchType.LAZY)
	List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@ManyToMany
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@ManyToMany
	private List<Console> consoles = new ArrayList<Console>();
	
	@ManyToMany
	private List<Personagem> personagems = new ArrayList<Personagem>();
	
	@ManyToMany
	private List<Plataforma> plataformas = new ArrayList<Plataforma>();
	
	@OneToMany(mappedBy = "jogo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AvJogo> avaliacoes = new ArrayList<AvJogo>();
	
	@OneToMany(mappedBy = "jogo")
	private List<Topico> topicos = new ArrayList<Topico>();
	
	@OneToMany(mappedBy = "jogo")
	private List<Noticia> noticias = new ArrayList<Noticia>();
	
	@ManyToOne
	private Serie serie;
	
	public Jogo(){
		
	}
	
	public Jogo(String nome) {
		this.nome = nome;
	}
	
	public Jogo(Integer id, String nome, BigDecimal nota, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.nota = nota;
	}
	
	public Jogo(String nome, String descricao, BigDecimal nota) {
		this.nome = nome;
		this.descricao = descricao;
		this.nota = nota;
	}
	
	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<AvJogo> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvJogo> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}	
	
	public Integer getHorasZeramento() {
		return horasZeramento;
	}

	public void setHorasZeramento(Integer horasZeramento) {
		this.horasZeramento = horasZeramento;
	}

	public Calendar getDtLancamento() {
		return dtLancamento;
	}

	public void setDtLancamento(Calendar dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public List<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	public List<Console> getConsoles() {
		return consoles;
	}

	public void setConsoles(List<Console> consoles) {
		this.consoles = consoles;
	}

	public List<Personagem> getPersonagems() {
		return personagems;
	}

	public void setPersonagems(List<Personagem> personagems) {
		this.personagems = personagems;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public String toString() {
		String s = "{Jogo: id=" + id +
				" nome = " + nome +
				" descricao = " + descricao +
				" nota = " + nota + " categorias = [ ";
		for (Categoria c : categorias) {
			s += c.toString();
		}
		s += " ]}";
		
		return s;
	}

	public String formatado() {
		
		return id + ";" + nome + ";" + descricao + ";" + nota + ";";
	}
}
