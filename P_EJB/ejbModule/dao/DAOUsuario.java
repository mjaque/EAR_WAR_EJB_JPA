package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	public Usuario login(String email, String clave) {
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
			resultado = query.getSingleResult();
			transaction.commit();
			System.out.println("TRON(DAOUsuario.login()): OK.");
		} catch (Exception ex) {
			System.out.println("TRON(DAOUsuario.login()): KO " + ex.getMessage());
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}

		return resultado;
	}

	@Override
	public Usuario registro(Usuario nuevoUsuario) {
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
		} finally {
			manager.close();
		}

		return nuevoUsuario;
	}

}
