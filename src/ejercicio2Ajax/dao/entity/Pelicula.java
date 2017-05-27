package ejercicio2Ajax.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pelicula database table.
 * 
 */
@Entity
@Table(name="pelicula")
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String cartel;

	private String fecha;

	private String titulo;

	//bi-directional many-to-many association to Actor
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="actuacion"
		, joinColumns={
			@JoinColumn(name="peliculaId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="actorId")
			}
		)
	private List<Actor> actors;

	//bi-directional many-to-one association to Director
	@ManyToOne
	@JoinColumn(name="directorId")
	private Director director;

	public Pelicula() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCartel() {
		return this.cartel;
	}

	public void setCartel(String cartel) {
		this.cartel = cartel;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Director getDirector() {
		return this.director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

}