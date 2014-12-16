package pe.com.cosito.daorepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Presentacion;

@Repository
public interface PresentacionDao extends JpaRepository<Presentacion, Integer> {

	@Query("select pr from Presentacion pr, PresentacionProducto p where p.idPresentacion=pr.id and p.idProducto = :producto")
	List<Presentacion> presentacionProductos(@Param("producto") int producto);
}
