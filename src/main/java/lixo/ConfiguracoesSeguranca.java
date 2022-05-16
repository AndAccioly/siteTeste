//package br.jogos.config.seguranca;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import br.jogos.repository.UsuarioRepo;
//
//@EnableWebSecurity
//@Configuration
//public class ConfiguracoesSeguranca extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private AutenticacaoService autenticacaoService;
//	
//	@Autowired
//	private TokenService tokenService;
//	
//	@Autowired
//	private UsuarioRepo usuarioRepo;
//	
//	@Override
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
//	
//	//Autenticacao
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());	
//	}
//	
//	//Autorizacao
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		//.antMatchers(HttpMethod.GET, "/listarJogos").permitAll()
//		.antMatchers(HttpMethod.GET, "/oi").permitAll()
//		.antMatchers(HttpMethod.POST, "/auth").permitAll()
//		.antMatchers(HttpMethod.POST, "/h2-console").permitAll()
//		.anyRequest().authenticated()
//		.and().csrf().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioRepo), UsernamePasswordAuthenticationFilter.class)
//		;
//	}
//		
//	//recursos estáticos (js, imagens, css...)
//	//8080/swagger-ui
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//        .antMatchers("/resources/**", "/static/**", "/css/**", "/html/**","/js/**", "/img/**", "/icon/**");
//		//web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
//	}
//
////	public static void main(String[] args) {
////		System.out.println(new BCryptPasswordEncoder().encode("123"));
////	}
//	
//}
//
//
