package pe.com.cosito.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/vendedor/*")
public class VendedorController {

	@RequestMapping(value="home",method={RequestMethod.GET,RequestMethod.POST})
	public String indexVendedor()
	{
		return "Vendedor/home";
	}
}