package br.jogos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.jogos.dados.BancoDados;
import br.jogos.modelos.AvJogo;
import br.jogos.modelos.Usuario;
import br.jogos.repository.UsuarioRepo;
import lixo.DadosUsuario;
import transferObjects.UsuarioFormCadastro;

@Controller
public class LoginController {
	
	@Autowired
	UsuarioRepo usuarioRepo;

	@GetMapping("/profile")
	public String profile(Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		BancoDados b = new BancoDados();
		Usuario usuario = b.buscarUsuario(username, usuarioRepo);
		
		model.addAttribute("souEu", true);
		model.addAttribute("avJogo", new AvJogo());
		model.addAttribute("usuario", usuario);
		return "perfil/verPerfil";
	}
	
	@GetMapping("/verPerfil")
	public String verPerfil(Model model, @RequestParam(name = "id") Integer id) {
		int souEu = 0;
		BancoDados b = new BancoDados();
		Usuario usuario = b.buscarUsuarioPorId(id, usuarioRepo);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (usuario.getUsername().equals(username)) {
			souEu = 1;
		}
		
		model.addAttribute("souEu", souEu);
		model.addAttribute("avJogo", new AvJogo());
		model.addAttribute("usuario", usuario);
		return "perfil/verPerfil";
	}
	
	@GetMapping("/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("usuario", new UsuarioFormCadastro());
		model.addAttribute("erro", "");
		return "paginas/cadastro";
	}
	
	@PostMapping("/cadastro")
	public String realizarCadastro(@ModelAttribute UsuarioFormCadastro usuarioForm, Model model) {
		String mensagem = "Usuário ou senha inválido(s).";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(usuarioForm.getPassword().equals(usuarioForm.getPasswordConfirmada())) {
			System.out.println("===> " + usuarioForm.getEmail());
			System.out.println("===> " + usuarioForm.getUsername());
			System.out.println("===> " + usuarioForm.getPassword());
			System.out.println("===> " + usuarioForm.getPasswordConfirmada());
			
			Usuario u = new Usuario(usuarioForm.getEmail(), usuarioForm.getUsername(), encoder.encode(usuarioForm.getPassword()), 0);
			BancoDados b = new BancoDados();
			boolean resp = b.cadastraUsuario(u, usuarioRepo);
			
			if(resp) {
				mensagem = "Usuário cadastrado com sucesso.";
			}
		}
		
		model.addAttribute("mensagem", mensagem);
		return "paginas/cadastro";
	}
	
	@GetMapping("/login")
	public String oi( Model model ) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public String processForm(@ModelAttribute Usuario usuario, Model model) {
		boolean resp;
		System.out.println("LOGANDO...");
		String erro = "";
		DadosUsuario dados = new DadosUsuario();
		resp = dados.autenticarUsuario(usuario);
		
		if(!resp) {
			erro = "Usuário e/ou senha incorretos.";
		}
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("erro", erro);
		
		if (resp) {
			return "meusJogos";
			
		}else {
			return "login";			
		}
	}
}
