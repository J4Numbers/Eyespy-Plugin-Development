<?php
	require 'config.php';
	require_once 'PasswordHash.php';
	session_start();
    if (isset($_SESSION['loggedIn'])) 
        header( "Location: index.php" );
        
    if (!isset($_SESSION['active'])) {
        $_SESSION['active'] = "1";
    }
    
    if ( $_POST['user'] != '' ) {
        $username = $_POST['user'];
        $password = $_POST['pass'];
		echo "User = " . $username . "<br />";
        echo "Pass = " . $password . "<br />";
		mysql_connect( $dbhost, $dbuser, $dbpass );
		mysql_select_db( $dbforumdb );
		$sql = "SELECT `user_id`,`username`,`user_password` FROM `phpbb_users` WHERE `username` = '" . $username . "' ";
		$res = mysql_query( $sql );
		$row = mysql_fetch_array( $res );
		
		$sql = "SELECT * FROM `phpbb_user_group` WHERE ( `user_id` = '" . $row['user_id'] . "' ) AND ( `group_id` = '8' )";
		
		if ( mysql_num_rows( mysql_query( $sql ) ) == 0 ) {
			$group = true;
		} else {
			$group = false;
		}
		
		$PasswordHasher = new PasswordHash(8, true);
		if ( ($PasswordHasher->CheckPassword($password, $row['user_password']) ) && ( $group ) ) {
			$_SESSION['loggedIn'] = '1';
			header( "Location: index.php");
			echo "Success!";
		}
    }
    
    echo "login <br />";
    echo "Active: " . $_SESSION['active'] . "<br />";
    echo "loggedIn: " . @$_SESSION['loggedIn'] . "<br />";
    echo "<br />";
    
    echo "<form name='input' action='login.php' method='post'>
          UserName: <input type='text' name='user' /><br />
          Password: <input type='password' name='pass' /><br />
          <input type='submit' value='Login' />
          </form>";
?>