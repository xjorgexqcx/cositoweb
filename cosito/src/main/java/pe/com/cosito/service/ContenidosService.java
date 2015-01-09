package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.daorepository.ContenidosDao;
import pe.com.cosito.beans.Contenidos;

@Service
public class ContenidosService {

	private static final Logger logger = LoggerFactory
			.getLogger(ContenidosService.class);
	
	@Autowired
	private ContenidosDao contenidosDao;
	
	public List<Contenidos> buscarPorId(int idSeccion){
		List<Contenidos> contenidos;
		try{
			contenidos = contenidosDao.BuscarPorId(idSeccion);
			logger.info("Cantidad de articulo en esta seccion : "+contenidos.size());
		}catch(Exception ex){
			contenidos=null;
			ex.printStackTrace();
			logger.info("ContenidosService buscarPorId - Error: "+ex.getMessage());
		}
		return contenidos;
	}
}
