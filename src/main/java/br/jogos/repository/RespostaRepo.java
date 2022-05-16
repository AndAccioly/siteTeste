package br.jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jogos.modelos.Jogo;
import br.jogos.modelos.Resposta;

public interface RespostaRepo extends JpaRepository<Resposta, Integer>{

}
