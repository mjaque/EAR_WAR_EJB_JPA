package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOException;
import dao.DAOUsuario;
import dao.DAOUsuarioRemote;
import dominio.Producto;
import dominio.Usuario;

/**
 * Servlet implementation class AdminListadousuarios
 */
@WebServlet(description = "Muestra el listado de usuarios", urlPatterns = { "/AdminListadoUsuarios" })
public class AdminListadoUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOUsuarioRemote daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminListadoUsuarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(AdminListadoUsuarios.java): Cargado AdminListadoUsuarios servlet");

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (!usuario.isAdmin()){
				System.out.println("TRON(AdminListadoUsuarios.java): Permisos insuficientes.");
				String warning = "Permisos insuficientes.";
				request.setAttribute("warning", warning);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			DAOUsuario dao = new DAOUsuario();
			List<Usuario> listadoUsuarios = dao.verUsuarios();
			if (listadoUsuarios.size() == 0){
				String warning = "No hay usuarios";
				request.setAttribute("warning", warning);
			}
			request.setAttribute("listadoUsuarios", listadoUsuarios);
			request.getRequestDispatcher("/listado_usuarios.jsp").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(AdminListadoUsuarios.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al obtener el listado de usuarios.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/listado_usuarios.jsp").forward(request, response);
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
