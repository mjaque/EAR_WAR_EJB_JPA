package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Producto;
import dominio.Producto.Categoria;
import dominio.Producto.Estado;
import dominio.Usuario;


/**
 * Session Bean implementation class DAOProducto
 */
@Stateless
public class DAOProducto implements DAOProductoRemote{
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("P1TIW");

	/**
	 * Default constructor.
	 */
	public DAOProducto() {
	}
	
	@Override
	public void alta(Producto nuevoProducto) throws DAOException{
		System.out.println("TRON(DAOProducto.alta("+nuevoProducto+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.alta()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(nuevoProducto);
			transaction.commit();
			System.out.println("TRON(DAOProducto.alta()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.alta()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al insertar los datos del producto.");
		} finally {
			manager.close();
		}
	}

	@Override
	public void modificar(Producto producto) throws DAOException{
		System.out.println("TRON(DAOProducto.modificar("+producto+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.modificar()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			Producto producto2 = this.getProducto(producto.getId());	//Lo cargamos de la BBDD para que esté en la transacción de JPA
			producto2.setCategoria(producto.getCategoria());
			producto2.setEstado(producto.getEstado());
			producto2.setDescripcion(producto.getDescripcion());
			producto2.setPrecio(producto.getPrecio());
			producto2.setTitulo(producto.getTitulo());
			System.out.println("TRON(DAOProducto.modificar()): urlFoto = " + producto.getUrlFoto());
			System.out.println("TRON(DAOProducto.modificar()): urlFoto (en BBDD) = " + producto2.getUrlFoto());
			if (producto.getUrlFoto() != null){	//Si es null, no se modifica
				producto2.setUrlFoto(producto.getUrlFoto());
			}
			producto2.setUsuario(producto.getUsuario());
			manager.merge(producto2);
			transaction.commit();
			System.out.println("TRON(DAOProducto.modificar()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.modificar()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al modificar los datos del Producto.");
		} finally {
			manager.close();
		}
	}

	public void baja(Producto Producto) throws DAOException{
		System.out.println("TRON(DAOProducto.baja("+Producto+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.baja()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			if (!manager.contains(Producto))
				Producto = manager.merge(Producto);			
			manager.remove(Producto);
			transaction.commit();
			System.out.println("TRON(DAOProducto.baja()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.baja()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al dar de baja al Producto.");
		} finally {
			manager.close();
		}
	}

	@Override
	public List<Producto> verProductos(Usuario usuario) throws DAOException{
		System.out.println("TRON(DAOProducto.verProductos(" + usuario +").");
		List<Producto> productos = null;
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.verProductos()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			productos = (List<Producto>)manager.createQuery("SELECT p FROM Producto p WHERE idUsuario = :idUsuario")
					.setParameter("idUsuario", usuario.getId())
					.getResultList(); 
			transaction.commit();
			System.out.println("TRON(DAOProducto.verProductos()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.verProductos()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al buscar Productos de usuario.");
		} finally {
			manager.close();
		}
		return productos;
	}

	@Override
	public List<Producto> verProductos() throws DAOException{
		System.out.println("TRON(DAOProducto.verProductos()):");
		List<Producto> productos = null;
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.verProductos()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			productos = (List<Producto>)manager.createQuery("SELECT p FROM Producto p")
					.setMaxResults(5)
					.getResultList(); 
			transaction.commit();
			System.out.println("TRON(DAOProducto.verProductos()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.verProductos()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al buscar Productos.");
		} finally {
			manager.close();
		}
		return productos;
	}

	@Override
	public List<Producto> buscarProductos(String criterio) throws DAOException{
		System.out.println("TRON(DAOProducto.buscarProductos()):");
		List<Producto> productos = null;
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.buscarProductos()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			productos = (List<Producto>)manager.createQuery("SELECT p FROM Producto p "
					+ "WHERE LOWER(titulo) LIKE :criterio OR LOWER(descripcion) LIKE :criterio")
					.setParameter("criterio", "%"+criterio.toLowerCase()+"%")
					.getResultList(); 
			transaction.commit();
			System.out.println("TRON(DAOProducto.buscarProductos()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.buscarProductos()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al buscar Productos (con criterio).");
		} finally {
			manager.close();
		}
		return productos;	
	}

	@Override
	public Producto getProducto(Integer id) throws DAOException {
		System.out.println("TRON(DAOProducto.getProducto(" + id +")):");
		Producto producto = null;
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.getProducto()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			producto = (Producto)manager.createQuery("SELECT p FROM Producto p "
					+ "WHERE id = :id")
					.setParameter("id", id)
					.getSingleResult(); 
			transaction.commit();
			System.out.println("TRON(DAOProducto.getProducto()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.getProducto()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al buscar Producto("+id+").");
		} finally {
			manager.close();
		}
		return producto;
	}

	@Override
	public void baja(Integer idProducto) throws DAOException{
		System.out.println("TRON(DAOProducto.baja("+idProducto+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.baja()): EntityManager creado.");
			transaction = manager.getTransaction();
			Producto producto = this.getProducto(idProducto);
			transaction.begin();
			if (!manager.contains(producto))
				producto = manager.merge(producto);			
			manager.remove(producto);
			transaction.commit();
			System.out.println("TRON(DAOProducto.baja()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.baja()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al dar de baja el producto.");
		} finally {
			manager.close();
		}
		
	}

	@Override
	public List<Producto> buscarProductos(String titulo, BigDecimal precio, Categoria categoria, Estado estado,
			String descripcion) throws DAOException{
		System.out.println("TRON(DAOProducto.buscarProductos()): " + titulo + ", " + precio  + ", " + categoria +  ", " + estado  + ", " + descripcion);
		List<Producto> productos = null;
		
		//TODO: enums vienen a null si no se han rellenado. ¿Y los textos?
		//NumberFormatException en el servlet si no hay precio.
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOProducto.buscarProductos()): EntityManager creado.");
			String sql = "SELECT p FROM Producto p";
			ArrayList<String> where = new ArrayList<>();
			if (!titulo.isEmpty())
				where.add("LOWER(titulo) LIKE :titulo");
			if (!descripcion.isEmpty())
				where.add("LOWER(descripcion) LIKE :descripcion");
			if (precio != null)
				where.add("precio = :precio");
			if (categoria != null)
				where.add("categoria = :categoria");
			if (estado != null)
				where.add("estado = :estado");
			String strWhere = "";
			if (where.size() > 0){
				strWhere = " WHERE ";
				for (String criterio : where){
					strWhere += criterio + " AND ";
				}
				strWhere = strWhere.substring(0, strWhere.length() - 4);
			}
			sql += strWhere;
			System.out.println("TRON(DAOProducto.buscarProductos()): SQL: " + sql);
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery(sql);
			if (!titulo.isEmpty())
					query.setParameter("titulo", "%"+titulo.toLowerCase()+"%");
			if (!descripcion.isEmpty())
					query.setParameter("descripcion", "%"+descripcion.toLowerCase()+"%");
			if (precio != null)
				query.setParameter("precio", precio);
			if (categoria != null)
					query.setParameter("categoria", categoria);
			if (estado != null)
					query.setParameter("estado", estado);
			productos = (List<Producto>)query.getResultList(); 
			transaction.commit();
			System.out.println("TRON(DAOProducto.buscarProductos()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOProducto.buscarProductos()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al buscar Productos (con criterios).");
		} finally {
			manager.close();
		}
		return productos;
	}

}
