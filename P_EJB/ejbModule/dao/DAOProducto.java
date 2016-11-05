package dao;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dominio.Producto;
import dominio.Usuario;
import dominio.Producto;


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
			manager.merge(producto);
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
	public Set<Producto> verProductos(Usuario usuario)  throws DAOException{
		System.out.println("TRON(DAOProducto.verProductos(" + usuario +").");
		return usuario.getProductos();
	}

}
