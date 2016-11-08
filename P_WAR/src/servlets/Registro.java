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
 * Servlet implementation class Registro
 */
@WebServlet(description = "Realiza el registro de nuevo usuario", urlPatterns = { "/Registro" })
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DAOUsuarioRemote daoUsuario;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TRON(Registro.java): Cargado servlet Registro");
		
		try {
			String email = request.getParameter("email");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String ciudad = request.getParameter("ciudad");
			String clave = request.getParameter("clave");
			String claveRepetida = request.getParameter("claveRepetida");
			
			if (!clave.equals(claveRepetida)){
				//Volver al formulario de registro marcando un error
				System.out.println("TRON(Registro.java): Las claves no coinciden.");
				String warning = "Las claves no coinciden";
				request.setAttribute("warning", warning);
				request.getRequestDispatcher("/registro.jsp").forward(request, response);
			}
			else{
				//Encriptamos la clave
				StringBuffer sbClaveEncriptada = new StringBuffer();
				try {
					MessageDigest md = MessageDigest.getInstance("MD5");
					 md.update(clave.getBytes());

				        byte byteData[] = md.digest();
				        for (int i = 0; i < byteData.length; i++) {
				         sbClaveEncriptada.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				        }
				} catch (NoSuchAlgorithmException e) {
					System.out.println("TRON(Registro.java): No existe el algoritmo de encriptación MD5.");
					e.printStackTrace();
					request.setAttribute("error", "No existe el algoritmo de encriptación MD5");
					request.getRequestDispatcher("/registro.jsp").forward(request, response);
				}

				DAOUsuario daoUsuario = new DAOUsuario();
				Usuario nuevoUsuario = new Usuario();
				nuevoUsuario.setEmail(email);
				nuevoUsuario.setNombre(nombre);
				nuevoUsuario.setApellidos(apellidos);
				nuevoUsuario.setClave(sbClaveEncriptada.toString());
				nuevoUsuario.setCiudad(ciudad);
				nuevoUsuario.setAdmin(false);
				
				nuevoUsuario = daoUsuario.registro(nuevoUsuario);
				request.getSession().setAttribute("usuario", nuevoUsuario);
				System.out.println("TRON(Registro.java): OK");
				request.getRequestDispatcher("/MenuPrincipal").forward(request, response);
			}
		} catch (DAOException e) {
			System.out.println("TRON(Regisstro.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al insertar datos de usuario.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/registro.jsp").forward(request, response);
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
