package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dominio.Usuario;

/**
 * Session Bean implementation class DAOTest
 */
@Stateless
public class DAOTest implements DAOTestRemote {

    /**
     * Default constructor. 
     */
    public DAOTest() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String conexionJDBC() {
		System.out.println("TRON(DAOTest.conexionJDBC():");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("TRON: " + e.getMessage());
			e.printStackTrace();
			return "KO - " + e.getMessage();
		}
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/practica1?user=root&password=root");){
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE test (id int auto_increment primary key)");
			System.out.println("TRON: Conexión a MySQL realizada.");
			statement.close();
			return "Ok";
		}
		catch(Exception e){
			System.out.println("TRON: " + e.getMessage());
			e.printStackTrace();
			return "KO - " + e.getMessage();
		}
	}

	@Override
	public String conexionHibernate() {
		System.out.println("TRON(DAOTest.conexionHibernate():");
		try{
			Configuration conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			System.out.println("TRON: Cargada configuración de hiberante.");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			System.out.println("TRON: ServiceRegistry creado.");
			Usuario usuario = new Usuario();
			usuario.setApellidos("Apellidos");
			usuario.setCiudad("Ciudad");
			usuario.setClave("clave");
			usuario.setEmail("email");
			usuario.setNombre("Nombre");
			try (SessionFactory sessionFactory = conf.buildSessionFactory(serviceRegistry);
				Session session = sessionFactory.openSession();){
				System.out.println("TRON: SessionFactory y Session creadas.");
				long id = (Long) session.save(usuario);
				session.flush();
				System.out.println("TRON: Creado usuario con id=" + id);
				session.close();
				sessionFactory.close();
				return "Ok";
			} catch (Exception e) {
				System.out.println("TRON: " + e.getMessage());
				e.printStackTrace();
				return "KO - " + e.getMessage();
			}
		}
		catch(Exception e){
			System.out.println("TRON: " + e.getMessage());
			e.printStackTrace();
			return "KO - " + e.getMessage();
		}
	}

	@Override
	public String conexionJPA() {
		System.out.println("TRON(DAOTest.conexionJPA():");
		
		EntityManagerFactory entityManagerFactory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("P1TIW");
			manager = entityManagerFactory.createEntityManager();
			System.out.println("TRON: EntityManager creado.");
			Usuario usuario = new Usuario();
			usuario.setApellidos("Apellidos");
			usuario.setCiudad("Ciudad");
			usuario.setClave("clave");
			usuario.setEmail("email");
			usuario.setNombre("Nombre");
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(usuario);
			transaction.commit();
			System.out.println("TRON: Usuario creado.");
			return "Ok";
		} catch (Exception e) {
			System.out.println("TRON(DAOTest.conexionJPA(): " + e.getMessage());
			e.printStackTrace();
			return "KO - " + e.getMessage();
		}
		finally {
			manager.close();
			entityManagerFactory.close();
		}
		
	}

}
