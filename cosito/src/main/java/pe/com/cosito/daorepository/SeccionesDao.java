package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Secciones;

@Repository
public interface SeccionesDao extends JpaRepository<Secciones,Integer>{
	
}