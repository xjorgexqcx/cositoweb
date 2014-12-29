package pe.com.cosito.controller;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.cosito.beans.Imagenes;
import pe.com.cosito.beans.Secciones;
import pe.com.cosito.service.ImagenesService;
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
	@Autowired
	private ImagenesService imagenesService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		try{
		List<Secciones> ltaSecciones = seccionesService.getAll();
		model.addAttribute("ltaSecciones", ltaSecciones);
		for (Secciones sec : ltaSecciones) {
			logger.info("Imagen de la seccion: " + sec.getNombre() + "-"
					+ sec.getIdImagen().getId());
		}
		}catch(Exception ex)
		{
			logger.info("Error en index: "+ex.getMessage());
			ex.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		List<Secciones> ltaSecciones = seccionesService.getAll();
		model.addAttribute("ltaSecciones", ltaSecciones);
		return "index";
	}

	@RequestMapping(value = "/publicaciones", method = RequestMethod.GET)
	public String publicaciones() {
		return "publicaciones";
	}

	@RequestMapping(value = "/sube")
	public String sube() {
		return "sube";
	}

	@RequestMapping(value = "/uploadFile", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String uploadFileHandler(HttpServletRequest servletRequest,
			Model model, @RequestParam("userPhoto") MultipartFile file) {

		if (file == null) {
			return "index";
		}

		String nombreImagen = file.getOriginalFilename();
		boolean verificado = verificaImagen(nombreImagen);
		if (!verificado) {
			model.addAttribute(
					"rpta",
					"Solo se permiten guardar imágenes. Haga Click en continuar y vuelva a intentarlo porfavor");
			model.addAttribute("banner", "images/error-banner.jpg");
			model.addAttribute("color", "red");
			model.addAttribute("nombre", file.getOriginalFilename());
			return "respuesta";
		}
		nombreImagen = "images/secciones/upload/" + file.getOriginalFilename();
		if (imagenesService.imagenRepetida(nombreImagen)) {
			model.addAttribute(
					"rpta",
					"Ya existe una imagen con el mismo nombre. Haga Click en continuar y vuelva a intentarlo porfavor");
			model.addAttribute("banner", "images/error-banner.jpg");
			model.addAttribute("color", "red");
			model.addAttribute("nombre", file.getOriginalFilename());
			return "respuesta";
		}

		InputStream inputStream = null;
		Image image = null;
		OutputStream outputStream = null;

		Secciones secc = new Secciones();
		Imagenes imgs = new Imagenes();
		String ruta = System.getProperty("user.home")
				+ "/git/cositoweb/cosito/src/main//webapp/images/secciones/upload/"
				+ file.getOriginalFilename();
		// String ruta = servletRequest.getSession().getServletContext()
		// .getRealPath("/")+ "/images/secciones/upload/" +
		// file.getOriginalFilename();
		int rpta1;
		int rpta = 0;
		try {
			inputStream = file.getInputStream(); // leemos el fichero local
			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(new File(ruta));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			rpta = 1;
			secc.setNombre(servletRequest.getParameter("nombre").toString());
			imgs.setUrl("images/secciones/upload/" + file.getOriginalFilename());
			imgs = imagenesService.Agregar(imgs);
			logger.info("Agrego la imagen" + imgs.getId() + "-" + imgs.getUrl());
			secc.setIdImagen(imgs);
			rpta1 = subirSeccionnBD(secc);
			HttpSession hs = servletRequest.getSession();
			if (rpta1 > 0) {
				model.addAttribute("rpta",
						"Todos los procesos se ejecutaron correctamente");
				// model.addAttribute("banner", imgs.getUrl());
				model.addAttribute("color", "green");
				model.addAttribute("nombre", file.getOriginalFilename());
				model.addAttribute("banner", "images/success-banner.jpg");
				File is = new File(imgs.getUrl());
				file.transferTo(is);
				image = ImageIO.read(is);
				image = ImageIO.read(is);
			}
			if (rpta1 <= 0) {
				eliminarImagen(ruta, imgs);
				rpta = 0;

				model.addAttribute("rpta",
						"Fallaron algunos procesos al intentar guardar el archivo y/o su información");
				model.addAttribute("banner", "images/error-banner.jpg");
				model.addAttribute("color", "red");
				model.addAttribute("nombre", file.getOriginalFilename());
			}
		} catch (IOException i) {
			rpta = 0;
			rpta1 = 0;
			logger.info("Error al cargar archivo: " + i.getMessage());
		}
		return "respuesta";
	}

	private boolean verificaImagen(String nombreImagen) {
		String extension=FilenameUtils.getExtension(nombreImagen);
		String[] formatos = { "jpg", "png", "gif", "jpeg" };
		boolean bandera = false;
		for (int i = 0; i < formatos.length; i++) {
			String minuscula = extension.toLowerCase();
			if (minuscula.equals(formatos[i])) {
				bandera = true;
				break;
			}
		}
		logger.info("Extension : "+extension);
		return bandera;
	}

	private void eliminarImagen(String ruta, Imagenes id) {
		try {
			File fichero = new File(ruta);
			if (fichero.delete()) {
				logger.info("Se elimino correctamente el fichero");
				imagenesService.eliminar(id);
			} else {
				logger.info("Error al eliminar el fichero");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private int subirSeccionnBD(Secciones sec) {
		int rpta = 0;
		try {
			rpta = seccionesService.agregar(sec);
		} catch (Exception ex) {
			return 0;
		}
		return rpta;
	}

	@RequestMapping(value = "/eliminaSeccion", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public int eliminarSeccion(@RequestParam("seccion") int seccion) {
		int rpta = seccionesService.eliminarSeccion(seccion);
		return rpta;
	}
}