//package br.jogos.controlador;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
////import br.jogos.config.seguranca.TokenService;
//import br.jogos.modelos.LoginForm;
//import br.jogos.modelos.TokenTO;
//
//@Controller
//public class AutenticacaoController {
//	
//	@Autowired
//	AuthenticationManager autManager;
//	
////	@Autowired
////	private TokenService tokenService;
////	
//	@PostMapping("/auth")
//	public String autenticar(@ModelAttribute LoginForm form, Map<String, Object> model){
//		
//		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
//		model.put("LoginForm", form);
//		
//		try {
//			
//			Authentication authentication = autManager.authenticate(dadosLogin);			
//			//String token = tokenService.gerarToken(authentication);
////			System.out.println("AUTENTICANDO... " + form.getNome() + " " + token);	
////			model.put("token", token);
//			
//			return "logado";
//			//TokenTO tokenTO = new TokenTO(token, "Bearer");
//			//return ResponseEntity.ok("logado");
//			
//		} catch (AuthenticationException e) {
//			//return ResponseEntity.badRequest().build();
//			return "login";
//			
//		}
//	}
//
//}
