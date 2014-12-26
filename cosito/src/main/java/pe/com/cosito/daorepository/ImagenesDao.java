package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Imagenes;

@Repository
public interface ImagenesDao extends JpaRepository<Imagenes,Integer>{

	@Query("select i from Imagenes i where url = :url")
	Imagenes buscarPorNombre(@Param("url") String url);
}