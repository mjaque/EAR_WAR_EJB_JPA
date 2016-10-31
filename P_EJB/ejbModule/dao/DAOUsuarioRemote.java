package dao;

import javax.ejb.Remote;

import dominio.Usuario;


@Remote
public interface DAOUsuarioRemote {
	
	public Usuario login(String nombre, String clave) throws DAOException;
	public Usuario registro(Usuario nuevoUsuario) throws DAOException;
	public void modificar(Usuario usuario) throws DAOException;

}
