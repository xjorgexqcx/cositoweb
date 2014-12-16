<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="es">
    <head>
    <title>
        Cosito | Home
    </title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/plantilla.css">
    <link href='http://fonts.googleapis.com/css?family=Luckiest+Guy' rel='stylesheet' type='text/css'>
    <script language="javascript" type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/tinyMCE.js"></script>
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
            <!--<div class="redesSociales">
                <a target="_blank" href="https://www.facebook.com/cositocomunity"><img alt="facebook"src="images/facebook.jpg"/></a>
                <img alt="twitter" src="images/twitter.jpg"/>
                <img alt="youtube"  src="images/youtube.jpg"/>
            </div>
            <div class="menu"><img onclick="redi('index.html');" alt="home" src="images/1016.png"><img alt="registro" src="images/register.png"></div> -->
            <div id="logo" class="logo">
                <img onclick="redi('index.html');" alt="" src="images/logo.png" />
            </div>
        </header>
        <section>
            <div onmouseover="moverScroll('1');" onmouseleave="stopScroll();" id="scrolling" class="scrollingUp"></div>
            <div onmouseover="moverScroll('0');" onmouseleave="stopScroll();" id="scrolling" class="scrolling"></div>
        </textarea>
        <audio>
            <source src="audios/click.mp3"></source>
            <source src="audios/click.ogg"></source>
        </audio>
        <div id="sounddiv"><bgsound id="sound"></div>
        </section>
    </body>
</html>