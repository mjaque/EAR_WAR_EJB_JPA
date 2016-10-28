package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dominio.Usuario;

/**
 * Session Bean implementation class DAOUsuario
 */
@Stateless
public class DAOUsuario implements DAOUsuarioRemote {
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("manager1");

	/**
	 * Default constructor.
	 */
	public DAOUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void test() {
		System.out.println("TRON: Ejecutando DAOUsuario.test()");
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/modelowebapp?" +
                               "user=root&password=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			System.out.println("TRON: Conexión a MySQL realizada.");
//			
			Configuration conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			System.out.println("TRON: Cargada configuración de hiberante.");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			System.out.println("TRON: ServiceRegistry creado.");
			try {
				SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
				System.out.println("TRON: SessionFactory creada.");
				System.out.println("TRON: Prueba Hibernate superada.");
			} catch (Exception e) {
				System.out.println("TRON: " + e.getMessage());
				e.printStackTrace();
			}		
		}
		catch(Exception e){
			System.out.println("TRON: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public Usuario login(String nombre, String clave) {
		System.out.println("En dao.Usuario.login(" + nombre + ", " + clave + ")");
		System.out.println("TRON1: " + ENTITY_MANAGER_FACTORY);
		Usuario resultado = null;

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		System.out.println("TRON2.");

		try {
			transaction = manager.getTransaction();
			System.out.println("TRON3.");
			transaction.begin();
			System.out.println("TRON4.");
			TypedQuery<Usuario> query = manager
					.createQuery("SELECT u FROM Usuario u WHERE nombre = :nombre AND clave = MD5(:clave)", Usuario.class);
			query.setParameter("nombre", nombre);
			query.setParameter("clave", clave);
			resultado = query.getSingleResult();
			transaction.commit();
		} catch (Exception ex) {
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
	public Usuario registro(String usuario, String clave, String email) {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(usuario);
		nuevoUsuario.setClave(clave);
		nuevoUsuario.setEmail(email);

//		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
//		EntityTransaction transaction = null;
//
//		try {
//			transaction = manager.getTransaction();
//			transaction.begin();
//			manager.persist(nuevoUsuario);
//			transaction.commit();
//		} catch (Exception ex) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			ex.printStackTrace();
//		} finally {
//			manager.close();
//		}

		return nuevoUsuario;
	}

}
