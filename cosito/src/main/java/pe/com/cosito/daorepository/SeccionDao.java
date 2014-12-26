package pe.com.cosito.daorepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cosito.beans.Seccion;

@Repository
public interface SeccionDao extends JpaRepository<Seccion,Integer>{

}