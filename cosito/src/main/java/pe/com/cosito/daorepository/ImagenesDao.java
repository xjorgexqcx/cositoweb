package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Imagenes;

@Repository
public interface ImagenesDao extends JpaRepository<Imagenes,Integer>{

}