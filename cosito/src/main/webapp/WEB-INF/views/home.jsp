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
            <div Style="display: none;" id="logo" class="logo">
                <img onclick="redi('index');" alt="" src="images/logo4.png" />
            </div>
        </header>