package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Ejemplo;

/**
 * 
 * @author Administrador
 *Este es la implementacion del dao, no hay nada, spring hace todo, antes haciamos los insert,select,etc
 */
@Repository
public interface EjemploDao extends JpaRepository<Ejemplo,Integer> {

	@Query("Select e from Ejemplo e where e.dni = :dni")
	Ejemplo findByDni(@Param("dni") String dni);
	
}
