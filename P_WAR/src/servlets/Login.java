package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRemote;
import dominio.Usuario;

//import ejb.Prueba;
//import ejb.PruebaInterface;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DAOUsuarioRemote daoUsuario;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Cargado Login servlet");
		
		// Control de Acceso
		//Usuario usuario = null;
		daoUsuario.test();
		Usuario usuario = daoUsuario.login(request.getParameter("usuario"), request.getParameter("clave"));
		if (usuario == null) {
			// Fallo de Acceso
			String error = "Fallo de login";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
			request.getSession().setAttribute("usuario", usuario);
			request.getRequestDispatcher("/menu_principal.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
