package ejercicio2Ajax.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ejercicio2Ajax.dao.entity.*;

public class BeanDaoConsultasImpl extends BeanDaoConexionImpl implements BeanDaoConsultas{
	
	
	public BeanDaoConsultasImpl(String unidadPersistencia){
		super(unidadPersistencia);
	}

	@Override
	public String obtenerNombresDirectores(String dato)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		String nombreDirector = null;
		Query query = null;
		List<Director> listaDirectores = new ArrayList();
		Director director = null;
		try {
			String jpql = "SELECT d FROM Director d WHERE d.nombre like ?1 order by d.nombre";
			query = em.createQuery(jpql);
			query.setParameter(1, dato+"%");
			listaDirectores = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		if(!listaDirectores.isEmpty()){
			director = listaDirectores.get(0);
			nombreDirector = director.getNombre();
		}
		return nombreDirector;
	}

	@Override
	public String obtenerNombresActores(String dato) throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		String nombreActor = null;
		Query query = null;
		List<Actor> listaActores = new ArrayList();
		Actor actor = null;
		try {
			String jpql = "SELECT a FROM Actor a WHERE a.nombre like ?1 order by a.nombre";
			query = em.createQuery(jpql);
			query.setParameter(1, dato+"%");
			listaActores = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		if(!listaActores.isEmpty()){
			actor = listaActores.get(0);
			nombreActor = actor.getNombre();
		}
		return nombreActor;
	}

	@Override
	public List<Pelicula> obtenerPeliculasDirector(String dato)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Pelicula> listaPeliculas = new ArrayList();
		Director director = new Director();
		Query query = null;
		try {
			String jpql = "SELECT d FROM Director d WHERE d.nombre LIKE ?1";
			query = em.createQuery(jpql);
			query.setParameter(1, dato);
			director = (Director) query.getSingleResult();
			System.out.println("Dentro del beandao: "+director.getId()+ ", " +director.getNombre());
			listaPeliculas = director.getPeliculas();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaPeliculas;
	}

	@Override
	public List<Pelicula> obtenerPeliculasActor(String dato)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Pelicula> listaPeliculas = new ArrayList();
		Actor actor = new Actor();
		Query query = null;
		try {
			String jpql = "SELECT a FROM Actor a WHERE a.nombre LIKE ?1";
			query = em.createQuery(jpql);
			query.setParameter(1, dato);
			actor = (Actor) query.getSingleResult();
			System.out.println("Dentro del beandao: "+actor.getId()+ ", " +actor.getNombre());
			listaPeliculas = actor.getPeliculas();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaPeliculas;
	}

	@Override
	public List<Pelicula> obtenerPeliculasFecha(String dato)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Pelicula> listaPeliculas = new ArrayList();
		Query query = null;
		try {
			String jpql = "SELECT p FROM Pelicula p WHERE p.fecha LIKE ?1";
			query = em.createQuery(jpql);
			query.setParameter(1, dato);
			listaPeliculas = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaPeliculas;
	}

}
