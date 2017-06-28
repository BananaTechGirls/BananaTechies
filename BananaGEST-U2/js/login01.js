document.getElementById('loginForm').onsubmit=function (evnt) {
	var valido = validaLoginConAPI();
	if(!valido) return false;
}

//la siguiente función deberia devolver falso en caso de que alguno de los campos falle.Debería detenerse el envío.
function validaLoginConAPI() {
	var email=document.getElementById('email');
	var pass=document.getElementById('pass');

	var itsOk=true;//para indicar si se ha producido un error
	
	var bloques_error=document.getElementsByClassName('mensajes_error');
	for (var i = 0; i < bloques_error.length; i++) {
		var mensajes_error = bloques_error[i].getElementsByTagName('div');
		for (var j = 0; j < mensajes_error.length; j++) mensajes_error[j].classList.remove('show');
	}//recorre el array de mensajes y los esconde mientras no falle el form.
	
	if(!email.validity.valid){//si email NO es valido
		if(email.validity.typeMismatch){
			document.getElementById('email[typeMismatch]').classList.add('show');//muestro el mensaje de error
			email.classList.add('errorInput');
			}
		if(email.validity.valueMissing){
			document.getElementById('email[valueMissing]').classList.add('show');
			email.classList.add('errorInput');
			}
		itsOk=false;
	}
	if(!pass.validity.valid){
		if(pass.validity.valueMissing){
			document.getElementById('pass[valueMissing]').classList.add('show');
			pass.classList.add('errorInput');
		}
		if(pass.validity.tooShort){
			document.getElementById('pass[tooShort]').classList.add('show');
			pass.classList.add('errorInput');
		}
		itsOk=false;
	}
	// debugger;
	return itsOk;
}


