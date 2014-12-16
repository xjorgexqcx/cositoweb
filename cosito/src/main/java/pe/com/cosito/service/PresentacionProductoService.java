package pe.com.cosito.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.PresentacionProducto;
import pe.com.cosito.controller.HomeController;
import pe.com.cosito.daorepository.PresentacionProductoDao;

@Service
public class PresentacionProductoService {

	@Autowired
	private PresentacionProductoDao presentacionProductoDao;

	private static final Logger logger = LoggerFactory
			.getLogger(PresentacionProductoService.class);
	
	public PresentacionProducto getPrecio(int idPresentacion, int idProducto) {
		PresentacionProducto prod=null;
		try {
			prod = presentacionProductoDao.getById(
					idPresentacion, idProducto);
		} catch (Exception ex) {
			logger.info("Error en el PresentacionProducto : "+ex.getMessage());
			ex.printStackTrace();
		}
		return prod;
	}

}