document.getElementById('btn_create_project').onclick=function (evnt) {
	var valido = validaCreatePConAPI();
	if(!valido) return false;
	else document.getElementById('btn_create_project').submit();
};

function validaCreatePConAPI() {
	var itsOk=true;
	var projectTitleCP=document.getElementById('projectTitleCP');
	var startDateCP=document.getElementById('startDateCP');
	var personaAsignadaCP= document.getElementById('personaAsignadaCP');
	var statusIdCP= document.getElementById('statusIdCP');
	var projectDescriptionIdCP= document.getElementById('projectDescriptionIdCP');
	var projectNotesCP= document.getElementById('projectNotesCP');

	var bloques_errorCP=document.getElementsByClassName('mensajes_error_createP');
	for (var i = 0; i < bloques_errorCP.length; i++) {
		var mensajes_errorCP= bloques_errorCP[i].getElementsByTagName('div');
		for (var j = 0; j < mensajes_errorCP.length; j++) {
			mensajes_errorCP[j].classList.remove('show');
		}
	}
	if(!projectTitleCP.validity.valid){
		if(projectTitleCP.validity.typeMismatch){
			document.getElementById('projectTitleCP[typeMismatch]').classList.add('show');
			projectTitleCP.classList.add('errorInput');
		}
		if(projectTitleCP.validity.valueMissing){
			document.getElementById('projectTitleCP[valueMissing]').classList.add('show');
			projectTitleCP.classList.add('errorInput');
		}
		if(projectTitleCP.validity.patternMismatch){
			document.getElementById('projectTitleCP[patternMismatch]').classList.add('show');
			projectTitleCP.classList.add('errorInput');
		}
		itsOk=false;
	}
	if(!startDateCP.validity.valid){
		if(startDateCP.validity.typeMismatch){
			document.getElementById('startDateCP[typeMismatch]').classList.add('show');
			startDateCP.classList.add('errorInput');
		}
		if(startDateCP.validity.valueMissing){
			document.getElementById('startDateCP[valueMissing]').classList.add('show');
			startDateCP.classList.add('errorInput');
		}
		itsOk=false;
	}
	if(!personaAsignadaCP.validity.valid){
		if(personaAsignadaCP.validity.valueMissing){
			document.getElementById('personaAsignadaCP[valueMissing]').classList.add('show');
			personaAsignadaCP.classList.add('errorInput');
		}
		itsOk=false;
	}
	if(!statusIdCP.validity.valid){
		if(statusIdCP.validity.typeMismatch){
			document.getElementById('statusIdCP[typeMismatch]').classList.add('show');
			statusIdCP.classList.add('errorInput');
		}
		if(statusIdCP.validity.valueMissing){
			document.getElementById('statusIdCP[valueMissing]').classList.add('show');
			statusIdCP.classList.add('errorInput');
		}
		itsOk=false;
	}
	if(!projectDescriptionIdCP.validity.valid){
		if(projectDescriptionIdCP.validity.typeMismatch){
			document.getElementById('projectDescriptionIdCP[typeMismatch]').classList.add('show');
			projectDescriptionIdCP.classList.add('errorInput');
		}
		if(projectDescriptionIdCP.validity.valueMissing){
			document.getElementById('projectDescriptionIdCP[valueMissing]').classList.add('show');
			projectDescriptionIdCP.classList.add('errorInput');
		}
		if(projectDescriptionIdCP.validity.patternMismatch){
			document.getElementById('projectDescriptionIdCP[patternMismatch]').classList.add('show');
			projectDescriptionIdCP.classList.add('errorInput');
		}
		itsOk=false;
	}

	return itsOk;
}


