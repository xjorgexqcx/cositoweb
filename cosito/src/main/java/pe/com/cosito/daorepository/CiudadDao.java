package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Ciudad;

@Repository
public interface CiudadDao extends JpaRepository<Ciudad,Integer>{

}