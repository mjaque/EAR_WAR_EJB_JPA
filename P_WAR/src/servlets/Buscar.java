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
@WebServlet(description = "Muestra el listado de productos resultado de la Búsqueda", urlPatterns = { "/Buscar" })
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOProductoRemote daoProducto;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Buscar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(Buscar.java): Cargado Buscar servlet");

		try {
			DAOProducto dao = new DAOProducto();
			List<Producto> listadoProductos = dao.buscarProductos(request.getParameter("criterio"));
			if (listadoProductos.size() == 0){
				String warning = "No hay productos";
				request.setAttribute("warning", warning);
			}
			request.setAttribute("listadoProductos", listadoProductos);
			request.getRequestDispatcher("/ListadoProductos").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(Buscar.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al obtener el resultados de búsqueda de productos.";
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
