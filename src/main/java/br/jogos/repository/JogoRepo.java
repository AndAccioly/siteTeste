package br.jogos.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jogos.modelos.Jogo;

public interface JogoRepo extends JpaRepository<Jogo, Integer>{
	String teste = " ORDER BY (CASE WHEN LOWER(j.nome) LIKE LOWER(concat(:pnome, '%')) THEN 1 ELSE 2 END)";
	
	@Cacheable("jogosPorUsuario")
	public Page<Jogo> findByUsuariosId(Integer i, Pageable paginacao);
	public Jogo findByNome(String nome);
	public Page<Jogo> findByCategoriasNome(String categoria, Pageable pageable);
	public Page<Jogo> findByPlataformasId(int id, Pageable pageable);
	
	@Query("SELECT j FROM Jogo j JOIN j.categorias c JOIN j.plataformas p WHERE c.id = :pCatId AND p.id = :pPlatId")
	public Page<Jogo> contemCategoriaAndPlataforma(@Param("pCatId") int catId, @Param("pPlatId") int pPlatId,Pageable pageable);
	
	@Query("SELECT j from Jogo j WHERE LOWER(j.nome) LIKE LOWER(concat('%', :pnome, '%'))" + teste)
	public Page<Jogo> contemNome(@Param("pnome") String nome, Pageable pageable);
	
	@Query("SELECT j from Jogo j WHERE LOWER(j.nome) LIKE LOWER(concat('%', :pnome, '%'))" + teste)
	public List<Jogo> contemNome(@Param("pnome") String nome);

	@Query("SELECT j FROM Jogo j JOIN j.plataformas p WHERE LOWER(j.nome) LIKE LOWER(concat('%', :pnome, '%')) AND p.id = :pPlatId" + teste)
	public Page<Jogo> contemNomeAndPlataforma(@Param("pnome") String nome, @Param("pPlatId") int id, Pageable pageable);
	
	@Query("SELECT j FROM Jogo j JOIN j.categorias c WHERE LOWER(j.nome) LIKE LOWER(concat('%', :pnome, '%')) AND c.id = :pCatId" + teste)
	public Page<Jogo> contemNomeAndCategoria(@Param("pnome") String nome, @Param("pCatId") int catId, Pageable pageable);

	@Query("SELECT j FROM Jogo j JOIN j.categorias c JOIN j.plataformas p WHERE LOWER(j.nome) LIKE LOWER(concat('%', :pnome, '%')) AND c.id = :pCatId AND p.id = :pPlatId" + teste)
	public Page<Jogo> contemNomeAndPlataformaAndCategoria(@Param("pnome") String nome, @Param("pCatId") int catId, @Param("pPlatId") int platId, Pageable pageable);
}
