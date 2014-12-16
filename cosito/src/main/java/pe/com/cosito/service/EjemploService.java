package pe.com.cosito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Ejemplo;
import pe.com.cosito.daorepository.EjemploDao;

@Service
public class EjemploService {

	//Por defecto la instancia que hace spring de un bean es con la 1era letra en minuscula osea EjemploDao => ejemploDao
	//Si no lo quieres asi usa qualifier, por ejem aca es !=
	@Autowired
	@Qualifier("ejemploDao")
	private EjemploDao ejemploBD;
	
	public List<Ejemplo> getAll() throws Exception{ //no me acuerdo el nombre exacto dela excepcion  lol
		return ejemploBD.findAll();
	}
	
	public Ejemplo getByDni(String dni){
		return ejemploBD.findByDni(dni);
	}
	
	public int insert(Ejemplo bean){
		try{
			ejemploBD.save(bean);  //Este guarda o actualiza segun sea el caso, el framework verifica si existe ok y lo actualzia
			return 0;
		}catch(Exception e){
			return 1;
		}
	}
	
	public int update(Ejemplo bean){
		try{
			Ejemplo a = ejemploBD.saveAndFlush(bean); // lo mismo de arriba pero la cache se actualiza mejor dicho devuelve el bean actualizado si es necesario
			return 0;
		}catch(Exception e){
			return 1;
		}
	}
}
