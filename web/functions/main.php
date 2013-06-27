<?php

require './functions/PasswordHash.php';

function sqlConnect( $db ) {
	require './functions/config.php';
	mysql_connect( $dbhost, $dbuser, $dbpass );
	mysql_select_db( $db );
}

function DST( $user ) {
	$time = $user->data['user_timezone'] * ( 60 * 60 );
	$time += $user->data['user_dst'] * ( 60 * 60 );
	$time = $time + ( 60 * 60 * 5 );
	return $time;
}
?>