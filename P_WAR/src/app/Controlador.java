package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		System.out.println("Recibida acción: " + accion);
		RequestDispatcher rd;

		if (accion == null) {
			System.out.println("Sin acción especificada");
			rd = request.getRequestDispatcher("/login.jsp");
		} else {
			switch (accion) {
			case "login":
				rd = request.getRequestDispatcher("/Login");
				break;
			case "ir_registro":
				rd = request.getRequestDispatcher("/registro.jsp");
				break;
			case "registro":
				rd = request.getRequestDispatcher("/Registro");
				break;
			case "baja":
				rd = request.getRequestDispatcher("/Baja");
				break;
			//Resto de acciones
			default:
				System.out.println("Acción desconocida: " + accion);
				rd = request.getRequestDispatcher("/login.jsp");
			}
		}
		rd.forward(request, response);

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
