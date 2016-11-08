package servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOException;
import dao.DAOUsuario;
import dao.DAOUsuarioRemote;
import dominio.Usuario;

/**
 * Servlet implementation class IrModificarUsuario
 */
@WebServlet(description = "Carga la información del usuario elegido en el formulario", urlPatterns = { "/IrModificarUsuario" })
public class IrModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOUsuarioRemote daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IrModificarUsuario() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(IrModificarUsuario.java): Cargado servlet IrModificarUsuario");

		try {
			DAOUsuario dao = new DAOUsuario();
			Usuario usuario = dao.getUsuario(Integer.valueOf(request.getParameter("idUsuario")));
			request.setAttribute("usuario", usuario);
			System.out.println("TRON(IrModificarUsuario.java): OK. ");
			request.getRequestDispatcher("/modificar_usuario.jsp").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(IrModificarUsuario.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al cargar datos de usuario.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/listado_usuarios.jsp").forward(request, response);
		}

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
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
