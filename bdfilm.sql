DROP DATABASE IF EXISTS filmromangarcia;
CREATE DATABASE filmromangarcia;
USE filmromangarcia;

CREATE TABLE director (
  id int(10),
  nombre varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE actor (
  id int(10),
  nombre varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE pelicula (
  id int(10),
  titulo varchar(80) NOT NULL,
  directorId int(10),
  fecha char(4) NOT NULL,
  cartel varchar(60) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (directorId) REFERENCES director(id)
);

CREATE TABLE actuacion (
  actorId int(10),
  peliculaId int(10),
  PRIMARY KEY (actorId, peliculaId),
  FOREIGN KEY (actorId) REFERENCES actor(id),
  FOREIGN KEY (peliculaId) REFERENCES pelicula(id)
);

INSERT INTO director VALUES 
(0,'Ridley Scott'),
(1,'James Cameron'),
(2,'David Fincher'),
(3,'Jean-Pierre Jeunet');

INSERT INTO actor VALUES 
(0,'Sigourney Weaver'),
(1,'John Hurt'),
(2,'Paul Reiser'),
(3,'Michael Biehn'),
(4,'Charles S. Dutton'),
(5,'Charles Dance'),
(6,'Winona Ryder'),
(7,'Ron Perlman'),
(8,'Noomi Rapace'),
(9,'Michael Fassbender'),
(10,'Charlize Theron'),
(11,'Katherine Waterston'),
(12,'Demián Bichir');

INSERT INTO pelicula VALUES 
(0,'Alien, el octavo pasajero',0,'1979','alien1.jpg'),
(1,'Aliens: El regreso',1,'1986','alien2.jpg'),
(2,'Alien 3',2,'1992','alien3.jpg'),
(3,'Alien: Resurrección',3,'1997','alien_resurrection.jpg'),
(4,'Prometheus',0,'2012','prometheus.jpg'),
(5,'Alien: Covenant',0,'2017','alien_covenant.jpg');

INSERT INTO actuacion VALUES
(0,0),
(0,1),
(0,2),
(0,3),
(1,0),
(2,1),
(3,1),
(4,2),
(5,2),
(6,3),
(7,3),
(8,4),
(9,4),
(9,5),
(10,4),
(11,5),
(12,5);
