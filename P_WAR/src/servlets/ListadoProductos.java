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
 * Servlet implementation class ListadoProductos
 */
@WebServlet(description = "Muestra el listado de productos del usuario", urlPatterns = { "/ListadoProductos" })
public class ListadoProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOUsuarioRemote daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoProductos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(ListadoProductos.java): Cargado ListadoProductos servlet");

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			DAOUsuario dao = new DAOUsuario();
			List<Producto> listadoProductos = dao.verProductos(usuario);
			if (listadoProductos.size() == 0){
				String warning = "No hay productos";
				request.setAttribute("warning", warning);
			}
			request.setAttribute("listadoProductos", listadoProductos);
			request.getRequestDispatcher("/listado_productos.jsp").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(ListadoProductos.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al obtener el listado de productos del usuario.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/MenuPrincipal").forward(request, response);
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
