package br.jogos.controlador;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.interceptor.InterceptadorDeAcesso;
import br.jogos.dados.AvJogoPage;
import br.jogos.dados.BancoDados;
import br.jogos.dados.JogoPage;
import br.jogos.modelos.AvJogo;
import br.jogos.modelos.Categoria;
import br.jogos.modelos.Jogo;
import br.jogos.modelos.Plataforma;
import br.jogos.repository.AvJogoRepo;
import br.jogos.repository.CategoriaRepo;
import br.jogos.repository.JogoRepo;
import br.jogos.repository.NoticiaRepo;
import br.jogos.repository.PlataformaRepo;
import br.jogos.repository.UsuarioRepo;
import mensagens.Mensagens;
import transferObjects.JogoBuscaTO;

@Controller
public class JogosController {
	
	private final int numeroDeJogosPaginacao = 3;
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@Autowired
	private JogoRepo jogoRepo;
	
	@Autowired
	private AvJogoRepo avJogoRepo;
	
	@Autowired
	private NoticiaRepo noticiaRepo;
	
	@Autowired
	private PlataformaRepo plataformaRepo;
	
	@Autowired
	private CategoriaRepo categoriaRepo;

	
	//meusJogos?userId=1&page=0&size=3&sort=nome,ASC
	@GetMapping("/meusJogos")
	//@Cacheable(value="meusJogos")
	public String meusJogos(
			@PageableDefault(sort="jogo.nome", direction=Direction.ASC, page=0, size=numeroDeJogosPaginacao) Pageable pageable,
			Map<String, Object> model) {
		
		BancoDados b = new BancoDados();
		String erro = "";
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<String> jogosNomes = b.buscarTodosJogos(jogoRepo);
		AvJogoPage pages = b.lerTodosJogosDoUsuario(pageable, username, usuarioRepo, avJogoRepo);
		
		List<AvJogo> avaliacoes = pages.getAvaliacoes();
		int ehUltimaPagina = (pageable.getPageNumber() == pages.getTotalPages())? 0 : 1;
		int paginaAtual = pageable.getPageNumber();
		
		model.put("erro", erro);
		model.put("paginaAtual", paginaAtual);
		model.put("ehUltimaPagina", ehUltimaPagina);
		model.put("jogosNomes", jogosNomes);
		model.put("avJogo", new AvJogo());
		model.put("avJogos", avaliacoes);
		
		return "jogos/meusJogos";
	}
	
	@RequestMapping("/meusJogosPagina")
	public String meusJogosTabela(@PageableDefault(sort="jogo.nome", direction=Direction.ASC, page=0, size=numeroDeJogosPaginacao) Pageable pageable,
			@RequestParam(name = "id", required = false) Integer idJogo,
			Map<String, Object> model) {
		
		BancoDados b = new BancoDados();
		System.out.println("Chegando aqui");
		System.out.println(idJogo);
		String erro = "";
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(idJogo != null) {
			Mensagens resp = b.removerAvJogo(idJogo, username, avJogoRepo, usuarioRepo);
			if ( resp == Mensagens.JOGO_NAO_EXISTE ) {
				erro = "Erro ao deletar jogo";
			}
		}
		
		AvJogoPage pages = b.lerTodosJogosDoUsuario(pageable, username, usuarioRepo, avJogoRepo);
		List<AvJogo> avaliacoes = pages.getAvaliacoes();
		int ehUltimaPagina = (pageable.getPageNumber() == pages.getTotalPages())? 0 : 1;
		int paginaAtual = pageable.getPageNumber();
		
		model.put("erro", erro);
		model.put("paginaAtual", paginaAtual);
		model.put("ehUltimaPagina", ehUltimaPagina);
		model.put("avJogo", new AvJogo());
		model.put("avJogos", avaliacoes);
		
		return "jogos/jogosFragments :: meusJogos";
	}
	
	@RequestMapping("/jogosPlataforma")
	public String jogosPlataforma(
			@RequestParam(name="page", required = true) int page,
			@RequestParam(name = "plataforma", required = false) String plataforma,
			@RequestParam(name = "ordem", required = false) String ordem,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "categoria", required = false) String categoria,
			@RequestParam(name = "sentido", required = false) String sentido,
			Map<String, Object> model) {
		
		Pageable pageable;
		String erro = "";
		BancoDados b = new BancoDados();
		JogoPage pages = null;
		List<Jogo> jogos = new ArrayList<Jogo>();

		if(sentido !=null && sentido.equals("desc")) {
			pageable = PageRequest.of(page, numeroDeJogosPaginacao, Sort.by(ordem).descending());
		}else {
			pageable = PageRequest.of(page, numeroDeJogosPaginacao, Sort.by(ordem).ascending());			
		}
		
		Plataforma plat = b.buscarPlataformaPorNome(plataforma, plataformaRepo);
		Categoria cat = b.buscarCategoriaPorNome(categoria, categoriaRepo);
		
		if(nome != null) {
			pages = b.buscarJogoPorNome(nome, jogoRepo, pageable, plat, cat);					
		} else if(categoria != null) {
			pages = b.buscarJogoPorCategoria(cat, jogoRepo, ordem, plat, pageable);
		} else if(!plataforma.equals("Tudo")){
			pages = b.buscarJogoPorPlataforma(plat.getId(), jogoRepo, pageable);
		} else {
			pages = b.lerTodosJogos(jogoRepo, pageable);
		}
	
		jogos = pages.getJogos();
		if(jogos.size() == 0) {
			erro = "Nenhum jogo encontrado";
		}
		
		int ehUltimaPagina = (pageable.getPageNumber() == pages.getTotalPages())? 0 : 1;
		int paginaAtual = pageable.getPageNumber();
		
		model.put("paginaRelativa", paginaAtual*numeroDeJogosPaginacao);
		model.put("erro", erro);
		model.put("paginaAtual", paginaAtual);
		model.put("ehUltimaPagina", ehUltimaPagina);
		model.put("jogos", jogos);
		model.put("jogo", new Jogo());
		return "jogos/jogosFragments :: listaJogos";
	}
	
	//Quando o jogo é pesquisado
	@GetMapping("/listarJogos")
	public String listarJogos(
			@PageableDefault(sort="nome", direction=Direction.ASC, page=0, size=numeroDeJogosPaginacao) Pageable pageable,
			Map<String, Object> model) {

		String erro = "";
		BancoDados b = new BancoDados();
		JogoPage pages = null;
		List<Jogo> jogos = new ArrayList<Jogo>();
		List<Categoria> listaCategorias = new ArrayList<Categoria>();		
		
		listaCategorias = b.buscarTodasCategorias(categoriaRepo);	
		List<String> jogosNomes = b.buscarTodosJogos(jogoRepo);
		pages = b.lerTodosJogos(jogoRepo, pageable);
		
		jogos = pages.getJogos();	
		
		if(jogos.size() == 0) {
			erro = "Nenhum jogo encontrado";
		}	
		
		int ehUltimaPagina = (pageable.getPageNumber() == pages.getTotalPages())? 0 : 1;
		int paginaAtual = pageable.getPageNumber();
		
		model.put("paginaRelativa", paginaAtual*numeroDeJogosPaginacao);
		model.put("jogoBuscaTO", new JogoBuscaTO());
		model.put("paginaAtual", paginaAtual);
		model.put("ehUltimaPagina", ehUltimaPagina);
		model.put("jogosNomes", jogosNomes);
		model.put("listaCategorias", listaCategorias);
		model.put("jogo", new Jogo());
		model.put("jogos", jogos);
		model.put("erro", erro);
		return "jogos/verJogos";

	}
	
	//@RequestMapping(value="/alterarAvaliacaoJogo", method=RequestMethod.POST,  consumes=MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/alterarAvaliacaoJogo")
	public String editarJogo(
			@RequestParam(name = "id", required = false) Integer idAv,
			@RequestParam(name = "review", required = false) String review,
			@RequestParam(name = "nota", required = false) Integer nota,
			@RequestParam(name = "horasJogadas", required = false) Integer horasJogadas,
			Map<String, Object> model) {
		
		if(review.equals("a")) {
			return "";
		}
		System.out.println("Cheguei");
		BancoDados b = new BancoDados();
		b.alterarAvJogo(idAv, review, nota, horasJogadas, avJogoRepo, usuarioRepo);

		return "redirect:/meusJogos";
	}
	
	@PostMapping("/adicionarAJogo")
	//@CacheEvict(value="meusJogos", allEntries=true)
	public String adicionarJogo(@ModelAttribute AvJogo avJogo, Map<String, Object> model) {
		
		String erro = "";
		BancoDados b = new BancoDados();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = b.adicionarAvJogo(avJogo.getJogo().getNome(), avJogo, username,jogoRepo, usuarioRepo, avJogoRepo);
		
		if(!result) {
			erro = "Jogo não encontrado";
		}
		
		return "redirect:/meusJogos";
		
	}
	
	@PostMapping("/adicionarAvJogo")
	public String adicionarAvJogo(
			@RequestParam(name = "nome", required = false) String nomeJogo,
			@RequestParam(name = "review", required = false) String review,
			@RequestParam(name = "nota", required = false) Integer nota,
			@RequestParam(name = "horasJogadas", required = false) Integer horasJogadas,
			Map<String, Object> model) {
		
		String erro = "";
		System.out.println("Chegando aqui");
		AvJogo avJogo = new AvJogo(nota, review, horasJogadas);
		BancoDados b = new BancoDados();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = b.adicionarAvJogo(nomeJogo, avJogo, username, jogoRepo, usuarioRepo, avJogoRepo);
		
		if(!result) {
			erro = "Jogo não encontrado";
		}
		
		return "redirect:/meusJogos";
		
	}
	
	
	@GetMapping("/mostrarJogoParaAlterar")
	public String mostrarJogoParaAlterar(
			@RequestParam(name = "id") Integer id,
			Map<String, Object> model) {
		
		String erro = "";
		
		BancoDados b = new BancoDados();
		AvJogo avJogo = b.buscarAvJogoPorId(id, avJogoRepo);
		
		if(avJogo == null) {
			erro = "Jogo não encontrado.";
		}
		model.put("avJogo", avJogo);
		model.put("erro", erro);
		
		return "jogos/mostrarJogoParaAlterar";
		
	}
	
	@GetMapping("/verAvaliacao")
	public String verAvaliacao(
			@RequestParam(name = "id") Integer id,
			Map<String, Object> model) {

		String erro = "";
		AvJogo av = new AvJogo();
		BancoDados b = new BancoDados();
		av = b.buscarAvJogoPorId(id, avJogoRepo);

		if(av == null) {
			erro = "Nenhuma avaliação encontrada";
		}
		
		model.put("avaliacao", av);
		model.put("erro", erro);
		return "paginas/verAvaliacao";
	}
	
	@GetMapping("/verJogo")
	public String verJogo(
			@RequestParam(name = "id") Integer id,
			Map<String, Object> model) {

		String erro = "";

		Jogo jogo = new Jogo();
		BancoDados b = new BancoDados();
		jogo = b.buscarJogoPorId(id, jogoRepo);

		if(jogo.getAvaliacoes().size() == 0) {
			erro = "Nenhuma avaliação encontrada";
		}
		
		//model.put("categoria", new Categoria());
		model.put("jogo", jogo);
		model.put("erro", erro);
		return "jogos/verJogo";
	}
	
	@PostMapping("/adicionarJogoBD")
	public String adicionarJogoBD(@ModelAttribute Jogo jogo, Map<String, Object> model) {
		BancoDados b = new BancoDados();
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		for(Categoria c : jogo.getCategorias()) {
			Categoria catBuscada = b.buscarCategoriaPorNome(c.getNome(), categoriaRepo);
			categorias.add(catBuscada);
		}

		jogo.setCategorias(categorias);
		
		b.adicionarJogo(jogo, jogoRepo);
		
		return "redirect:/adminJogo";
	}
	
	@GetMapping("/sobre")
	public String sobre(Map<String, Object> model) {
		
		model.put("avJogoEditar", new AvJogo());
		return "paginas/sobre";
	}
	
	@GetMapping("/contato")
	public String contato(Map<String, Object> model) {
		
		return "paginas/contato";
	}
	
	@GetMapping("/acessos")
	public String getAcessos(Map<String, Object> model){
		model.put("acessos", InterceptadorDeAcesso.acessos);
		return "paginas/acessos";
	}	
	
}
