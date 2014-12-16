package pe.com.cosito.daorepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Productos;

@Repository
public interface ProductosDao extends JpaRepository<Productos,Integer>{
	
	@Query("select p from Productos p where nombre = :nombre ")
	List<Productos> buscarPorProducto(@Param("nombre") String nombre);
	
	@Query("select p from Productos p, Categoria ca where ca.descripcion = :nombre and p.idCategoria = ca.id ")
	List<Productos> getAllByCategory(@Param("nombre") String nombre);
}