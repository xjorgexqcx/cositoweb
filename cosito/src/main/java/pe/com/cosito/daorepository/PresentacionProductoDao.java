package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.PresentacionProducto;

@Repository
public interface PresentacionProductoDao extends JpaRepository<PresentacionProducto,Integer> {

	@Query("select p from PresentacionProducto p where p.idPresentacion = :idPresentacion and p.idProducto = :idProducto")
	public PresentacionProducto getById(@Param("idPresentacion")int idPresentacion,@Param("idProducto")int idProducto);
}
