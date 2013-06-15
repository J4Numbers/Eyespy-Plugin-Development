<?php

require './functions/PasswordHash.php';

function sqlConnect( $db ) {
	require './functions/config.php';
	mysql_connect( $dbhost, $dbuser, $dbpass );
	mysql_select_db( $db );
}

function DST( $id ) {
	require './functions/config.php';
	sqlConnect( $dbforum );
	$sql = "SELECT `user_id`,`user_timezone`,`user_dst` 
			FROM `phpbb_users` 
			WHERE `user_id`='" . $id . "'";
	$res = mysql_fetch_array( mysql_query( $sql ) );
	$time = $res['user_timezone'] * ( 60 * 60 );
	$time += $res['user_dst'] * ( 60 * 60 );
	return $time;
}
?>