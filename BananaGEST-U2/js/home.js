var quitarBorde=document.getElementsByClassName('navbar-default');
for (var i = 0; i < quitarBorde.length; i++) {
	quitarBorde[i].classList.remove('navbar-default');
}

// guarda en una variable, o "le pone un nombre" a la peticion al servidor
var req = new XMLHttpRequest();

// busca todos los botones de delete del project por nombre de clase
var btn_del_Project=document.getElementsByClassName('btn_del_Project');

// esto va a ejecutarse solo si encuentra los elementos
if(btn_del_Project){

	// recorre el array y lista todos los elementos
	for (var i = 0; i < btn_del_Project.length; i++) {

		// a cada botón le asocia un evento click
		btn_del_Project[i].onclick=function(evnt){

			// define la variable con la frase que aparecerá en la alert
			var agreeMessage = confirm('Do you Want to delete this Project?');

			// Esto define que las acciones solo se ejecutarán si agreeMessage es true
			if(agreeMessage == true){

				// guarda el nombre de la referencia (que es el data_ID)
				var nombredereferencia=this.getAttribute('data_Id');

				// identifica cual es el projecto a borrar
				var projectABorrar=document.getElementById(nombredereferencia);

				// esto abre una petición en el servidor y le con GET va a adquirir info de la página "ok.html" (no prestar atencion a TRUE por ahora)
				req.open("GET", 'ok.html', true);

				// cuando la la respuesta del servidor se recibe...
				req.onreadystatechange = function(){

					// Si la operación se ha completado (4)
					if (req.readyState == 4){

						// esto es solo para saber cual ha sido la respuesta en la consola
						console.log('respuesta:',req.responseText);

						// Solo si en "ok.html" ha encontrado "ok" se ejecutará la acción
						if(req.responseText=='ok'){

							// borra el proyecto
							projectABorrar.parentNode.removeChild(projectABorrar);
						}

					}

				};

				// Enviar al servidor toda la parrafada de arriba
				req.send();
			}

			// Si no se cumple el if de arriba, no se hace la acción
			return false;
		}

	}
}

var btn_del_Task=document.getElementsByClassName('btn_del_Task')
if(btn_del_Task){
	for (var i = 0; i < btn_del_Task.length; i++) {
		btn_del_Task[i].onclick=function(evnt){

			var agreeMessage = confirm('Do you Want to delete this Task?');

			if(agreeMessage == true){
				var nombredereferencia=this.getAttribute('data_Id');
				var projectABorrar=document.getElementById(nombredereferencia);

				req.open("GET", 'ok.html', true);
				req.onreadystatechange = function(){
					if (req.readyState == 4){
						console.log('respuesta:',req.responseText);
						if(req.responseText=='ok'){
							projectABorrar.parentNode.removeChild(projectABorrar);
						}

					}

				};
				req.open("GET", "ok.html", true);
				req.send();
			}

			return false;
		}

	}

}

//////////////////////////////////////////////
//////////////////////////////////////////////

//Search
var searchPForm=document.getElementById('searchPForm');
if(searchPForm){
	searchPForm.onsubmit=function (evnt) {
		var valido = validaSearchPFormConAPI();
		if(!valido) return false;
	}
}
function validaSearchPFormConAPI() {
	var searchBoxP=document.getElementById('searchBoxP');	
	var itsOk=true;//para indicar si se ha producido un error
	if(searchBoxP){
		var bloques_error_SearchP=document.getElementsByClassName('mensajes_error_searchBoxP');
		for (var i = 0; i < bloques_error_SearchP.length; i++) {
			var mensajes_error = bloques_error_SearchP[i].getElementsByTagName('div');
			for (var j = 0; j < mensajes_error.length; j++) mensajes_error[j].classList.remove('show');
		}//recorre el array de mensajes y los esconde mientras no falle el form.
		if(!searchBoxP.validity.valid){//si searchBoxP NO es valido
				if(searchBoxP.validity.valueMissing){
					document.getElementById('searchBoxP[valueMissing]').classList.add('show');
					searchBoxP.classList.add('errorInput');
			}
			itsOk=false;
		}
	}
		// debugger;
		return itsOk;
}
