package dao;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import dominio.Producto;
import dominio.Usuario;


@Remote
public interface DAOProductoRemote {
	
	public void alta(Producto nuevoProducto) throws DAOException;
	public void modificar(Producto producto) throws DAOException;
	public void baja(Producto Producto) throws DAOException;
	public List<Producto> verProductos(Usuario usuario) throws DAOException;
	public List<Producto> verProductos() throws DAOException;
	public List<Producto> buscarProductos(String parameter) throws DAOException;
	public Producto getProducto(Integer id) throws DAOException;
	void baja(Integer idProducto) throws DAOException;

}
