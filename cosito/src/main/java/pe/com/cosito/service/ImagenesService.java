package pe.com.cosito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cosito.beans.Imagenes;
import pe.com.cosito.daorepository.ImagenesDao;

@Service
public class ImagenesService {

	@Autowired
	private ImagenesDao imagenesDao;
	
	public List<Imagenes> getAll()
	{
		return null;	
	}
}