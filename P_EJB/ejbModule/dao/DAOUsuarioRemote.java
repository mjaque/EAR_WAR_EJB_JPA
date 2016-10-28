package dao;

import javax.ejb.Remote;

import dominio.Usuario;

@Remote
public interface DAOUsuarioRemote {
	
	public Usuario login(String nombre, String clave);
	public Usuario registro(String usuario, String clave, String email);
	public void test();

}
