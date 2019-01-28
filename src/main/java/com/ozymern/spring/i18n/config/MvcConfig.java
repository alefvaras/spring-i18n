package com.ozymern.spring.i18n.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	//para determinar qué configuración regional se está utilizando actualmente, debemos agregar un bean LocaleResolver 
	//La interfaz LocaleResolver tiene implementaciones que determinan la configuración regional actual en función de la sesión, 
	//las cookies, el encabezado Accept-Language o un valor fijo
	 @Bean
	    public LocaleResolver localeResolver() {
	        SessionLocaleResolver slr = new SessionLocaleResolver();
	        slr.setDefaultLocale(Locale.US);
	        return slr;
	    }
	 //debemos agregar un bean interceptor que cambiará a una nueva configuración regional en función del valor del parámetro lang adjunto 
	// a una solicitud:
	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	        lci.setParamName("lang");
	        return lci;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeChangeInterceptor());
	    }
	    
	    
	    //se modificara el bean para agregar el nuevo classpath de los archivos de idiomas
	    @Bean
	    public MessageSource messageSource() 
	    {
	        // https://stackoverflow.com/questions/40165151/how-to-handle-multiple-files-and-messages-for-internationalization-in-spring
	        
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	         messageSource.setBasename("classpath:i18n/messages");
//	        messageSource.setBasenames(
//	            "classpath:i18n/messages/messages",
//	            "classpath:i18n/layout/messages",
//	            "classpath:i18n/sites/messages",
//	            "classpath:i18n/auth/messages",
//	            "classpath:i18n/pages/messages"
//	        );
	        
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }

}
