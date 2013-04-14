<?php
require './functions/config.php';
require './functions/PasswordHash.php';

function sqlConnect( $db ) {
	require './functions/config.php';
	mysql_connect( $dbhost, $dbuser, $dbpass );
	mysql_select_db( $db );
}

function login( $username, $password ) {
	$sql = "SELECT `user_id`,`username`,`user_password` FROM `phpbb_users` WHERE `username` = '" . $username . "' ";
	$res = mysql_query( $sql );
	$row = mysql_fetch_array( $res );
	
	$sql = "SELECT * FROM `phpbb_user_group` WHERE ( `user_id` = '" . $row['user_id'] . "' ) AND ( `group_id` = '8' )";
	
	if ( mysql_num_rows( mysql_query( $sql ) ) == 0 ) {
		$group = true;
	} else {
		$group = false;
	}
	
	mysql_close();
	
	$PasswordHasher = new PasswordHash(8, true);
	if ( ($PasswordHasher->CheckPassword($password, $row['user_password']) ) && ( $group ) ) {
		$_SESSION['loggedIn'] = $username;
		header( "Location: ./index.php");
	} else {
		$fail = true;
	}
}
?>