<html lang="es">
<head>
<meta charset="utf-8">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="js/toastr.js"></script>
<link href="css/toastr.css" rel="stylesheet" />
<script type="text/javascript">
	function mensaje() {
		toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"progressBar" : true,
			"positionClass" : "toast-top-center",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}
		toastr.error("Hola muchachas");
	}
</script>
</head>
<body>
	<button id="showtoast" onclick="mensaje();">Mostrar mensaje</button>
	<fieldset>
		<legend>Banner</legend>
		<form action="uploadFile" enctype="multipart/form-data" method="post">

			<label>Upload Photo</label> <input name="userPhoto" type="file">
			</br> </br> <input type="submit" name="SUBMIT">

		</form>

	</fieldset>
</body>
</html>