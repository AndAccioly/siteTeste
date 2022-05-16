//package br.jogos.config.seguranca;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import br.jogos.modelos.Usuario;
//import br.jogos.repository.UsuarioRepo;
//
//public class AutenticacaoTokenFilter extends OncePerRequestFilter {
//
//	private TokenService tokenService;
//	private UsuarioRepo usuarioRepo;
//	
//	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepo usuarioRepo) {
//		this.tokenService = tokenService;
//		this.usuarioRepo = usuarioRepo;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		//Http11Processor.service(SocketWrapperBase)
//		String token = recuperarToken(request);
//		System.out.println("WHO CALLS: " + request.getRequestURL());
//		System.out.println("AUTHORIZATION: " + request.getHeader("Authorization"));
//		//System.out.println("FILTER AUTH TOKEN: " + token);
//		
//		boolean valido = tokenService.isTokenValido(token);
//		if(valido) {
//			System.out.println("Usuário válido");
//			autenticarCliente(token);
//		}
//
//		filterChain.doFilter(request, response);
//		
//	}
//
//	private void autenticarCliente(String token) {
//		Integer idUsuario = tokenService.getIdUsuario(token);
//		Usuario usuario = usuarioRepo.findById(idUsuario).get();
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(idUsuario, null, usuario.getAuthorities());	 
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//	}
//
//	private String recuperarToken(HttpServletRequest request) {
//		String token = request.getHeader("Authorization");
//		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
//			return null;			
//		}
//		
//		return token.substring(7, token.length());
//	}
//
//}
