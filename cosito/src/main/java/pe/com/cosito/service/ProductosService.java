package pe.com.cosito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Productos;
import pe.com.cosito.daorepository.ProductosDao;

@Service
public class ProductosService {

	@Autowired
	private ProductosDao productosDao;

	private static final Logger logger = LoggerFactory
			.getLogger(ProductosService.class);

	public List<Productos> getAll() throws Exception {
		return productosDao.findAll();
	}

	public List<Productos> buscarPorProducto(String nombre) {
		List<Productos> rpta = null;
		try {
			rpta = productosDao.buscarPorProducto(nombre);
		} catch (Exception ex) {
			rpta = null;
			logger.info("El error es el siguiente:"+ex.getMessage());
		}
		return rpta;
	}

	public List<Productos> getAllByCategory(String nombre){
		List<Productos> rpta = null;
		try{
			logger.info("el dato aun es el siguiente:"+nombre);
			rpta=productosDao.getAllByCategory(nombre);
		}catch(Exception ex)
		{
			rpta=null;
			logger.info("El error es el siguiente: "+ex.getMessage());
		}
		return rpta;
	}
}