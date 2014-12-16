package pe.com.cosito.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inventario")
public class Inventario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "presentacion")
	private Presentacion idPresentacion;

	@ManyToOne
	@JoinColumn(name = "producto")
	private Producto idProducto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Presentacion getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(Presentacion idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}
}
