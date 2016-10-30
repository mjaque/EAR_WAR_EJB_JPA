package dao;

import javax.ejb.Remote;

@Remote
public interface DAOTestRemote {
	
	public String conexionJDBC();
    public String conexionHibernate();
    public String conexionJPA();

}
