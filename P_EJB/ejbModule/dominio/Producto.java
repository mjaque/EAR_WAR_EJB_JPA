package dominio;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, length = 50, name = "titulo")
	private String titulo;
	
	@Column(nullable = false, length = 500, name = "descripcion")
	private String descripcion;
	
	@Column(nullable = false, length = 50, name = "url_foto")
	private String urlFoto;
	
	@Column(nullable = false, name = "precio")
	private BigDecimal precio;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	public enum Categoria {Vehiculos, Moda, Electrodomesticos, Libros};
	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public enum Estado {Disponible, Reservado, Vendido};
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
        if (!usuario.getProductos().contains(this)) {
            usuario.addProducto(this);
        }
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", urlFoto=" + urlFoto
				+ ", precio=" + precio + ", categoria=" + categoria + "]";
	}
	
}
