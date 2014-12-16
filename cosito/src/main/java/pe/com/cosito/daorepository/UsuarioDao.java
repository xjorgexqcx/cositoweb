package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
	@Query("select u from Usuario u where id = :id")
	public Usuario findById(@Param("id") int id);

	@Query("select u from Usuario u where dni = :dni")
	public void busquedaDni(@Param("dni") String dni);
}