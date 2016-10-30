package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOTest;
import dao.DAOTestRemote;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private DAOTestRemote daoTest;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TRON(servlet.Test).doGet(): accion = " + request.getParameter("accion"));
		response.getWriter().append("Ejecutando Test servlet");
		
		if (request.getParameter("accion").equals("testJDBC")){ 
			response.getWriter().append("Resultado testJDBC: ").append(daoTest.conexionJDBC());
		}
		if (request.getParameter("accion").equals("testHibernate")){ 
			response.getWriter().append("Resultado testHibernate: ").append(daoTest.conexionHibernate());
		}
		if (request.getParameter("accion").equals("testJPA")){ 
			response.getWriter().append("Resultado testJPA: ").append(daoTest.conexionJPA());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
