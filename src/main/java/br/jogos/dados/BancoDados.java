package br.jogos.dados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.jogos.modelos.AvJogo;
import br.jogos.modelos.Categoria;
import br.jogos.modelos.Denuncia;
import br.jogos.modelos.Jogo;
import br.jogos.modelos.Noticia;
import br.jogos.modelos.Plataforma;
import br.jogos.modelos.Resposta;
import br.jogos.modelos.Topico;
import br.jogos.modelos.Usuario;
import br.jogos.repository.AvJogoRepo;
import br.jogos.repository.CategoriaRepo;
import br.jogos.repository.DenunciaRepo;
import br.jogos.repository.JogoRepo;
import br.jogos.repository.NoticiaRepo;
import br.jogos.repository.PlataformaRepo;
import br.jogos.repository.RespostaRepo;
import br.jogos.repository.TopicoRepo;
import br.jogos.repository.UsuarioRepo;
import mensagens.Mensagens;

public class BancoDados {

	public JogoPage lerTodosJogos(JogoRepo jogoRepo, Pageable pageable) {
		System.out.println("Iniciando lerTodosJogos");
		JogoPage jogoPage;
		Page<Jogo> jogos = jogoRepo.findAll(pageable);
		
		jogoPage = new JogoPage(jogos.toList(), jogos.getTotalPages() - 1);
		
		return jogoPage;
	}
	
	public AvJogoPage lerTodosJogosDoUsuario(Pageable paginacao, String username, UsuarioRepo usuarioRepo, AvJogoRepo avJogoRepo){
		System.out.println("Iniciando lerTodosJogosDoUsuario");
		Usuario u = usuarioRepo.findByUsername(username);
		if(u != null) {
			Integer id = u.getId();
			Page<AvJogo> avaliacoes = avJogoRepo.findByUsuarioId(id, paginacao);
			AvJogoPage pages = new AvJogoPage(avaliacoes.toList(), avaliacoes.getTotalPages() - 1);
			
			return pages;
		}
		
		return null;
	}	

	public boolean adicionarAvJogo(String nomeJogo, AvJogo avJogo, String username, JogoRepo jogoRepo, UsuarioRepo usuarioRepo, AvJogoRepo avJogoRepo) {	
		
		System.out.println("Iniciando adicionar jogo " + avJogo.getJogo().formatado());
		
		Usuario u = usuarioRepo.findByUsername(username);		
		Jogo j = jogoRepo.findByNome(nomeJogo);
		
		
		if(j != null && u != null) {
			avJogo.setJogo(j);
			avJogo.setUsuario(u);
			
			u.aumentaHorasJogadas(avJogo.getHorasJogadas());
			u.adicionaAvaliacao(avJogo);
			
			avJogoRepo.save(avJogo);
			usuarioRepo.save(u);
					
		}else {
			return false;
		}
		
		System.out.println("Finalizando adicionar jogo");
		return true;
	}
	
	public boolean alterarAvJogo(int id, String review, Integer nota, Integer horasJogadas, AvJogoRepo avJogoRepo, UsuarioRepo usuarioRepo) {		
		System.out.println("Iniciando alteração");
		AvJogo avJogo = avJogoRepo.findById(id).orElse(null);
		Usuario u = usuarioRepo.findByUsername(avJogo.getUsuario().getUsername());
		if(avJogo != null && u != null) {
			u.diminuiHorasJogadas(avJogo.getHorasJogadas());
			u.aumentaHorasJogadas(horasJogadas);
			
			avJogo.setNota(nota);
			avJogo.setHorasJogadas(horasJogadas);
			avJogo.setReview(review);
			
			avJogoRepo.save(avJogo);
			usuarioRepo.save(u);

		}else {
			return false;
		}
		
		System.out.println("Finalizando alteração");
		return true;
	}
		
	public Mensagens removerAvJogo(Integer idAvJogo, String username, AvJogoRepo avJogoRepo, UsuarioRepo usuarioRepo) {

		System.out.println("Iniciando remoção");
		
		AvJogo av = avJogoRepo.findById(idAvJogo).orElse(null);
		Usuario u = usuarioRepo.findByUsername(username);
		
		if(av!=null && u!=null) {
			if (username.equals(av.getUsuario().getUsername()) ) {
				System.out.println("REMOVENDO JOGO");
				u.diminuiHorasJogadas(av.getHorasJogadas());
				
				avJogoRepo.delete(av);
				usuarioRepo.save(u);
			}else {
				System.out.println("DEU ERRADO");
				return Mensagens.JOGO_NAO_EXISTE;
			}
		}
		
		System.out.println("Finalizando remoção");
			
		return Mensagens.REMOCAO_CONCLUIDA;
	}
	
	public List<Jogo> buscarJogoPorNome(String nome, JogoRepo jogoRepo) {
		System.out.println("Iniciando busca por nome");
		
		List<Jogo> jogos = new ArrayList<Jogo>();
		jogos = jogoRepo.contemNome(nome);
		
		if(jogos.size()>0) {
			System.out.println("JOGOS ENCONTRADOS");
			for(Jogo j : jogos) {
				System.out.println(j.formatado());
			}
		}else {
			System.out.println("Nenhum jogo encontrado");
			
		}

		System.out.println("Finalizando busca por nome");

		return jogos;
	}
	
	public JogoPage buscarJogoPorNome(String nome, JogoRepo jogoRepo, Pageable pageable, Plataforma plat, Categoria cat) {
		System.out.println("Iniciando busca por nome");
		Page<Jogo> jogos;
		JogoPage jogoPage;
		
		if((plat.getNome().equals("Tudo")) && (cat == null) ) {
			jogos = jogoRepo.contemNome(nome, pageable);	
			
		}else if((plat == null || plat.getNome().equals("Tudo")) && (cat != null)) {
			jogos = jogoRepo.contemNomeAndCategoria(nome, cat.getId(), pageable);
		
		} else if(cat == null){
			jogos = jogoRepo.contemNomeAndPlataforma(nome, plat.getId(),pageable);
		} else {
			jogos = jogoRepo.contemNomeAndPlataformaAndCategoria(nome, cat.getId(), plat.getId(),pageable);
		}
		jogoPage = new JogoPage(jogos.toList(), jogos.getTotalPages() - 1);

		return jogoPage;
	}
	
	public AvJogo buscarAvJogoPorId(Integer id, AvJogoRepo avJogoRepo) {
		System.out.println("Iniciando busca de avaliacao por ID");

		AvJogo avJogo = avJogoRepo.findById(id).orElse(null);
		System.out.println("Finalizando busca de avaliacao por ID");

		
		return avJogo;
	}

	public Usuario buscarUsuario(String username, UsuarioRepo usuarioRepo) {
		Usuario u = usuarioRepo.findByUsername(username);
		
		return u;
	}

	public Jogo buscarJogoPorId(Integer id, JogoRepo jogoRepo) {
		System.out.println("Iniciando busca de jogo por ID");

		Jogo jogo = jogoRepo.findById(id).orElse(null);
		System.out.println("Finalizando busca de jogo por ID");
		return jogo;
	}

	public List<Noticia> ultimasNoticias(NoticiaRepo noticiaRepo, int numNoticias) {
		System.out.println("Buscando ultimas noticias.");
		return noticiaRepo.ultimasNoticias(PageRequest.of(0, numNoticias));
	}
	
	public Noticia buscarNoticiaPorId(Integer idNoticia, NoticiaRepo noticiaRepo) {
		System.out.println("Carregando noticia por id " + idNoticia);
		return noticiaRepo.findById(idNoticia).orElse(null);
	}

	public boolean cadastraUsuario(Usuario u, UsuarioRepo usuarioRepo) {
		System.out.println("Cadastrando usuario " + u.getUsername());
		Usuario i = usuarioRepo.save(u);
		
		if(i != null) {
			return true;
		}
		
		return false;
	}

	public List<Topico> topicosRecentes(int numTopicos, TopicoRepo topicoRepo) {
		System.out.println("Carregando topicos mais recentes.");
		return topicoRepo.ultimosTopicos(PageRequest.of(0, numTopicos));
	}

	public List<Topico> bucarTopicosPorUsuario(String username, int numTopicos, TopicoRepo topicoRepo, UsuarioRepo usuarioRepo) {
		System.out.println("Carregando topicos do usuario " + username);
		Usuario u = usuarioRepo.findByUsername(username);
		return topicoRepo.ultimosTopicosUsuario(u.getId(), PageRequest.of(0, numTopicos));
	}

	public Topico topicoPorId(Integer idTopico, TopicoRepo topicoRepo) {
		System.out.println("Carregando topico por id " + idTopico);
		return topicoRepo.findById(idTopico).orElse(null);
	}

	public Resposta respostaPorId(Integer idResposta, RespostaRepo respostaRepo) {
		System.out.println("Carregando resposta por id " + idResposta);
		return respostaRepo.findById(idResposta).orElse(null);
	}

	public void adicionarResposta(Integer idTopico, Resposta resposta, RespostaRepo respostaRepo) {
		
		System.out.println("Adicionando resposta ao banco...");
		
		respostaRepo.save(resposta);
		System.out.println("Resposta adicionada com sucesso");
		
	}

	public List<String> buscarTodosJogos(JogoRepo jogoRepo) {
		System.out.println("Buscando todos os nomes dos jogos.");
		List<String> jogosNomes = new ArrayList<String>();
		List<Jogo> jogos = jogoRepo.findAll();
		
		for(Jogo jogo : jogos) {
			jogosNomes.add(jogo.getNome());
		}
		
		return jogosNomes;
	}

	public void adicionarTopico(Topico topico, TopicoRepo topicoRepo) {
		topicoRepo.save(topico);
	}

	public void adicionarJogo(Jogo jogo, JogoRepo jogoRepo) {
		System.out.println("Adicionando jogo ao banco de dados.");
		jogoRepo.save(jogo);
	}

	public Categoria buscarCategoria(Integer idCategoria, CategoriaRepo categoriaRepo) {
		System.out.println("Carregando categoria por id " + idCategoria);
		return categoriaRepo.findById(idCategoria).orElse(null);
	}

	public List<Categoria> buscarTodasCategorias(CategoriaRepo categoriaRepo) {
		return categoriaRepo.findAll();
	}

	public Categoria buscarCategoriaPorNome(String nome, CategoriaRepo categoriaRepo) {
		System.out.println("Iniciando busca de categoria por nome");
		Categoria categoria = categoriaRepo.findByNome(nome);
		return categoria;
	}

	public void adicionarDenuncia(Denuncia denuncia, DenunciaRepo denunciaRepo) {
		System.out.println("Adicionando denúncia");
		denunciaRepo.save(denuncia);
	}

	public List<Denuncia> buscaTopicosDenunciados(DenunciaRepo denunciaRepo) {
		return denunciaRepo.buscarPorTopicos();
	}

	public List<Denuncia> buscaRespostasDenunciadas(DenunciaRepo denunciaRepo) {
		return denunciaRepo.buscarPorRespostas();
	}

	public Usuario buscarUsuarioPorId(Integer idUsuario, UsuarioRepo usuarioRepo) {
		return usuarioRepo.findById(idUsuario).orElse(null);
	}

	public Denuncia buscarDenunciaPorId(Integer idDenuncia, DenunciaRepo denunciaRepo) {
		return denunciaRepo.findById(idDenuncia).orElse(null);
	}

	public void removerDenuncia(Denuncia denuncia, DenunciaRepo denunciaRepo) {
		System.out.println("Removendo denuncia...");
		denunciaRepo.delete(denuncia);
		System.out.println("Denuncia removida");
	}

	public void removerResposta(Resposta respostaDenunciada, RespostaRepo respostaRepo, DenunciaRepo denunciaRepo) {
		System.out.println("Removendo resposta...");
		for(Denuncia d : respostaDenunciada.getDenuncias()) {
			denunciaRepo.delete(d);
		}
		respostaDenunciada.setDenuncias(null);
		respostaRepo.delete(respostaDenunciada);
		System.out.println("Resposta removida...");
	}

	public void removerTopicoDenunciado(Topico topicoDenunciado, Denuncia denuncia, TopicoRepo topicoRepo, DenunciaRepo denunciaRepo) {
		System.out.println("Removendo topico denunciado...");
		denuncia.setTopicoDenunciado(null);
		denunciaRepo.save(denuncia);
		for(Denuncia d : topicoDenunciado.getDenuncias()) {
			denunciaRepo.delete(d);
		}
		topicoRepo.delete(topicoDenunciado);
		System.out.println("Topico denunciado removido");
	}

	public void removerRespostaDenunciada(Resposta respostaDenunciada, Denuncia denuncia, RespostaRepo respostaRepo, DenunciaRepo denunciaRepo) {
		System.out.println("Removendo resposta denunciado...");
		denuncia.setRespostaDenunciada(null);
		denunciaRepo.save(denuncia);
		for(Denuncia d : respostaDenunciada.getDenuncias()) {
			denunciaRepo.delete(d);
		}
		respostaRepo.delete(respostaDenunciada);
		System.out.println("Resposta denunciado removido");
	}

	public JogoPage buscarJogoPorCategoria(Categoria categoria, JogoRepo jogoRepo, String ordem, Plataforma plat, Pageable pageable) {
		Page<Jogo> jogos;
		JogoPage jogoPage;
		
		if(plat == null || plat.getNome().equals("Tudo")) {
			jogos = jogoRepo.findByCategoriasNome(categoria.getNome(), pageable);
		}else {
			jogos = jogoRepo.contemCategoriaAndPlataforma(categoria.getId(), plat.getId(), pageable);
		}
		
		jogoPage = new JogoPage(jogos.toList(), jogos.getTotalPages() - 1);
		
		return jogoPage;
	}

	public Plataforma buscarPlataformaPorNome(String nome, PlataformaRepo plataformaRepo) {
		System.out.println("Iniciando busca de categoria por nome " + nome);
		return plataformaRepo.findByNome(nome);
	}

	public JogoPage buscarJogoPorPlataforma(int id, JogoRepo jogoRepo, Pageable pageable) {
		System.out.println("Iniciando busca de categoria por nome");
		Page<Jogo> jogos;
		JogoPage jogoPage;
		
		jogos = jogoRepo.findByPlataformasId(id, pageable);
		jogoPage = new JogoPage(jogos.toList(), jogos.getTotalPages() - 1);
		
		return jogoPage;
	}



}
