<?php
	require_once './functions/page.php';
	require './functions/config.php';
	require_once './functions/main.php';
	session_start();
	unset( $fail );
    if (isset($_SESSION['loggedIn'])) 
        header( "Location: index.php" );
        
    if (!isset($_SESSION['active'])) {
        $_SESSION['active'] = "1";
    }
    
    if ( @$_POST['user'] != '' ) {
        $username = $_POST['user'];
        $password = $_POST['pass'];
		sqlConnect( $dbforum );
		login( $username, $password );
    }
    
    $output = "login <br />";
	if ( isset( $username ) ) {
		$output .= "<font color='red'>Your username or password was incorrect</font><br />";
	}
    $output .= "<br />";
    
    $output .= "<form name='input' action='login.php' method='post'>
          <table><tr><td>Username:</td><td><input type='text' name='user' /></td></tr>
          <tr><td>Password:</td><td><input type='password' name='pass' /></td></tr></table>
          <input type='submit' value='Login' />
          </form>";
?>

<HTML>
	<?php HeaderThing( 'Login' ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>