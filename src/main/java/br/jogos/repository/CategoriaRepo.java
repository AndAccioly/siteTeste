package br.jogos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jogos.modelos.Categoria;
import br.jogos.modelos.Jogo;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer>{

	public Categoria findByNome(String nome);


}
