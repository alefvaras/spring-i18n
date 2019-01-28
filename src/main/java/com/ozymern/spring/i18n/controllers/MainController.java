package com.ozymern.spring.i18n.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	public static final String PAGE_HOME = "index";
	
	//Al inyectar MessageSourceen el controlador podemos crear un mensaje parametrizado mediante
	//el MessageSource.getMessage(code, args, locale)método. Esto MessageSourcese encarga de cargar
	//el mensaje de idioma correcto en función de la clave y la configuración regional dadas. Opcionalmente 
	//podemos añadir algunos argumentos
		@Autowired
		private MessageSource messageSource;
		

	@GetMapping("/")
	public String home(Model model,Locale locale) {
		model.addAttribute("title","home");
		//cambio de idioma dinamico
		model.addAttribute("footer", messageSource.getMessage("text.footer", null, locale));
		return PAGE_HOME;
	}
		
	
	
}
