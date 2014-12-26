package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Seccion;
import pe.com.cosito.beans.Secciones;
import pe.com.cosito.daorepository.SeccionDao;
import pe.com.cosito.daorepository.SeccionesDao;

@Service
public class SeccionesService {

	@Autowired
	private SeccionesDao seccionesDao;
	@Autowired
	private SeccionDao seccionDao;

	private static final Logger logger = LoggerFactory
			.getLogger(SeccionesService.class);

	public List<Secciones> getAll() {
		List<Secciones> info;
		try {
			info = seccionesDao.findAll();
		} catch (Exception ex) {
			logger.info("SeccionesService-getAll : " + ex.getMessage());
			info = null;
		}
		return info;
	}

	public int agregar(Secciones secc) {
		int rpta=0;
		Seccion sec = new Seccion();
		sec.setIdImagen(secc.getIdImagen().getId());
		sec.setNombre(secc.getNombre());
		sec.setEstado(1);
		try {
			seccionDao.saveAndFlush(sec);
			rpta=1;
		} catch (Exception ex) {
			rpta=0;
			logger.info("SeccionesService-agregar : " + ex.getMessage());
			ex.printStackTrace();
		}
		return rpta;
	}

	public int eliminarSeccion(int seccion) {
		Secciones secc = seccionesDao.findOne(seccion);
		logger.info("Secciones: "+secc.getIdImagen().getId());
		Seccion sec = new Seccion();
		sec.setId(secc.getId());
		sec.setIdImagen(secc.getIdImagen().getId());
		sec.setNombre(secc.getNombre());
		sec.setEstado(0);		
		sec=seccionDao.saveAndFlush(sec);
		if(sec != null){
			return 1;
		}
		return 0;
	}

}