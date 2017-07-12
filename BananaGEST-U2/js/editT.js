
////////////////////////////////
//////////////////////////////
////////////////////////////////

var okBtnList = document.getElementsByClassName('glyphicon glyphicon-ok');
console.log(okBtnList);
 if(okBtnList){
    for (var i = 0; i < okBtnList.length; i++) {
        okBtnList[i].onclick=function(evnt){
            evnt.preventDefault();
            var id=this.getAttribute('data_Id');
            var elemAEditar=document.getElementById(id);
            var elemEditado=document.getElementsByClassName('elemEditado');
            for (var j = 0; j < elemEditado.length; j++) {
                elemAEditar.innerHTML = elemEditado[j].value;
                }
            return elemEditado[j];

        }
    }
}


