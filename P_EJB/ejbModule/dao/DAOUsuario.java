package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dominio.Producto;
import dominio.Usuario;


/**
 * Session Bean implementation class DAOUsuario
 */
@Stateless
public class DAOUsuario implements DAOUsuarioRemote {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("P1TIW");

	/**
	 * Default constructor.
	 */
	public DAOUsuario() {
	}
	
	@Override
	public Usuario login(String email, String clave) throws DAOException{
		System.out.println("TRON((DAOUsuario.login(" + email + ", " + clave + "):");
		Usuario resultado = null;

		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOUsuario.login()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			TypedQuery<Usuario> query = manager
					.createQuery("SELECT u FROM Usuario u WHERE email = :email AND clave = MD5(:clave)", Usuario.class);
			query.setParameter("email", email);
			query.setParameter("clave", clave);
			resultado = (Usuario)query.getSingleResult();
			transaction.commit();
			System.out.println("TRON(DAOUsuario.login()): OK. " + resultado);
		} catch (Exception ex) {
			System.out.println("TRON(DAOUsuario.login()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error logear al usuario.");
		} finally {
			manager.close();
		}
		return resultado;
	}

	@Override
	public Usuario registro(Usuario nuevoUsuario) throws DAOException{
		System.out.println("TRON(DAOUsuario.registro("+nuevoUsuario+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOUsuario.registro()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(nuevoUsuario);
			transaction.commit();
			System.out.println("TRON(DAOUsuario.registro()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOUsuario.registro()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al modificar los datos del usuario.");
		} finally {
			manager.close();
		}

		return nuevoUsuario;
	}

	@Override
	public void modificar(Usuario usuario) throws DAOException{
		System.out.println("TRON(DAOUsuario.modificar("+usuario+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOUsuario.modificar()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(usuario);
			transaction.commit();
			System.out.println("TRON(DAOUsuario.modificar()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOUsuario.modificar()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al modificar los datos del usuario.");
		} finally {
			manager.close();
		}
	}

	public void baja(Usuario usuario) throws DAOException{
		System.out.println("TRON(DAOUsuario.baja("+usuario+")):");
		
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			System.out.println("TRON(DAOUsuario.baja()): EntityManager creado.");
			transaction = manager.getTransaction();
			transaction.begin();
			if (!manager.contains(usuario))
				usuario = manager.merge(usuario);			
			manager.remove(usuario);
			transaction.commit();
			System.out.println("TRON(DAOUsuario.baja()): OK");
		} catch (Exception ex) {
			System.out.println("TRON(DAOUsuario.baja()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
			throw new DAOException("Error al dar de baja al usuario.");
		} finally {
			manager.close();
		}
	}

	public List<Producto> verProductos(Usuario usuario)  throws DAOException{
		DAOProducto daoProducto = new DAOProducto();
		return daoProducto.verProductos(usuario);
	}

}
