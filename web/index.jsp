<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de películas</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/jquery320.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="js/utilidades.js"></script>
<script src="js/funciones.js"></script>
</head>
<body>
	<div class="container">
	<div class="page-header">
    <h1 class="text-center">Ejercicio 2 Ajax</h1>      
  	</div>    
	<div class="row">
	<div class="col-md-4 col-md-offset-4">
	<fieldset>
		<form action="/ejercicio2ajax/consultarpeliculas" method="post">
		<div class="form-group">
			<label for="tipoBusqueda">Seleccione primero el tipo de búsqueda:</label>
			<select class="form-control" id="tipoBusqueda" name="tipoBusqueda" onchange="cambiaInput()">
  				<option value="director" selected>Por director</option> 
  				<option value="actor">Por actor</option>
  				<option value="fecha">Por año de estreno</option>
			</select>
		</div>
		<div class="form-group">
			<label for="dato">Parámetro: </label><input class="form-control" id="dato" type="text" name="dato" onkeyup="sugerencia(event)"/><br/>
			<input class="btn btn-default center-block" type="button" onclick="buscarDato()" name="buscar" value="Buscar"/>
		</div>
		</form>
	</fieldset>
	</div>
	</div>
	<div class="row">
	<div id="resultado" class="col-md-8 col-md-offset-2"></div>
	</div>
	<!-- Modal -->
  	<div class="modal fade" id="myModal" role="dialog">
    	<div class="modal-dialog">
		<!-- Modal content-->
      	<div class="modal-content">
        	<div class="modal-header">
          		<button type="button" class="close" data-dismiss="modal">&times;</button>
          		<h4 class="modal-title">Cartelera</h4>
        	</div>
        	<div class="modal-body text-center">
          		 <img id="imagenCartel" src="" alt="cartel" height="auto" width="60%" max-width="600px"> 
        	</div>
        	<div class="modal-footer">
          		<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        	</div>
      	</div>
    	</div>
    </div>
	</div>
</body>
</html>