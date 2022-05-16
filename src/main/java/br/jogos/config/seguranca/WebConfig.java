package br.jogos.config.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.interceptor.InterceptadorDeAcesso;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	
	public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new InterceptadorDeAcesso()).addPathPatterns("/**");
	}
}
