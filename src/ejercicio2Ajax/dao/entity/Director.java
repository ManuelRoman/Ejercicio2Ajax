package ejercicio2Ajax.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the director database table.
 * 
 */
@Entity
@Table(name="director")
@NamedQuery(name="Director.findAll", query="SELECT d FROM Director d")
public class Director implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Pelicula
	@OneToMany(mappedBy="director", fetch=FetchType.EAGER)
	private List<Pelicula> peliculas;

	public Director() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pelicula> getPeliculas() {
		return this.peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public Pelicula addPelicula(Pelicula pelicula) {
		getPeliculas().add(pelicula);
		pelicula.setDirector(this);

		return pelicula;
	}

	public Pelicula removePelicula(Pelicula pelicula) {
		getPeliculas().remove(pelicula);
		pelicula.setDirector(null);

		return pelicula;
	}

}