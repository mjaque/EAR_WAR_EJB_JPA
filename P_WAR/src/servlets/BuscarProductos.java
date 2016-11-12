package servlets;

import java.io.IOException;
import java.math.BigDecimal;
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
import dominio.Producto.Categoria;
import dominio.Producto.Estado;

/**
 * Servlet implementation class BuscarProductos
 */
@WebServlet(description = "Muestra el listado de productos según los criterios de búsqueda", urlPatterns = { "/BuscarProductos" })
public class BuscarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOProductoRemote daoProducto;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuscarProductos() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(BuscarProductos.java): Cargado ListadoProductos servlet");

		try {
			//Recogemos los parámetros
			String titulo = request.getParameter("titulo");
			BigDecimal precio;
			if (request.getParameter("precio").isEmpty())
				precio = null;
			else
				precio = new BigDecimal(request.getParameter("precio"));
			Producto.Categoria categoria;
			if (request.getParameter("categoria").equals("Cualquiera"))
			 categoria = null;
			else
				categoria = Categoria.valueOf(request.getParameter("categoria"));
			Producto.Estado estado;
			if (request.getParameter("estado").equals("Cualquiera"))
				 estado = null;
				else
					estado = Estado.valueOf(request.getParameter("estado"));
			String descripcion = request.getParameter("descripcion");
			DAOProducto dao = new DAOProducto();
			List<Producto> listadoProductos = dao.buscarProductos(titulo, precio, categoria, estado, descripcion);
			if (listadoProductos.size() == 0){
				String warning = "No hay productos";
				request.setAttribute("warning", warning);
			}
			request.setAttribute("listadoProductos", listadoProductos);
			request.getRequestDispatcher("/listado_productos.jsp").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(BuscarProductos.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al obtener el listado de productos de búsqueda.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/buscar_productos.jsp").forward(request, response);
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
