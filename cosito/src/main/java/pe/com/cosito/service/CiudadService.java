package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Ciudad;
import pe.com.cosito.daorepository.CiudadDao;

@Service
public class CiudadService {

	private static final Logger logger = LoggerFactory
			.getLogger(CiudadService.class);
	
	@Autowired
	private CiudadDao ciudadDao;

	public List<Ciudad> getAll() {
		List<Ciudad> ciudades=null;
		try{
			ciudades=ciudadDao.findAll();
		}catch(Exception ex)
		{
			ciudades = null;
			logger.info("Error: "+ex.getMessage());
		}
		return ciudades;
	}
}
