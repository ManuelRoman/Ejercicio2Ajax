$(document).ready(function (e) {
  $('#myModal').on('show.bs.modal', function(e) {    
    var img = $(e.relatedTarget).data().id;
	$(e.currentTarget).find('#imagenCartel').attr("src", img);
  });
});

function cambiaInput(){
	var tipo=document.getElementById("tipoBusqueda").value;
	if(tipo=="fecha"){
		document.getElementById("dato").type="number";
		document.getElementById("dato").min="1900";
		document.getElementById("dato").max="2017";
		document.getElementById("dato").step="1";
	} else{
		document.getElementById("dato").type="text";
	}
}
var obj;
function sugerencia(evento){
       if((evento.keyCode>=48 && evento.keyCode<=57) || (evento.keyCode>=65 && evento.keyCode<=90) || (evento.keyCode >= 96 && evento.keyCode <= 105)){
           obj=new ObjetoAJAX();
           var objForm=document.forms[0]; //el dom tiene un array con los formularios que contiene, tomamos el form completo
           obj.enviar("sugerirdato",objForm.method,"procesaSugerencia",objForm);        
       }
}
function procesaSugerencia(){
   	if(obj.estado()==200){ //200->OK
       	var resp=obj.respuestaTexto();
        var caja=document.getElementById("dato");
       	//si se ha recibido una palabra de respuesta se introduce en el control y se seleccionan los caracteres aún no tecleados
       	if(resp!=""){                    
           	var inicioSel=caja.value.length;
           	caja.value=resp;
           	caja.selectionStart=inicioSel;
           	caja.selectionEnd=caja.value.length;
       	}
	}
    else{
       	alert(obj.textoEstado());
    }
}       
function buscarDato() {
	objAJAX = new ObjetoAJAX();
	var objForm = document.forms[0];
	objAJAX.enviar(objForm.action,objForm.method,"procesaBuscarDato",objForm);
}
function procesaBuscarDato() {
	var listaPeliculas = eval ("("+objAJAX.respuestaTexto()+")");
	var datos ="";
	if(listaPeliculas.length>0){
		var tipo = listaPeliculas[0].tipo;
		switch (tipo) {
			case "director":
				datos+="<table class='table table-hover'><thead><tr><th>Título</th><th>Año de estreno</th><th>Actores</th><th>Cartel</th></tr></thead><tbody>";
   				for(var elm = 1;elm < listaPeliculas.length;elm++){
   				datos+="<tr><td>"+listaPeliculas[elm].titulo+"</td><td>"+listaPeliculas[elm].fecha+"</td><td>"
   				+listaPeliculas[elm].actores+"</td><td><button type='button' class='btn btn-default' data-toggle='modal' data-target='#myModal' data-id='carteles/"
   				+listaPeliculas[elm].cartel+"'>Ver</button></td></tr>";
       			}
       			datos+="</tbody></table>";
   			break;
			default:
				datos+="<table class='table table-hover'><thead><tr><th>Título</th><th>Director</th><th>Año de estreno</th><th>Actores</th><th>Cartel</th></tr></thead><tbody>";
   				for(var elm = 1;elm < listaPeliculas.length;elm++){
       				datos+="<tr><td>"+listaPeliculas[elm].titulo+"</td><td>"+listaPeliculas[elm].director+"</td><td>"
       				+listaPeliculas[elm].fecha+"</td><td>"+listaPeliculas[elm].actores+
       				"</td><td><button type='button' class='btn btn-default' data-toggle='modal' data-target='#myModal' data-id='carteles/"
       				+listaPeliculas[elm].cartel+"'>Ver</button></td></tr>";
       			}
       			datos+="</tbody></table>";	
		}
	} else {
       	datos+="Sin resultados";
    }
    document.getElementById("resultado").innerHTML=datos;
}