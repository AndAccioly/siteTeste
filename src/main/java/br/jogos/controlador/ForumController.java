package br.jogos.controlador;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.jogos.dados.BancoDados;
import br.jogos.modelos.Resposta;
import br.jogos.modelos.Topico;
import br.jogos.repository.JogoRepo;
import br.jogos.repository.RespostaRepo;
import br.jogos.repository.TopicoRepo;
import br.jogos.repository.UsuarioRepo;

@Controller
public class ForumController {

	@Autowired
	TopicoRepo topicoRepo;
	
	@Autowired
	RespostaRepo respostaRepo;
	
	@Autowired
	UsuarioRepo usuarioRepo;
	
	@Autowired
	JogoRepo jogoRepo;
	
	
	@GetMapping("/forum")
	public String forum(Map<String, Object> model) {
		List<Topico> topicosRecentes;
		List<Topico> meusTopicos;		
		int numTopicos = 5;
		int numTopicosRecentes = 15;
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		BancoDados b = new BancoDados();
		
		topicosRecentes = b.topicosRecentes(numTopicosRecentes, topicoRepo);
		meusTopicos = b.bucarTopicosPorUsuario(username, numTopicos, topicoRepo, usuarioRepo);
		
		List<String> jogosNomes = b.buscarTodosJogos(jogoRepo);
		
		model.put("jogosNomes", jogosNomes);
		model.put("topico", new Topico());
		model.put("meusTopicos", meusTopicos);
		model.put("topicosRecentes", topicosRecentes);
		return "forum/forum";
	}
	
	@GetMapping("/topico")
	public String verTopico(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		BancoDados b = new BancoDados();
		Topico topico = b.topicoPorId(id, topicoRepo);
		
		Resposta resposta = new Resposta();
		resposta.setTopico(topico);
		
		
		model.put("respostaNova", resposta);
		model.put("topico", topico);
		return "forum/verTopico";
	}
	
	@GetMapping("/resposta")
	public String verResposta(Map<String, Object> model, @RequestParam(name = "id") Integer id) {
		BancoDados b = new BancoDados();
		Resposta resposta= b.respostaPorId(id, respostaRepo);

		model.put("resposta", resposta);
		return "forum/verResposta";
	}
	
	@PostMapping("/darResposta")
	public String darResposta(Map<String, Object> model, 
			RedirectAttributes redirectAttributes,
			@ModelAttribute Resposta resposta
			) {
		
		System.out.println("Resposta: " + resposta.getConteudo());
		System.out.println("Topico: " + resposta.getTopico().getTitulo());
		Integer id = resposta.getTopico().getId();
		BancoDados b = new BancoDados();
		
		resposta.setAutor(b.buscarUsuario(SecurityContextHolder.getContext().getAuthentication().getName(), usuarioRepo));
		
		resposta.setDataPublicacao(new Timestamp(System.currentTimeMillis()));
		b.adicionarResposta(id, resposta, respostaRepo);
		
		redirectAttributes.addAttribute("id", id);
		System.out.println("redirecionando...");
		return "redirect:/topico";
	}
	
	@PostMapping("/adicionarTopico")
	public String adicionarTopico(RedirectAttributes redirectAttributes, @ModelAttribute Topico topico) {
		BancoDados b = new BancoDados();
		
		topico.setDataPublicacao(new Timestamp(System.currentTimeMillis()));
		topico.setAutor(b.buscarUsuario(SecurityContextHolder.getContext().getAuthentication().getName(), usuarioRepo));
		topico.setJogo(b.buscarJogoPorNome(topico.getJogo().getNome(), jogoRepo).get(0));
		
		b.adicionarTopico(topico, topicoRepo);
		
		
		redirectAttributes.addAttribute("id", topico.getId());
		return "redirect:/topico";
	}	
	
}
