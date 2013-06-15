<?php

function HeaderThing( $title, $head = '' ) {
	$r = "<head>
			<title>" . $title . "</title>
			<link type='text/css' rel='stylesheet' href='./css/css.css'>
			<link rel='shortcut icon' type='image/ico' href='./images/EyeSpyIcon.ico'>
			<script type='text/javascript' src='/js/faq.js'></script>
			<link rel='stylesheet' href='./js/jquery-ui.css' />
			<link rel='stylesheet' href='./js/jquery.ui.timepicker.css' />
			<script src='./js/jquery-1.9.1.js'></script>
			<script src='./js/jquery-ui.js'></script>
			<script src='./js/jquery.ui.timepicker.js'></script>"
			 . $head . 
		"</head>
		<body>
			<div class='main'><div class='header'>
				<div id='img'>
					<a href='./index.php'><img src='./images/EyeSpy.png' height='112px'></a>
				</div>
			</div>
			<div class='inner'>";
	echo $r;
}

function FooterThing() {
	$r = "</div>
			<div class='footer'>
				Coded by <a href='http://m4numbers.co.uk'>M477</a><br />
				<font size='0.5'>&copy Escapecraft 2013</font>
			</div>
		</div>
	</body>";
	echo $r;
}

?>
