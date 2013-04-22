<?php

require './functions/PasswordHash.php';

function sqlConnect( $db ) {
	require './functions/config.php';
	mysql_connect( $dbhost, $dbuser, $dbpass );
	mysql_select_db( $db );
}

function idToName( $id ) {
	require './functions/config.php';
	sqlConnect( $dbforum );
	$sql = "SELECT `user_id`,`username` 
			FROM `phpbb_users`
			WHERE `user_id`='" . $id . "'";
	$res = mysql_fetch_array( mysql_query( $sql ) );
	mysql_close();
	return $res['username'];
}

function login( $username, $password ) {
	$sql = "SELECT `user_id`,`username`,`user_password` FROM `phpbb_users` WHERE `username` = '" . $username . "' ";
	$res = mysql_query( $sql );
	if ( !mysql_query( $sql ) ) {
		
	}
	$row = mysql_fetch_array( $res );
	
	#$sql = "SELECT * FROM `phpbb_user_group` WHERE ( `user_id` = '" . $row['user_id'] . "' ) AND ( `group_id` IN ( '8', '30', '29' ) )";
	#echo $row['user_id'];
	#if ( mysql_num_rows( mysql_query( $sql ) ) != 0 ) {
	#	$group = true;
	#} else {
	#	$group = false;
	#}
	
	mysql_close();
	
	#$PasswordHasher = new PasswordHash(8, true);
	#if ( ($PasswordHasher->CheckPassword($password, $row['user_password']) ) && ( $group ) ) {
		$_SESSION['loggedIn'] = $row['user_id'] ;
		header( "Location: ./index.php");
	#} else {
	#	$fail = true;
	#}
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