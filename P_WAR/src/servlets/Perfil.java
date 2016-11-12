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
 * Servlet implementation class Perfil
 */
@WebServlet(description = "Modifica la información del perfil del usuario", urlPatterns = { "/Perfil" })
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOUsuarioRemote daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Perfil() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(Perfil.java): Cargado servlet Perfil");

		try {
			String email = request.getParameter("email");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String ciudad = request.getParameter("ciudad");
			String clave = request.getParameter("clave");
			String claveRepetida = request.getParameter("claveRepetida");

			if (!clave.equals(claveRepetida)) {
				// Volver al formulario de registro marcando un error
				System.out.println("TRON(Perfil.java): Las claves no coinciden.");
				String error = "Las claves no coinciden";
				request.setAttribute("error", error);
				request.setAttribute("usuario", request.getSession().getAttribute("usuario"));
				request.getRequestDispatcher("/perfil.jsp").forward(request, response);
			} else {
				DAOUsuario daoUsuario = new DAOUsuario();
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				usuario.setEmail(email);
				usuario.setNombre(nombre);
				usuario.setApellidos(apellidos);
				usuario.setCiudad(ciudad);

				if (!clave.equals("****")) {
					// Encriptamos la clave
					StringBuffer sbClaveEncriptada = new StringBuffer();
					try {
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(clave.getBytes());

						byte byteData[] = md.digest();
						for (int i = 0; i < byteData.length; i++) {
							sbClaveEncriptada.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
						}
						usuario.setClave(sbClaveEncriptada.toString());
					} catch (NoSuchAlgorithmException e) {
						System.out.println("TRON(Perfil.jsp): No existe el algoritmo de encriptación MD5.");
						e.printStackTrace();
						request.setAttribute("error", "No existe el algoritmo de encriptación MD5");
						request.setAttribute("usuario", request.getSession().getAttribute("usuario"));
						request.getRequestDispatcher("/perfil.jsp").forward(request, response);
					}
				}

				daoUsuario.modificar(usuario);
				request.setAttribute("success", "Datos de usuario modificados correctamente");
				System.out.println("TRON(Perfil.java): OK");
				request.setAttribute("usuario", request.getSession().getAttribute("usuario"));
				request.getRequestDispatcher("/perfil.jsp").forward(request, response);
			}
		} catch (DAOException e) {
			System.out.println("TRON(Perfil.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al modificar datos de usuario.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/perfil.jsp").forward(request, response);
		}

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
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
