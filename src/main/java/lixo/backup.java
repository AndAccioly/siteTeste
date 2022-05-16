//package br.jogos.modelos;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//
//import br.jogos.modelos.Jogo;
//
//@Entity
////@Table(name="users")
//public class Usuario{
////public class Usuario implements UserDetails{
//	
//	private static final long serialVersionUID = 1L;
//
//	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	private String nome;
//	private String senha;
//	
////	@ManyToMany(fetch = FetchType.EAGER)
////	private List<Perfil> perfis = new ArrayList<>();
//	
//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(
//			name = "Usuario_Jogos",
//			joinColumns = {@JoinColumn(name="usuario_id")},
//			inverseJoinColumns = {@JoinColumn(name="jogos_id")}
//		)
//	private List<Jogo> jogos = new ArrayList<Jogo>();
//	
//	
//	public Usuario() {
//
//	}
//	
//	public Usuario(Integer id, String nome, String senha) {
//		this.nome = nome;
//		this.id = id;
//		this.senha = senha;
//	}
//	
//	public Usuario(String nome, String senha) {
//		this.nome = nome;
//		this.senha = senha;
//	}
//	
//	public List<Jogo> getJogos() {
//		return jogos;
//	}
//
//	public void setJogos(List<Jogo> jogos) {
//		this.jogos = jogos;
//	}
//	
//	public String getNome() {
//		return this.nome;
//	}
//	
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//	
//	public Integer getId() {
//		return this.id;
//	}
//	
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	
//	public String getSenha() {
//		return this.senha;
//	}
//	
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//	
//	public String formatado() {
//		return "id = " + id
//				+ " nome = " + nome
//				+ " senha = " + senha;
//	}
//}
