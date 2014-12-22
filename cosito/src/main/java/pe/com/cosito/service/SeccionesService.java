package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Secciones;
import pe.com.cosito.daorepository.SeccionesDao;

@Service
public class SeccionesService {

	@Autowired
	private SeccionesDao seccionesDao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(SeccionesService.class);
	
	public List<Secciones> getAll(){
		List<Secciones> info;
		try{
			info=seccionesDao.findAll();
		}catch(Exception ex){
			logger.info("SeccionesService-getAll : "+ex.getMessage());
			info=null;
		}
		return info;
	}
}