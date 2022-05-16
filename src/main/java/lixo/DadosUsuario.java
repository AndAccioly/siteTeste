package lixo;

import java.util.ArrayList;
import java.util.List;

import br.jogos.modelos.Usuario;

public class DadosUsuario {
	
	List <Usuario> usuarios;
	
	public DadosUsuario() {
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario(121, "Joao de teste", "1"));
		usuarios.add(new Usuario(122, "Andre", "2"));
		usuarios.add(new Usuario(123, "Testeonilson", "3"));
		usuarios.add(new Usuario(124, "Testerson", "4"));
	}
	
	public boolean autenticarUsuario(Usuario usuario) {
		for(Usuario c : usuarios) {
			if(c.getUsername().equals(usuario.getUsername())) {
				return true;
			}
		}
		return false;
	}
}
