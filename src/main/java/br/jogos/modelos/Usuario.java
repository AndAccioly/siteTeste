package br.jogos.modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class Usuario implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
	
	private String email;
	
	private Integer totalHorasJogadas;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Usuario_Jogos",
			joinColumns = {@JoinColumn(name="usuario_id")},
			inverseJoinColumns = {@JoinColumn(name="jogos_id")}
		)
	private List<Jogo> jogos = new ArrayList<Jogo>();
	
	@OneToMany(mappedBy = "usuario")
	private List<AvJogo> avaliacoes = new ArrayList<AvJogo>();
	
	@OneToMany(mappedBy = "autor")
	private List<Noticia> noticias = new ArrayList<Noticia>();
	
	@OneToMany(mappedBy = "autor")
	private List<Topico> topicos = new ArrayList<Topico>();
	
	@OneToMany(mappedBy = "autor")
	private List<Resposta> respotas = new ArrayList<Resposta>();
	
	@OneToMany(mappedBy = "autorDenuncia")
	private List<Denuncia> denuncias = new ArrayList<Denuncia>();
	
	
	public Usuario() {}
	
	public Usuario(Integer id, String nome, String password) {
		this.id = id;
		this.username = nome;
		this.password = password;
	}
	
	public Usuario(String nome, String password) {
		this.username = nome;
		this.password = password;
	}
	
	public Usuario(String email, String username, String password, Integer totalHorasJogadas) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.totalHorasJogadas = totalHorasJogadas;
	}
	
	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
		
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTotalHorasJogadas() {
		return totalHorasJogadas;
	}

	public void setTotalHorasJogadas(Integer totalHorasJogadas) {
		this.totalHorasJogadas = totalHorasJogadas;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<AvJogo> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvJogo> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	public void adicionaAvaliacao(AvJogo avJogo) {
		this.avaliacoes.add(avJogo);
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public List<Resposta> getRespotas() {
		return respotas;
	}

	public void setRespotas(List<Resposta> respotas) {
		this.respotas = respotas;
	}

	public String formatado() {
		return "id = " + id
				+ " username = " + username
				+ " password = " + password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public void aumentaHorasJogadas(Integer horasJogadas) {
		this.totalHorasJogadas += horasJogadas;
	}

	public void diminuiHorasJogadas(Integer horasJogadas) {
		this.totalHorasJogadas -= horasJogadas;		
	}
}
