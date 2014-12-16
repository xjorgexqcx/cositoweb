package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Categoria;
import pe.com.cosito.beans.Presentacion;
import pe.com.cosito.daorepository.PresentacionDao;

@Service
public class PresentacionService {

	@Autowired
	private PresentacionDao presentacionDao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PresentacionService.class);
	
	public List<Presentacion> getPresentacionxProducto(int producto){//no me acuerdo el nombre exacto dela excepcion
		List<Presentacion> pre=null;
		try{
			pre=presentacionDao.presentacionProductos(producto);
		}catch(Exception ex)
		{
			pre=null;
			logger.info("Error al mostrar presentaciones:"+ex.getMessage());
		}
		return pre;
	}

	public List<Presentacion> getAll() {
		// TODO Auto-generated method stub
		List<Presentacion> pre = null;
		try{
			pre=presentacionDao.findAll();
		}catch(Exception ex)
		{
			pre=null;
			logger.info("Error: "+ex.getMessage());
		}
		return pre;
	}
}
