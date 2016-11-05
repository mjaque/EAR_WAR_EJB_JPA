package servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import dominio.Producto;
import dominio.Usuario;

/**
 * Servlet implementation class AltaProducto
 */
@WebServlet(description = "Realiza el alta de nuevo producto", urlPatterns = { "/AltaProducto" })
@MultipartConfig
public class AltaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DAOProductoRemote daoProducto;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AltaProducto() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TRON(AltaProducto.java): Cargado servlet AltaProducto");

		request.setCharacterEncoding("UTF-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("TRON: isMultipart: " + isMultipart);

		List<FileItem> items;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			Producto nuevoProducto = new Producto();
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input
					// type="text|radio|checkbox|etc", select, etc).
					String fieldname = item.getFieldName();
					//Debería estar ya codificado en UTF, pero...
					String fieldvalue = new String(item.getString().getBytes("ISO-8859-1"));
					System.out.println("TRON: " + fieldname + "= " + fieldvalue);
					switch (fieldname) {
					case "titulo":
						nuevoProducto.setTitulo(fieldvalue);
						break;
					case "precio":
						nuevoProducto.setPrecio(new BigDecimal(fieldvalue));
						break;
					case "categoria":
						nuevoProducto.setCategoria(Producto.Categoria.valueOf(fieldvalue));
						break;
					case "descripcion":
						nuevoProducto.setDescripcion(fieldvalue);
						break;
					}
				} else {
					// Process form file field (input type="file").
					String fieldname = item.getFieldName();
					String filename = FilenameUtils.getName(item.getName());
					InputStream filecontent = item.getInputStream();
					System.out
							.println("TRON: fieldname: " + fieldname + " filename: " + filename + " -> " + filecontent);
					if (fieldname.equals("foto")) {
						nuevoProducto.setUrlFoto(Controlador.URL_IMG+filename);
						FileItem uploaded = (FileItem) item;
						File fichero = new File(Controlador.DIR_IMG, uploaded.getName());
						uploaded.write(fichero);
					}
				}
			}
			nuevoProducto.setUsuario((Usuario) request.getSession().getAttribute("usuario"));
			DAOProducto daoProducto = new DAOProducto();
			daoProducto.alta(nuevoProducto);
			System.out.println("TRON(AltaProducto.java): OK");
			String success = "El producto se registró correctamente.";
			request.setAttribute("success", success);
			request.getRequestDispatcher("?accion=ir_listado_productos").forward(request, response);
		} catch (Exception e) {
			System.out.println("TRON(AltaProducto.java): KO. " + e.getMessage());
			e.printStackTrace();
			String error = "Error al insertar producto.";
			request.setAttribute("error", error);
			request.getRequestDispatcher("?accion=ir_alta_producto").forward(request, response);
		}

		// Part filePart = request.getPart("file"); // Retrieves <input
		// type="file" name="file">
		// String fileName =
		// Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		// // MSIE fix.
		// InputStream fileContent = filePart.getInputStream();
		/*
		 * File file; int maxFileSize = 5000 * 1024; int maxMemSize = 5000 *
		 * 1024; String filePath =
		 * this.getServletContext().getInitParameter("file-upload");
		 * 
		 * // Verify the content type String contentType =
		 * request.getContentType(); if
		 * ((contentType.indexOf("multipart/form-data") >= 0)) {
		 * 
		 * DiskFileItemFactory factory = new DiskFileItemFactory(); // maximum
		 * size that will be stored in memory
		 * factory.setSizeThreshold(maxMemSize); // Location to save data that
		 * is larger than maxMemSize. factory.setRepository(new File("\tmp"));
		 * 
		 * // Create a new file upload handler ServletFileUpload upload = new
		 * ServletFileUpload(factory); // maximum file size to be uploaded.
		 * upload.setSizeMax(maxFileSize); try { // Parse the request to get
		 * file items. List<FileItem> fileItems = upload.parseRequest(request);
		 * 
		 * // Process the uploaded file items Iterator<FileItem> i =
		 * fileItems.iterator(); while (i.hasNext()) { FileItem fi = (FileItem)
		 * i.next(); if (!fi.isFormField()) { // Get the uploaded file
		 * parameters String fieldName = fi.getFieldName(); String fileName =
		 * fi.getName(); boolean isInMemory = fi.isInMemory(); long sizeInBytes
		 * = fi.getSize(); // Write the file if (fileName.lastIndexOf("\\") >=
		 * 0) { file = new File(filePath +
		 * fileName.substring(fileName.lastIndexOf("\\"))); } else { file = new
		 * File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
		 * } fi.write(file); System.out.println("Uploaded Filename: " + filePath
		 * + fileName); } } } catch (Exception ex) { System.out.println(ex); } }
		 * else { System.out.println("No uploaded file"); }
		 */
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
