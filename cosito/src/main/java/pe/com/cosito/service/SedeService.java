package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Sede;
import pe.com.cosito.daorepository.SedeDao;

@Service
public class SedeService {

	private static final Logger logger = LoggerFactory
			.getLogger(SedeService.class);
	
	@Autowired
	private SedeDao sedeDao;
	
	public List<Sede> getAll()
	{
		List<Sede> sedes=null;
		try{
			sedes=sedeDao.findAll();
		}catch(Exception ex)
		{
			sedes=null;
			logger.info("Error: "+ex.getMessage());
		}
		return sedes;
	}
}
