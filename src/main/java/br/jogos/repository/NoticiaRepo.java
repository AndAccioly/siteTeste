package br.jogos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jogos.modelos.Noticia;
import br.jogos.modelos.Usuario;

public interface NoticiaRepo extends JpaRepository<Noticia, Integer>{
	
	@Query("SELECT n FROM Noticia n ORDER BY n.dataPublicacao DESC")
	public List<Noticia> ultimasNoticias(Pageable pageable);
}
