package br.jogos.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.jogos.modelos.AvJogo;
import br.jogos.modelos.Jogo;

public interface AvJogoRepo extends JpaRepository<AvJogo, Integer>{

	Page<AvJogo> findByUsuarioId(Integer id, Pageable paginacao);

}
