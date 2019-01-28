package com.ozymern.spring.i18n.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaleController {

	@GetMapping("/locale")
	public String locale(HttpServletRequest request){
		// referer entrega el link de la ultima pagina, la referencia de nuestra ultima url
		String endaUrl=request.getHeader("referer");
	
		return "redirect:"+endaUrl;
				
		
		
	}
	
}
