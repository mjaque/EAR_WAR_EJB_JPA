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
import dao.DAOProducto;
import dao.DAOProductoRemote;
import dominio.Producto;
import dominio.Usuario;

/**
 * Servlet implementation class AdminProductos
 */
@WebServlet(description = "Muestra el listado de productos", urlPatterns = { "/AdminProductos" })
public class AdminProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOProductoRemote daoProducto;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminProductos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(AdminProductos.java): Cargado AdminProductos servlet");

		try {
			//TODO: Hay que refactorizar la seguridad en todas las clases (de administración y de usuario)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (!usuario.isAdmin()){
				System.out.println("TRON(AdminProductos.java): Permisos insuficientes.");
				String warning = "Permisos insuficientes.";
				request.setAttribute("warning", warning);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			DAOProducto dao = new DAOProducto();
			List<Producto> listadoProductos = dao.verProductos();
			if (listadoProductos.size() == 0){
				String warning = "No hay productos";
				request.setAttribute("warning", warning);
			}
			request.setAttribute("listadoProductos", listadoProductos);
			System.out.println("TRON(AdminProductos.java): OK.");
			request.getRequestDispatcher("/listado_productos_admin.jsp").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(AdminProductos.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al obtener el listado de productos.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/listado_productos_admin.jsp").forward(request, response);
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
