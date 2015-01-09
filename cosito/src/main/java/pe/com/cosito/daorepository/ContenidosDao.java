package pe.com.cosito.daorepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.cosito.beans.Contenidos;

public interface ContenidosDao extends JpaRepository<Contenidos,Integer>{

	@Query("select c from Contenidos c where idseccion = :idSeccion")
	List<Contenidos> BuscarPorId(@Param("idSeccion") int idSeccion);
}
