document.getElementById('fPassForm').onsubmit=function (evnt) {
	var valido = validafPassConAPI();
	if(!valido) return false;
}
var itsOk=true;//para indicar si se ha producido un error
//la siguiente función deberia devolver falso en caso de que alguno de los campos falle.Debería detenerse el envío.
function validafPassConAPI() {
	var emailFPass=document.getElementById('emailFPass');

	var bloques_error=document.getElementsByClassName('mensajes_error_fPass');
	for (var i = 0; i < bloques_error.length; i++) {
		var mensajes_error = bloques_error[i].getElementsByTagName('div');
		for (var j = 0; j < mensajes_error.length; j++) mensajes_error[j].classList.remove('show');
	}//recorre el array de mensajes y los esconde mientras no falle el form.
	
	if(!emailFPass.validity.valid){//si email NO es valido
		if(emailFPass.validity.typeMismatch){
			document.getElementById('emailFPass[typeMismatch]').classList.add('show');//muestro el mensaje de error
			emailFPass.classList.add('errorInput');
		}
		if(emailFPass.validity.valueMissing){
			document.getElementById('emailFPass[valueMissing]').classList.add('show');
			emailFPass.classList.add('errorInput');
		}
		itsOk=false;
	}
	debugger;
	return itsOk;
}
