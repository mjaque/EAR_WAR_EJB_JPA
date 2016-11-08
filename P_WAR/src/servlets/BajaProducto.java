package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOException;
import dao.DAOProducto;
import dao.DAOProductoRemote;
import dominio.Usuario;

/**
 * Servlet implementation class BajaProducto
 */
@WebServlet(description = "Realiza la baja de un producto", urlPatterns = { "/BajaProducto" })
public class BajaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DAOProductoRemote daoProducto;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BajaProducto() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TRON(BajaProducto.java): Cargado Baja servlet");
		
		try {
			Integer idProducto = Integer.valueOf(request.getParameter("idProducto"));
			DAOProducto dao = new DAOProducto();
			dao.baja(idProducto);
			String success = "La baja del producto se realizó correctamente.";
			request.setAttribute("success", success);
			if (((Usuario)request.getSession().getAttribute("usuario")).isAdmin())
				request.getRequestDispatcher("/AdminProductos").forward(request, response);
			else
				request.getRequestDispatcher("/MenuPrincipal").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(BajaProducto.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al procesar la baja del producto.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/MenuPrincipal").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
