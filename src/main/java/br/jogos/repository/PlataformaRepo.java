package br.jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jogos.modelos.Plataforma;

public interface PlataformaRepo extends JpaRepository<Plataforma, Integer>{

	Plataforma findByNome(String nome);

}
