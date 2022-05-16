//package br.jogos.config.seguranca;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import br.jogos.modelos.Usuario;
//import br.jogos.repository.UsuarioRepo;
//import mensagens.MensagemTexto;
//import mensagens.Mensagens;
//
//@Service
//public class AutenticacaoService implements UserDetailsService {
//
//	@Autowired
//	UsuarioRepo usuarioRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Usuario> usuario = usuarioRepo.findByNome(username);
//		if(usuario.isPresent()) {
//			return usuario.get();					
//		}
//
//		throw new UsernameNotFoundException(MensagemTexto.getMensagemTexto(Mensagens.DADOS_INVALIDOS));	
//	}
//
//}
