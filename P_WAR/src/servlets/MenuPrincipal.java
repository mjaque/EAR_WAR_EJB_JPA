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

/**
 * Servlet implementation class ListadoProductos
 */
@WebServlet(description = "Muestra un listado de productos aleatorio", urlPatterns = { "/MenuPrincipal" })
public class MenuPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOProductoRemote daoProducto;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuPrincipal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(MenuPrincipal.java): Cargado MenuPrincipal servlet");

		try {
			DAOProducto dao = new DAOProducto();
			List<Producto> listadoProductos = dao.verProductos();
			if (listadoProductos.size() == 0){
				String warning = "No hay productos";
				request.setAttribute("warning", warning);
			}
			request.setAttribute("listadoProductos", listadoProductos);
			request.getRequestDispatcher("/menu_principal.jsp").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(MenuPrincipal.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al obtener el listado de productos.";
			request.setAttribute("error", error);
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
