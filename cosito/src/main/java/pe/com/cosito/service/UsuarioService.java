package pe.com.cosito.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Usuario;
import pe.com.cosito.daorepository.UsuarioDao;

@Service
public class UsuarioService {

	// Por defecto la instancia que hace spring de un bean es con la 1era letra
	// en minuscula osea EjemploDao => ejemploDao
	// Si no lo quieres asi usa qualifier, por ejem aca es !=
	private static final Logger logger = LoggerFactory
			.getLogger(UsuarioService.class);

	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioBD;

	public List<Usuario> validaLogin() throws Exception {
		return usuarioBD.findAll();
	}

	public int guardar(Usuario usu) {
		int rpta = 0;
		try {
			usuarioBD.save(usu);
			rpta = 1;
		} catch (Exception ex) {
			rpta = 0;
			logger.info("Error al guardar: " + ex.getMessage());
			ex.printStackTrace();
		}
		return rpta;
	}

	public Usuario findById(String id) {
		Usuario usu =null;
		int idU=Integer.parseInt(id);
		try{
			logger.info("Entro a buscar usuario por id: "+id);
			usu=usuarioBD.findById(idU);
		}catch(Exception ex){
			usu=null;
			logger.info("Error al buscar por ID: "+ex.getMessage());
			ex.printStackTrace();
		}
		return usu;
	}
	public boolean busquedaDni(String dni)
	{
		boolean bandera=false;
		try{
			usuarioBD.busquedaDni(dni);
			bandera=true;
		}catch(Exception ex){
			bandera=false;
			logger.info("Error al buscar por Dni: "+ex.getMessage());
			ex.printStackTrace();
		}
		return bandera;
	}
}