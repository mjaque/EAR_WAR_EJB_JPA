package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOException;
import dao.DAOUsuarioRemote;
import dominio.Usuario;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(Login.java): Cargado Login servlet");
		
		try {
			// Control de Acceso
			Usuario usuario = daoUsuario.login(request.getParameter("email"), request.getParameter("clave"));
			if (usuario == null) {
				// Fallo de Acceso
				System.out.println("TRON(Login.java): Fallo de login");
				String warning = "Fallo de login";
				request.setAttribute("warning", warning);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else{
				System.out.println("TRON(Login.java): OK");
				System.out.println("TRON(Login.java): "+ usuario);
				request.getSession().setAttribute("usuario", usuario);
				request.getRequestDispatcher("/menu_principal.jsp").forward(request, response);
			}
		} catch (DAOException e) {
			System.out.println("TRON(Login.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al intentar el login.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
