package pe.com.cosito.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.com.cosito.beans.Secciones;
import pe.com.cosito.service.SeccionesService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	@Autowired
	private SeccionesService seccionesService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
		List<Secciones> ltaSecciones = seccionesService.getAll();
		model.addAttribute("ltaSecciones",ltaSecciones);
		
		logger.info("Tamaño de las secciones: "+ltaSecciones.size());
		for(Secciones sec : ltaSecciones){
			logger.info("Imagen de la seccion: "+sec.getNombre());
		}
		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model){
		List<Secciones> ltaSecciones = seccionesService.getAll();
		model.addAttribute("ltaSecciones",ltaSecciones);
		return "index";
	}
	@RequestMapping(value="/publicaciones", method=RequestMethod.GET)
	public String publicaciones(){
		return "publicaciones";
	}
	
}