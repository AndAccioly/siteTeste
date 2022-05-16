package br.jogos.controlador;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.jogos.dados.BancoDados;
import br.jogos.modelos.Categoria;
import br.jogos.modelos.Denuncia;
import br.jogos.modelos.Jogo;
import br.jogos.modelos.Resposta;
import br.jogos.modelos.Topico;
import br.jogos.modelos.Usuario;
import br.jogos.repository.CategoriaRepo;
import br.jogos.repository.DenunciaRepo;
import br.jogos.repository.JogoRepo;
import br.jogos.repository.RespostaRepo;
import br.jogos.repository.TopicoRepo;
import br.jogos.repository.UsuarioRepo;

@Controller
public class AdminController {
	
	@Autowired
	JogoRepo jogoRepo;
	
	@Autowired
	CategoriaRepo categoriaRepo;
	
	@Autowired
	DenunciaRepo denunciaRepo;
	
	@Autowired
	TopicoRepo topicoRepo;
	
	@Autowired
	UsuarioRepo usuarioRepo;
	
	@Autowired
	RespostaRepo respostaRepo;
	
	@GetMapping("/admin")
	public String admin(Map<String, Object> model) {
		
		return "admin/admin";
	}
	
	@GetMapping("/adminUsuario")
	public String adminUsuario(Map<String, Object> model) {
		
		return "admin/adminUsuario";
	}
	
	@GetMapping("/adminJogo")
	public String adminJogo(Map<String, Object> model) {
		List<Categoria> listaCategorias = new ArrayList<Categoria>();

		BancoDados b = new BancoDados();
		listaCategorias = b.buscarTodasCategorias(categoriaRepo);
		
		model.put("categoria", new Categoria());
		model.put("listaCategorias", listaCategorias);
		model.put("jogo", new Jogo());
		model.put("cat", new Categoria());
		return "admin/adminJogo";
	}
	
	@GetMapping("/adminForum")
	public String adminForum(Map<String, Object> model) {
		
		return "admin/adminForum";
	}
	
	@GetMapping("/denunciar")
	public String denunciar(Map<String, Object> model,
			@RequestParam(name = "id") Integer id,
			@RequestParam(name = "entidade") String entidade) {
		
		BancoDados b = new BancoDados();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = b.buscarUsuario(username, usuarioRepo);
		
		Denuncia denuncia = new Denuncia();
		
		if(entidade.equals("topico")) {
			Topico t = b.topicoPorId(id, topicoRepo);
			denuncia.setTopicoDenunciado(t);
		}else if(entidade.equals("resposta")) {
			Resposta r = b.respostaPorId(id, respostaRepo);
			denuncia.setRespostaDenunciada(r);
		}

		denuncia.setEntidadeDenunciado(entidade);
		denuncia.setDataDenuncia(new Timestamp(System.currentTimeMillis()));
		denuncia.setAutorDenuncia(usuario);
		
		b.adicionarDenuncia(denuncia, denunciaRepo);
		
		System.out.println("Denunciando " + entidade + " id " + id);
		return "admin/adminForum";
	}
	
	
	@GetMapping("/adminDenuncias")
	public String verDenuncias(Map<String, Object> model ){
		
		BancoDados b = new BancoDados();
		List<Denuncia> denunciasTopicos = b.buscaTopicosDenunciados(denunciaRepo);
		List<Denuncia> denunciasRespostas = b.buscaRespostasDenunciadas(denunciaRepo);
		
		model.put("denunciasTopicos", denunciasTopicos);
		model.put("denunciasRespostas", denunciasRespostas);
		return "admin/adminDenuncias";
	}
	
	@GetMapping("/excluirDenuncia")
	public String excluirDenuncia(Map<String, Object> model,
			@RequestParam(name = "id") Integer id) {
		
		BancoDados b = new BancoDados();
		Denuncia denuncia = b.buscarDenunciaPorId(id, denunciaRepo);
		
		if(denuncia.getEntidadeDenunciado().equals("resposta")) {
			b.removerRespostaDenunciada(denuncia.getRespostaDenunciada(), denuncia, respostaRepo, denunciaRepo);
			
		}else if (denuncia.getEntidadeDenunciado().equals("topico")) {
			for(Resposta r : denuncia.getTopicoDenunciado().getRespostas()) {
				b.removerResposta(r, respostaRepo, denunciaRepo);
			}
			b.removerTopicoDenunciado(denuncia.getTopicoDenunciado(), denuncia, topicoRepo, denunciaRepo);
		}
		
		b.removerDenuncia(denuncia, denunciaRepo);
		
		return "redirect:/adminDenuncias";
	}
	
}
