package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, length = 50, name = "nombre")
	private String nombre;
	
	@Column(nullable = false, length = 50, name = "apellidos")
	private String apellidos;
	
	@Column(nullable = false, length = 32, name = "clave")
	private String clave;
	
	@Column(nullable = false, length = 100, name = "email")
	private String email;
	
	@Column(nullable = false, length = 50, name = "ciudad")
	private String ciudad;
	
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	 private List<Producto> productos = new ArrayList<>();
	 
	 @Column(nullable = false, name = "admin")
	 private Boolean admin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public boolean addProducto(Producto producto) {
		boolean result = this.productos.add(producto);
		if (producto.getUsuario() != this) {
            producto.setUsuario(this);
        }
		return result;
	}
	
	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", clave=" + clave + ", email="
				+ email + ", ciudad=" + ciudad + ", productos=" + productos + "]";
	}
	
}
