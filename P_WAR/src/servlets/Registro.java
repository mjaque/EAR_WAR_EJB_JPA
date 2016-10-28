package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;


/**
 * Servlet implementation class Registro
 */
@WebServlet(description = "Realiza el registro de nuevo usuario", urlPatterns = { "/Registro" })
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	@EJB
//	private DAOUsuarioRemote daoUsuario;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Cargado servlet Registro");
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		String claveRepetida = request.getParameter("claveRepetida");
		String email = request.getParameter("email");
		
		if (!clave.equals(claveRepetida)){
			//Volver al formulario de registro marcando un error
			System.out.println("Las claves no coinciden");
			String error = "Las claves no coinciden";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/registro.jsp").forward(request, response);
		}
		else{
//			DAOUsuario daoUsuario = new DAOUsuario();
//			Usuario nuevoUsuario = daoUsuario.registro(usuario, clave, email);
			Usuario nuevoUsuario = null;
			request.getSession().setAttribute("usuario", nuevoUsuario);
			request.getRequestDispatcher("/menu_principal.jsp").forward(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
