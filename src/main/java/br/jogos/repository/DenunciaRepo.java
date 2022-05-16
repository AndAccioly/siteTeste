package br.jogos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jogos.modelos.Denuncia;

public interface DenunciaRepo extends JpaRepository<Denuncia, Integer>{

	@Query("SELECT d FROM Denuncia d WHERE d.entidadeDenunciado LIKE 'topico' ORDER BY d.dataDenuncia DESC")
	List<Denuncia> buscarPorTopicos();

	@Query("SELECT d FROM Denuncia d WHERE d.entidadeDenunciado LIKE 'resposta' ORDER BY d.dataDenuncia DESC")
	List<Denuncia> buscarPorRespostas();

}
