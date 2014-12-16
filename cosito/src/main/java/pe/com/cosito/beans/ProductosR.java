package pe.com.cosito.beans;

import java.io.Serializable;

public class ProductosR implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nombre;
	
	private Laboratorio idLaboratorio;
	
	private Categoria idCategoria;

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	
	public Laboratorio getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Laboratorio idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}