package br.jogos.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.jogos.dados.BancoDados;
import br.jogos.modelos.Noticia;
import br.jogos.repository.NoticiaRepo;

@Controller
public class NoticiaController {

	@Autowired
	NoticiaRepo noticiaRepo;
	
	@GetMapping("/home")
	public String home(Map<String, Object> model) {
		BancoDados b = new BancoDados();
		List<Noticia> noticias = b.ultimasNoticias(noticiaRepo, 4);
		model.put("noticias", noticias);
		
		return "paginas/home";
	}
	
	@GetMapping("/verNoticia")
	public String verNoticia(
			@RequestParam(name = "id") Integer id,
			Map<String, Object> model) {
			
		BancoDados b = new BancoDados();
		Noticia noticia = b.buscarNoticiaPorId(id, noticiaRepo);
		
		model.put("noticia", noticia);
		return "noticia/verNoticia";
	}
	
	@GetMapping("/escreverNoticia")
	public String escreverNoticia(Map<String, Object> model) {
		model.put("noticia", new Noticia());
		return "noticia/escreverNoticia";
	}
	
	@PostMapping("/salvarNoticia")
	public String salvarNoticia(Map<String, Object> model) {
		return "redirect:/escreverNoticia";
	}
}

