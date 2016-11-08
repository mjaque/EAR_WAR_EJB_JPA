package dao;

import java.util.List;

import javax.ejb.Remote;

import dominio.Usuario;


@Remote
public interface DAOUsuarioRemote {
	
	public Usuario login(String nombre, String clave) throws DAOException;
	public Usuario registro(Usuario nuevoUsuario) throws DAOException;
	public void modificar(Usuario usuario) throws DAOException;
	List<Usuario> verUsuarios() throws DAOException;
	Usuario getUsuario(Integer id) throws DAOException;
	void baja(Usuario usuario) throws DAOException;

}
