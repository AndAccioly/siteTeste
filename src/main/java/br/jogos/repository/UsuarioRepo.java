package br.jogos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jogos.modelos.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
	
	Usuario findByUsername(String username);

	//@Query("SELECT jogo_id FROM Usuario_Jogos WHERE usuario_id = pid "
			//+ "WHERE id IN (SELECT jogos_id FROM Usuario_Jogos uj WHERE uj.usuario_id = :pid)"
			//)
	//public Page<Jogo> jogosDoUsuario(@Param("pid") Integer id, Pageable paginacao);
}

  