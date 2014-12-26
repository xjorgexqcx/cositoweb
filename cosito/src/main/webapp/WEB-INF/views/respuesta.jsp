<%@ include file="home.jsp"%>
<section>
	<br />
	<h2 Style="text-align:center;color:${color};">${rpta}</h2>
	<div Style="text-align: center;">
		<img src="${banner}" title="${nombre}" />
	</div>
	<div Style="text-align:center;">
		<a href="index"><button>Continuar</button></a>
	</div>

	<audio>
		<source src="audios/click.mp3"></source>
		<source src="audios/click.ogg"></source>
	</audio>
	<div id="sounddiv">
		<bgsound id="sound">
	</div>
</section>
</body>
</html>