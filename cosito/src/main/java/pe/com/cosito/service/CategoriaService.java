package pe.com.cosito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Categoria;
import pe.com.cosito.daorepository.CategoriaDao;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;
	
	public List<Categoria> getAll() throws Exception{ //no me acuerdo el nombre exacto dela excepcion  lol
		return categoriaDao.findAll();
	}
}