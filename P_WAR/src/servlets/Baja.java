package servlets;

import java.io.IOException;

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
 * Servlet implementation class Baja
 */
@WebServlet(description = "Realiza la baja del usuario", urlPatterns = { "/Baja" })
public class Baja extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DAOUsuarioRemote daoUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Baja() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TRON(Baja.java): Cargado Baja servlet");
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			DAOUsuario dao = new DAOUsuario();
			dao.baja(usuario);
			String success = "La baja del usuario se realizó correctamente.";
			request.setAttribute("success", success);
			request.getRequestDispatcher("/Controlador").forward(request, response);
		} catch (DAOException e) {
			System.out.println("TRON(Baja.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al procesar la baja.";
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
