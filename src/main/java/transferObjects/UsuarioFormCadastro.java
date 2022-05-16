package transferObjects;

public class UsuarioFormCadastro {
	private String username;
	private String email;
	private String password;
	private String passwordConfirmada;
	
	public UsuarioFormCadastro() {}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirmada() {
		return passwordConfirmada;
	}
	
	public void setPasswordConfirmada(String passwordConfirmada) {
		this.passwordConfirmada = passwordConfirmada;
	}
	
	public String toString() {
		return "EMAIL: " + this.email
				+ "USERNAME: " + this.username
				+ "PASSWORD: " + this.password
				+ "PASSWORD CONFIMADA: " + this.passwordConfirmada;
	}
	
	
	
}
