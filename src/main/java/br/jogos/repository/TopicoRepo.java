package br.jogos.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jogos.modelos.Noticia;
import br.jogos.modelos.Topico;

public interface TopicoRepo extends JpaRepository<Topico, Integer>{

	@Query("SELECT t FROM Topico t ORDER BY t.dataPublicacao DESC")
	public List<Topico> ultimosTopicos(Pageable pageable);

	@Query("SELECT t FROM Topico t WHERE t.autor.id LIKE :pidusuario ORDER BY t.dataPublicacao DESC")
	public List<Topico> ultimosTopicosUsuario(@Param("pidusuario") Integer idUsuario, Pageable pageable);
}
