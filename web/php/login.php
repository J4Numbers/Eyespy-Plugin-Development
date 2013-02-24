<?php
	session_start();
	
	include ("../config.php");
	
	if ($isAuth) {
		$_SESSION["loggedIn"] = true;
		$_SESSION["forumAuth"] = true;
		header("Location: index.php");
	} else {
		if(isset($_SESSION["forumAuth"])) {
			unset($_SESSION["forumAuth"]);
			unset($_SESSION["loggedIn"]);
		}
	}
	if (isset($_SESSION["loggedIn"]))
		header("Location: index.php");
		
	if (isset($_GET["page"]) && $_GET["page"] == "logout") {
		unset($_SESSION["loggedIn"]);
		header("Location: index.php");
	}
?>

