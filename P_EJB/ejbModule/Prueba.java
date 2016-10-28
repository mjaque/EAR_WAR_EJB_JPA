import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.DAOUsuario;

public class Prueba {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("manager1");

	public static void main(String[] args) {

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		
		DAOUsuario dao = new DAOUsuario();
		System.out.println(dao.login("mjaque", "mjaque"));
		
		dao.registro("Usuario_3", "clave", "email@email");

//		try {
//			transaction = manager.getTransaction();
//			transaction.begin();
//
//			Usuario usu = new Usuario();
//			usu.setNombre("Prueba 4");
//			usu.setClave("asdfasdf");
//			usu.setEmail("email.email");
//
//			manager.persist(usu);
//
//			transaction.commit();
//		} catch (Exception ex) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			ex.printStackTrace();
//		} finally {
//			manager.close();
//		}
//
//		// NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
//		ENTITY_MANAGER_FACTORY.close();
	}

}
