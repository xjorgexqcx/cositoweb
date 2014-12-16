<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!Doctype HTML>
<html lang="es">
    <head>
    <title>
        Cosito
    </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/plantilla.css">
    <link href='http://fonts.googleapis.com/css?family=Luckiest+Guy' rel='stylesheet' type='text/css'>
    <link rel="icon" type="image/png" href="images/atom.png" />
    <script src="js/inicio.js"></script>
    <script>
    function playSound() {
        var audio = document.getElementsByTagName("audio")[0];
        audio.play();
    }
    </script>
    </head>
    <body id="posicionScroll" onclick="playSound();">
        <header>
            <div class="redesSociales">
                <a target="_blank" href="https://www.facebook.com/cositocomunity"><img alt="facebook"src="images/facebook.jpg"/></a>
                <a target="_blank" href="https://twitter.com/CositoComunity"><img alt="twitter" src="images/twitter.jpg"/></a>
                <a target="_blank" href="https://www.youtube.com/channel/UCSpHY1LxFbkd2fWwdVYFn9w"><img alt="youtube"  src="images/youtube.jpg"/></a>
            </div>
            <div id="logo" class="logo">
                <img onclick="redi('index');" alt="" src="images/logo4.png" />
            </div>
        </header>
        <section>
            <div onmouseover="moverScroll('1');" onmouseleave="stopScroll();" id="scrolling" class="scrollingUp"></div>
            <div onmouseover="moverScroll('0');" onmouseleave="stopScroll();" id="scrolling" class="scrolling"></div>
            <div class="agregarSeccion">
                <img alt="Agregar sección" src="images/add.png" />
            </div>
            <div class="eliminarSeccion">
                <img alt="eliminar Seccion" src="images/delete.png" />
            </div>
            <div Style="background-image: url('images/secciones/CositoVip/Banner.jpg') !important;" id="item" onclick="redi('publicaciones.html');" class="item">
            </div>
            <div Style="background-image: url('images/secciones/RememberFallido/Banner.jpg') !important" id="item" onclick="redi('publicaciones.html');" class="item">
            </div>
            <div Style="background-image: url('images/secciones/networking.png')" id="item" onclick="redi('publicaciones.html');" class="item">
            </div>
            <div Style="background-image: url('images/secciones/networking.png')" id="item" onclick="redi('publicaciones.html');" class="item">
            </div>
            <div Style="background-image: url('images/secciones/networking.png')" id="item" onclick="redi('publicaciones.html');" class="item">
            </div>
        <audio>
            <source src="audios/click.mp3"></source>
            <source src="audios/click.ogg"></source>
        </audio>
        <div id="sounddiv"><bgsound id="sound"></div>
        </section>
    <footer>
        <!--
        <div class="menu">
            <img onclick="redi('index.html');" alt="home" src="images/1016.png">
            <img alt="registro" src="images/register.png">
        </div>
        -->
    </footer>
    </body>
</html>