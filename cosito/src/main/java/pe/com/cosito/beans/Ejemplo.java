package pe.com.cosito.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_usuario")
public class Ejemplo implements Serializable {

	/**
	 * Identificador cuando un bean viaja por la red, algo asi
	 */
	private static final long serialVersionUID = 1L;
	
	//@Column(name = "id") si el atributo no se llama = que el campo de la bd debes poner column
	private Integer id;
	
	private String descripcion;
	private String dni;
	
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="nombre")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

}
