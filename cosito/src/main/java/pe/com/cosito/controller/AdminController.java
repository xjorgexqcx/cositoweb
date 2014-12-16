package pe.com.cosito.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/administrador/*")
public class AdminController {

	@RequestMapping(value="home", method={RequestMethod.GET,RequestMethod.POST})
	public String indexAdmin()
	{
		return "Administrador/home";
	}
	@RequestMapping(value="valida", method={RequestMethod.GET,RequestMethod.POST})
	public String validar()
	{
		return "Administrador/valida";
	}
}
