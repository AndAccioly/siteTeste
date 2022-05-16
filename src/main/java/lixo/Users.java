//package br.jogos.modelos;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Entity
//@Table(name="users")
//public class Users implements UserDetails{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
//	Integer id;
//	
//	@Id
//	@Column(nullable = false)
//	private String username;
//	
//	@Column(nullable = false)
//	private String password;
//	
//	@Column(nullable = false)
//	private boolean enabled;
//	
//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(
//			name = "Users_Jogos",
//			joinColumns = {@JoinColumn(name="user_id")},
//			inverseJoinColumns = {@JoinColumn(name="jogos_id")}
//		)
//	private List<Jogo> jogos = new ArrayList<Jogo>();
//	
//	public Users() {}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public static Object builder() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//}
