package ejercicio2Ajax.dao;

import javax.persistence.*;

public interface BeanDaoConexion {
	
	public void getConexion() throws Exception,EntityExistsException, IllegalStateException;
	
	public void close() throws Exception,EntityExistsException, IllegalStateException;

}
