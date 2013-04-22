<HTML>
	<head>
		<link rel="stylesheet" href="./js/jquery-ui.css" />
		<link rel="stylesheet" href="./js/jquery.ui.timepicker.css" />
		<script src="./js/jquery-1.9.1.js"></script>
		<script src="./js/jquery-ui.js"></script>
		<script src="./js/jquery.ui.timepicker.js"></script>

		<script>
			$(function() {
			$( "#datepicker" ).datepicker();
			});
			
			$(function() {
			$( "#timepicker" ).timepicker();
			});
		</script>
	</head>
	<body>
	
		<p>Date: <input type="text" id="datepicker" /></p>
		<p>Time: <input type="text" id="timepicker" /></p>
		<?php echo strtotime("2013-04-18 21:04:06") . "<br />";
		      echo time(); ?>
	
	</body>
</HTML>