package pe.com.cosito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Laboratorio;
import pe.com.cosito.daorepository.LaboratorioDao;

@Service
public class LaboratorioService {

	@Autowired
	private LaboratorioDao laboratorioDao;
	
	public List<Laboratorio> getAll() throws Exception{ //no me acuerdo el nombre exacto dela excepcion  lol
		return laboratorioDao.findAll();
	}
}
