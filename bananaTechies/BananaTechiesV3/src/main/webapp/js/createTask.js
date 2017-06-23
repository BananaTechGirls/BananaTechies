document.getElementById('btn_create_task').onclick=function (evnt) {
	var valido = validaCreateTConAPI();
	if(!valido) return false;
	else document.getElementById('btn_create_task').submit();
};

function validaCreateTConAPI() {
	var itsOk=true;
	var taskTitle=document.getElementById('taskTitle');
	var startDate=document.getElementById('startDate');
	var endDate=document.getElementById('endDate');
	var inChangeControl= document.getElementById('inChangeControl');
	var proyectFormid= document.getElementById('proyectFormid');
	var statusId= document.getElementById('statusId');
	var taskDescriptionId= document.getElementById('taskDescriptionId'); 


	var bloques_error=document.getElementsByClassName('mensajes_error_createT');
	for (var i = 0; i < bloques_error.length; i++) {
		var mensajes_error= bloques_error[i].getElementsByTagName('div');
		for (var j = 0; j < mensajes_error.length; j++) {
			mensajes_error[j].classList.remove('show');
		}
	}//recorre el array de mensajes y los esconde mientras no falle el form.
	
	if(!taskTitle.validity.valid){//si taskTitle NO es valido
		if(taskTitle.validity.typeMismatch){
			document.getElementById('taskTitle[typeMismatch]').classList.add('show');//muestro el mensaje de error
			taskTitle.classList.add('errorInput');
			}
		if(taskTitle.validity.valueMissing){
			document.getElementById('taskTitle[valueMissing]').classList.add('show');
			taskTitle.classList.add('errorInput');
			}
		if(taskTitle.validity.patternMismatch){
			document.getElementById('taskTitle[patternMismatch]').classList.add('show');
			taskTitle.classList.add('errorInput');
			}
		itsOk=false;
	}

	if(!startDate.validity.valid){//si la fecha NO es valida
			if(startDate.validity.typeMismatch){
				document.getElementById('startDate[typeMismatch]').classList.add('show');//muestro el mensaje de error
				startDate.classList.add('errorInput');
				}
			if(startDate.validity.valueMissing){
				document.getElementById('startDate[valueMissing]').classList.add('show');
				startDate.classList.add('errorInput');
				}
			if(startDate.validity.patternMismatch){
				document.getElementById('startDate[patternMismatch]').classList.add('show');
				startDate.classList.add('errorInput');
				}
			itsOk=false;
	}
	if(!endDate.validity.valid){//si la fecha NO es valida
			if(endDate.validity.typeMismatch){
				document.getElementById('endDate[typeMismatch]').classList.add('show');//muestro el mensaje de error
				endDate.classList.add('errorInput');
				}
			itsOk=false;
	}
	if(!inChangeControl.validity.valid){//si la asignacion esta vacia
			if(inChangeControl.validity.valueMissing){
				document.getElementById('inChangeControl[valueMissing]').classList.add('show');
				inChangeControl.classList.add('errorInput');
				}
			itsOk=false;
	}		
	if(!proyectFormid.validity.valid){//si el proyecto esta vacio
			if(proyectFormid.validity.valueMissing){
				document.getElementById('proyectFormid[valueMissing]').classList.add('show');
				proyectFormid.classList.add('errorInput');
				}
			itsOk=false;
    }
	if(!statusId.validity.valid){//si el estado esta vacio
			if(statusId.validity.valueMissing){
				document.getElementById('statusId[valueMissing]').classList.add('show');
				statusId.classList.add('errorInput');
				}
			itsOk=false;
	}		
	if(!taskDescriptionId.validity.valid){//si la descripcion esta vacia
			if(taskDescriptionId.validity.typeMismatch){
				document.getElementById('taskDescriptionId[typeMismatch]').classList.add('show');//muestro el mensaje de error
				taskDescriptionId.classList.add('errorInput');
				}
			if(taskDescriptionId.validity.valueMissing){
				document.getElementById('taskDescriptionId[valueMissing]').classList.add('show');
				taskDescriptionId.classList.add('errorInput');
				}
			if(taskDescriptionId.validity.patternMismatch){
				document.getElementById('taskDescriptionId[patternMismatch]').classList.add('show');
				taskDescriptionId.classList.add('errorInput');
				}
			itsOk=false;
		}
	return itsOk;
}




