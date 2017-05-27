package ejercicio2Ajax.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import ejercicio2Ajax.dao.entity.*;

public interface BeanDaoConsultas {
	
	public String obtenerNombresDirectores(String dato) throws Exception, EntityExistsException, IllegalArgumentException;
	
	public String obtenerNombresActores(String dato) throws Exception, EntityExistsException, IllegalArgumentException;
	
	public List<Pelicula> obtenerPeliculasDirector(String dato) throws Exception, EntityExistsException, IllegalArgumentException;
	
	public List<Pelicula> obtenerPeliculasActor(String dato) throws Exception, EntityExistsException, IllegalArgumentException;
	
	public List<Pelicula> obtenerPeliculasFecha(String dato) throws Exception, EntityExistsException, IllegalArgumentException;

}
