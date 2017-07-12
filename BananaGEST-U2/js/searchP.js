document.getElementById('searchProject').onclick = function(evnt) {
    evnt.preventDefault();

    var valido = validaSearchPConAPI();
    if (!valido) return false;
    else document.getElementById('searchPForm').submit();
}

//la siguiente función deberia devolver falso en caso de que alguno de los campos falle.Debería detenerse el envío.
function validaSearchPConAPI() {
    var searchBoxP = document.getElementById('searchBoxP');
    var itsOk = true; //para indicar si se ha producido un error

    var bloques_error = document.getElementsByClassName('mensajes_error_searchBoxP');
    for (var i = 0; i < bloques_error.length; i++) {
        var mensajes_error = bloques_error[i].getElementsByTagName('div');
        for (var j = 0; j < mensajes_error.length; j++) mensajes_error[j].classList.remove('show');
    } //recorre el array de mensajes y los esconde mientras no falle el form.

    if (!searchBoxP.validity.valid) { //si searchBoxP NO es valido
        if (searchBoxP.validity.valueMissing) {
            document.getElementById('searchBoxP[valueMissing]').classList.add('show');
            searchBoxP.classList.add('errorInput');
        }
        itsOk = false;
    }
    return itsOk;
}