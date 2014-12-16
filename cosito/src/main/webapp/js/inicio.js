var myVar;
var inicio=0;

function redi(uri)
{
    window.location.href=uri;
}
function moverScroll(direccion)
{
    var posicion=document.getElementById("posicionScroll");
    inicio=posicion.scrollTop;
    if(direccion == "0"){
        myVar=setInterval(baja, 40);
    }
    if(direccion == "1"){
        myVar=setInterval(sube, 40);
    }
}
function baja(){
    inicio += 0.1;
    window.scrollBy(0,inicio);
}
function sube(){
    inicio -= 0.1;
    window.scrollBy(0,inicio);
}
function stopScroll()
{
    clearInterval(myVar);
}