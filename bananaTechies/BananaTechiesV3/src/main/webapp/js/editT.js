
////////////////////////////////
//////////////////////////////
////////////////////////////////

document.getElementById('btnSaveEditT').onclick=function (evnt) {
    var valido = validaEditTConAPI();
    if(!valido) return false;
    else document.getElementById('btnSaveEditT').submit();
};

function validaEditTConAPI() {
    var itsOk=true;
    var editTaskTitle=document.getElementById('editTaskTitle');
    var startDateET=document.getElementById('startDateET');
    var endDateET=document.getElementById('endDateET');
    var editInChargeT= document.getElementById('editInChargeT');
    var editPT= document.getElementById('editPT');
    var editStatusT= document.getElementById('editStatusT');
    var editTDescription= document.getElementById('editTDescription'); 


    var bloques_error=document.getElementsByClassName('mensajes_error_EditT');
    for (var i = 0; i < bloques_error.length; i++) {
        var mensajes_error= bloques_error[i].getElementsByTagName('div');
        for (var j = 0; j < mensajes_error.length; j++) {
            mensajes_error[j].classList.remove('show');
        }
    }//recorre el array de mensajes y los esconde mientras no falle el form.
    
    if(!editTaskTitle.validity.valid){//si editTaskTitle NO es valido
        if(editTaskTitle.validity.typeMismatch){
            document.getElementById('editTaskTitle[typeMismatch]').classList.add('show');//muestro el mensaje de error
            editTaskTitle.classList.add('errorInput');
            }
        if(editTaskTitle.validity.valueMissing){
            document.getElementById('editTaskTitle[valueMissing]').classList.add('show');
            editTaskTitle.classList.add('errorInput');
            }
        if(editTaskTitle.validity.patternMismatch){
            document.getElementById('editTaskTitle[patternMismatch]').classList.add('show');
            editTaskTitle.classList.add('errorInput');
            }
        itsOk=false;
    }

    if(!startDateET.validity.valid){//si la fecha NO es valida
            if(startDateET.validity.typeMismatch){
                document.getElementById('startDateET[typeMismatch]').classList.add('show');//muestro el mensaje de error
                startDateET.classList.add('errorInput');
                }
            if(startDateET.validity.valueMissing){
                document.getElementById('startDateET[valueMissing]').classList.add('show');
                startDateET.classList.add('errorInput');
                }
            if(startDateET.validity.patternMismatch){
                document.getElementById('startDateET[patternMismatch]').classList.add('show');
                startDateET.classList.add('errorInput');
                }
            itsOk=false;
    }
    if(!endDateET.validity.valid){//si la fecha NO es valida
            if(endDateET.validity.typeMismatch){
                document.getElementById('endDateET[typeMismatch]').classList.add('show');//muestro el mensaje de error
                endDateET.classList.add('errorInput');
                }
            itsOk=false;
    }
    if(!editInChargeT.validity.valid){//si la asignacion esta vacia
            if(editInChargeT.validity.valueMissing){
                document.getElementById('editInChargeT[valueMissing]').classList.add('show');
                editInChargeT.classList.add('errorInput');
                }
            itsOk=false;
    }       
    if(!editPT.validity.valid){//si el proyecto esta vacio
            if(editPT.validity.valueMissing){
                document.getElementById('editPT[valueMissing]').classList.add('show');
                editPT.classList.add('errorInput');
                }
            itsOk=false;
    }
    if(!editStatusT.validity.valid){//si el estado esta vacio
            if(editStatusT.validity.valueMissing){
                document.getElementById('editStatusT[valueMissing]').classList.add('show');
                editStatusT.classList.add('errorInput');
                }
            itsOk=false;
    }       
    if(!editTDescription.validity.valid){//si la descripcion esta vacia
            if(editTDescription.validity.typeMismatch){
                document.getElementById('editTDescription[typeMismatch]').classList.add('show');//muestro el mensaje de error
                editTDescription.classList.add('errorInput');
                }
            if(editTDescription.validity.valueMissing){
                document.getElementById('editTDescription[valueMissing]').classList.add('show');
                editTDescription.classList.add('errorInput');
                }
            if(editTDescription.validity.patternMismatch){
                document.getElementById('editTDescription[patternMismatch]').classList.add('show');
                editTDescription.classList.add('errorInput');
                }
            itsOk=false;
        }
    return itsOk;
}






