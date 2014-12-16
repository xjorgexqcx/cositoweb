package pe.com.cosito.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "presentacion_producto")
public class PresentacionProducto {

	private Integer idPresentacion;
	private Integer idProducto;
	private double Precio;
	
	public double getPrecio() {
		return Precio;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}
	
	@Id
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getIdPresentacion() {
		return idPresentacion;
	}
	public void setIdPresentacion(Integer idPresentacion) {
		this.idPresentacion = idPresentacion;
	}	
}