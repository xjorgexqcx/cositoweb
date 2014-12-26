package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Imagenes;
import pe.com.cosito.beans.Secciones;
import pe.com.cosito.controller.HomeController;
import pe.com.cosito.daorepository.ImagenesDao;

@Service
public class ImagenesService {

	private static final Logger logger = LoggerFactory
			.getLogger(ImagenesService.class);

	@Autowired
	private ImagenesDao imagenesDao;

	public List<Imagenes> getAll() {
		List<Imagenes> img;
		try {
			img = imagenesDao.findAll();
		} catch (Exception ex) {
			logger.info("SeccionesService-getAll : " + ex.getMessage());
			img = null;
		}
		return img;
	}

	public Imagenes findById(int id) {
		Imagenes img;
		try {
			img = imagenesDao.getOne(id);
		} catch (Exception ex) {
			logger.info("SeccionesService-getAll : " + ex.getMessage());
			img = null;
		}
		return img;
	}

	public Imagenes Agregar(Imagenes img) {
		Imagenes imgs;
		try {

			imgs = imagenesDao.save(img);
			logger.info("Despues de agregar se obtuvo como respuesta: "
					+ imgs.getId() + "-" + imgs.getUrl());
		} catch (Exception ex) {
			logger.info("SeccionesService-getAll : " + ex.getMessage());
			imgs=null;
		}
		return imgs;
	}

	public int eliminar(Imagenes imgs) {
		int rpta=0;
		try{
			imagenesDao.delete(imgs);
			rpta=1;
		}catch(Exception ex)
		{
			logger.info("Error al eliminar imagen de la base de datos");
		}
		return rpta;
	}
	public boolean imagenRepetida(String url) {
		Imagenes img=imagenesDao.buscarPorNombre(url);
		logger.info(url+" = "+img.getUrl());
		if(img != null){
			return true;
		}
		return false;
	}
}