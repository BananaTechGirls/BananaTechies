document.getElementById('searchTask').onclick = function(evnt) {
    evnt.preventDefault();

    var valido = validaSearchTConAPI();
    if (!valido) return false;
    else document.getElementById('searchTForm').submit();
}

//la siguiente función deberia devolver falso en caso de que alguno de los campos falle.Debería detenerse el envío.
function validaSearchTConAPI() {
    var searchBoxT = document.getElementById('searchBoxT');
    var itsOk = true; //para indicar si se ha producido un error

    var bloques_error_ST = document.getElementsByClassName('mensajes_error_searchBoxT');
    for (var i = 0; i < bloques_error_ST.length; i++) {
        var mensajes_error_ST = bloques_error_ST[i].getElementsByTagName('div');
        for (var j = 0; j < mensajes_error_ST.length; j++) mensajes_error_ST[j].classList.remove('show');
    } //recorre el array de mensajes y los esconde mientras no falle el form.

    if (!searchBoxT.validity.valid) { //si searchBoxT NO es valido
        if (searchBoxT.validity.valueMissing) {
            document.getElementById('searchBoxT[valueMissing]').classList.add('show');
            searchBoxT.classList.add('errorInput');
        }
        itsOk = false;
    }
    return itsOk;
}