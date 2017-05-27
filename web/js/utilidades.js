
function ObjetoAJAX(){
	// instanciar en primer lugar el objeto XMLHttpRequest, que es el objeto clave que permite realizar comunicaciones 
	// con el servidor en segundo plano, sin necesidad de recargar las páginas.
	var xmlHttpRequest;
    if(window.ActiveXObject){
         xmlHttpRequest=new ActiveXObject("Microsoft.XMLHttp");      
    }
    else if((window.XMLHttpRequest) || (typeof XMLHttpRequest)!=undefined){
         xmlHttpRequest=new XMLHttpRequest();
    }
    else{
         xmlHttpRequest=null;
         alert("Su navegador no tiene soporte para AJAX");
    }
    this.enviar=m_enviar;
    this.respuestaTexto=m_texto;
    this.respuestaXML=m_XML;
    this.obtenerEncabezados=m_encabezados;
    this.estado=m_estado;
    this.textoEstado=m_textoEstado;

    function m_enviar(url, method, funcionRetorno, objForm){
        var dataSend=null;
        if(method.toLowerCase()=="post"&&objForm!=null){
            dataSend=obtenerDatos(objForm);
        }
        else if(method.toLowerCase()=="get"&&objForm!=null){
            dataSend=obtenerDatos(objForm); //poner el dataSend a nulo
            if(url.indexOf("?")==-1){
                url+="?"+dataSend;
            }
            else{
                url+="&"+dataSend;
            }
        }
        //Petición en modo asíncrono
        xmlHttpRequest.open(method,url,true);
        xmlHttpRequest.onreadystatechange=function(){
            if(xmlHttpRequest.readyState==4){
                eval(funcionRetorno+"("+")");
            }
        };
        if(method.toLowerCase()=="post"&&objForm!=null){
        	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        xmlHttpRequest.send(dataSend);
    }
    function m_texto(){
    	// El contenido de la respuesta del servidor en forma de cadena de texto
        return xmlHttpRequest.responseText;
    }
    function m_XML(){
    	// El contenido de la respuesta del servidor en formato XML. El objeto devuelto se puede procesar como un objeto DOM
        return xmlHttpRequest.responseXML;
    }
    function m_encabezados(){
    	// Devuelve una cadena de texto con todas las cabeceras de la respuesta del servidor
    	return xmlHttpRequest.getAllResponseHeaders();
    }
    function m_estado(){
    	// El código de estado HTTP devuelto por el servidor (200 para una respuesta correcta, 404 para "No encontrado", 500 para un error de servidor, etc.)
        return xmlHttpRequest.status;
    }
    function m_textoEstado(){
    	// El código de estado HTTP devuelto por el servidor en forma de cadena de texto: "OK", "Not Found", "Internal Server Error", etc.
        return xmlHttpRequest.statusText;
    }
    function obtenerDatos(objForm){ //Recorremos los elementos de formulario y obtenemos los nombres   	
        var controles=objForm.elements; // de los elementos y sus valores
        var datos=new Array();
        var cad="";
        for(var i=0;i<controles.length;i++){
             cad=encodeURIComponent(controles[i].name)+"="; //con encodeURIComponent por si hay espacios en blancos
             cad+=encodeURIComponent(controles[i].value);
             datos.push(cad);
        }
        cad=datos.join("&"); //Obtenemos un string con todos lo elementos separados por &
        return cad;
     }
}