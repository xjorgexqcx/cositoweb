package pe.com.cosito.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Imagenes;
import pe.com.cosito.beans.Seccion;
import pe.com.cosito.beans.Secciones;
import pe.com.cosito.daorepository.ImagenesDao;
import pe.com.cosito.daorepository.SeccionDao;
import pe.com.cosito.daorepository.SeccionesDao;

@Service
public class SeccionesService {

	@Autowired
	private SeccionesDao seccionesDao;
	@Autowired
	private SeccionDao seccionDao;
	@Autowired
	private ImagenesDao imagenesDao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(SeccionesService.class);

	public List<Secciones> getAll() {
		List<Seccion> secciones = null;
		List<Secciones> info = new ArrayList();
		List<Imagenes> imagenes =null;

		try {
			secciones = seccionDao.findAll();
			imagenes = imagenesDao.findAll();
			
			logger.info("No estan vacios? : "+secciones.size());
			logger.info("Imagenes tampoco?: "+imagenes.size());
			
			for (Seccion sec : secciones) {
				Secciones se=new Secciones();
				for(Imagenes ima: imagenes){
					if(ima.getId() == sec.getIdImagen()){
						logger.info(sec.getIdImagen()+"Imagen encontrada: "+ima.getId()+" - "+ima.getUrl());
						se.setIdImagen(ima);
					}
				}
				se.setId(sec.getId());
				se.setNombre(sec.getNombre());
				se.setEstado(sec.getEstado());
				info.add(se);
			}
		} catch (Exception ex) {
			logger.info("SeccionesService-getAll : " + ex.getMessage());
			ex.printStackTrace();
		}
		return info;
	}

	public int agregar(Secciones secc) {
		int rpta = 0;
		Seccion sec = new Seccion();
		sec.setIdImagen(secc.getIdImagen().getId());
		sec.setNombre(secc.getNombre());
		sec.setEstado(1);
		try {
			seccionDao.saveAndFlush(sec);
			rpta = 1;
		} catch (Exception ex) {
			rpta = 0;
			logger.info("SeccionesService-agregar : " + ex.getMessage());
			ex.printStackTrace();
		}
		return rpta;
	}

	public int eliminarSeccion(int seccion) {
		Secciones secc = seccionesDao.findOne(seccion);
		logger.info("Secciones: " + secc.getIdImagen().getId());
		Seccion sec = new Seccion();
		sec.setId(secc.getId());
		sec.setIdImagen(secc.getIdImagen().getId());
		sec.setNombre(secc.getNombre());
		sec.setEstado(0);
		sec = seccionDao.saveAndFlush(sec);
		if (sec != null) {
			return 1;
		}
		return 0;
	}

}