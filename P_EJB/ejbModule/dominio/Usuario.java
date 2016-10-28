package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true, nullable = false, length = 50, name = "nombre")
	private String nombre;
	
	@Column(nullable = false, length = 32, name = "clave")
	private String clave;
	
	@Column(nullable = false, length = 100, name = "email")
	private String email;
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
}
