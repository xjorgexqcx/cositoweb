package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Categoria;

@Repository
public interface CategoriaDao extends JpaRepository<Categoria,Integer>{

}
