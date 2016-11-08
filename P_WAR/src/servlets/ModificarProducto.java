package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import app.Controlador;
import dao.DAOProducto;
import dao.DAOProductoRemote;
import dao.DAOUsuario;
import dao.DAOUsuarioRemote;
import dominio.Producto;
import dominio.Usuario;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet(description = "Modifica la información de un producto", urlPatterns = { "/ModificarProducto" })
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOProductoRemote daoProducto;
	
	@EJB
	private DAOUsuarioRemote daoUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(ModificarProducto.java): Cargado servlet ModificarProducto");
		
		request.setCharacterEncoding("UTF-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("TRON(ModificarProducto.java): isMultipart: " + isMultipart);

		Producto producto = new Producto();
		List<FileItem> items;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input
					// type="text|radio|checkbox|etc", select, etc).
					String fieldname = item.getFieldName();
					//Debería estar ya codificado en UTF, pero...
					String fieldvalue = new String(item.getString().getBytes("ISO-8859-1"));
					System.out.println("TRON(ModificarProducto.java): " + fieldname + "= " + fieldvalue);
					switch (fieldname) {
					case "idProducto":
						producto.setId(Integer.valueOf(fieldvalue));
						break;
					case "titulo":
						producto.setTitulo(fieldvalue);
						break;
					case "precio":
						producto.setPrecio(new BigDecimal(fieldvalue));
						break;
					case "categoria":
						producto.setCategoria(Producto.Categoria.valueOf(fieldvalue));
						break;
					case "estado":
						producto.setEstado(Producto.Estado.valueOf(fieldvalue));
						break;
					case "descripcion":
						producto.setDescripcion(fieldvalue);
						break;
					case "idUsuario":
						daoUsuario = new DAOUsuario();
						producto.setUsuario(daoUsuario.getUsuario(Integer.valueOf(fieldvalue)));
						break;
					}
				} else {
					// Process form file field (input type="file").
					String fieldname = item.getFieldName();
					String filename = FilenameUtils.getName(item.getName());
					InputStream filecontent = item.getInputStream();
					System.out
							.println("TRON(ModificarProducto.java): fieldname: " + fieldname + " filename: " + filename + " -> " + filecontent);
					if (fieldname.equals("foto")) {
						if (!filename.isEmpty()){
							producto.setUrlFoto(Controlador.URL_IMG+filename);
							FileItem uploaded = (FileItem) item;
							File fichero = new File(Controlador.DIR_IMG, uploaded.getName());
							uploaded.write(fichero);
						}
						else
							producto.setUrlFoto(null);
					}
				}
			}
			producto.setUsuario((Usuario) request.getSession().getAttribute("usuario"));
			DAOProducto daoProducto = new DAOProducto();
			daoProducto.modificar(producto);
			System.out.println("TRON(ModificarProducto.java): OK");
			String success = "El producto se modificó correctamente.";
			request.setAttribute("success", success);
			if (((Usuario)request.getSession().getAttribute("usuario")).isAdmin())
				request.getRequestDispatcher("/AdminProductos").forward(request, response);
			else
				request.getRequestDispatcher("/ListadoProductos").forward(request, response);
		} catch (Exception e) {
			System.out.println("TRON(ModificarProducto.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al modificar producto.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/producto.jsp").forward(request, response);
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
