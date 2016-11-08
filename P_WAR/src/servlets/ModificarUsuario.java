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
 * Servlet implementation class ModificarUsuario
 */
@WebServlet(description = "Modifica la información de un usuario", urlPatterns = { "/ModificarUsuario" })
public class ModificarUsuario extends HttpServlet {
	//TODO: Habría que refactorizar este servlet con Perfil
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOUsuarioRemote daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarUsuario() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(ModificarUsuario.java): Cargado servlet ModificarUsuario");
		
		try {
			String email = request.getParameter("email");
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String ciudad = request.getParameter("ciudad");
			String clave = request.getParameter("clave");
			String claveRepetida = request.getParameter("claveRepetida");

			if (!clave.equals(claveRepetida)) {
				// Volver al formulario de registro marcando un error
				System.out.println("TRON(ModificarUsuario.java): Las claves no coinciden.");
				String error = "Las claves no coinciden";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/modificar_usuario.jsp").forward(request, response);
			} else {
				DAOUsuario daoUsuario = new DAOUsuario();
				Usuario usuario = daoUsuario.getUsuario(Integer.valueOf(request.getParameter("idUsuario")));
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
						System.out.println("TRON(ModificarUsuario.jsp): No existe el algoritmo de encriptación MD5.");
						e.printStackTrace();
						request.setAttribute("error", "No existe el algoritmo de encriptación MD5");
						request.getRequestDispatcher("/modificar_usuario.jsp").forward(request, response);
					}
				}

				daoUsuario.modificar(usuario);
				request.setAttribute("success", "Datos de usuario modificados correctamente");
				System.out.println("TRON(ModificarUsuario.java): OK");
				request.getRequestDispatcher("/AdminListadoUsuarios").forward(request, response);
			}
		} catch (DAOException e) {
			System.out.println("TRON(ModificarUsuario.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al modificar datos de usuario.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/modificar_usuario.jsp").forward(request, response);
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
