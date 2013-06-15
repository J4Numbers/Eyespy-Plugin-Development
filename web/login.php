<?php
	require_once './functions/page.php';
	require './functions/config.php';
	require_once './functions/main.php';
	session_start();
	
	define('IN_PHPBB', true);
	$phpbb_root_path = (defined('PHPBB_ROOT_PATH')) ? PHPBB_ROOT_PATH : '../phpBB3/';
	$phpEx = substr(strrchr(__FILE__, '.'), 1);
	include($phpbb_root_path . 'common.' . $phpEx);
	
	require_once $phpbb_root_path.'/includes/functions_user.php';

	// Start session management
	$user->session_begin();
	$auth->acl($user->data);
	$user->setup();
	
	if ( $user->data['is_registered'] ) {
		if ( !group_memberships( Array( 5, 8 ), $user->data['user_id'] ) ) {
			header("Location: login-failed.php");
			die();
		} else {
			header("Location: index.php");
			die();
		}
    }
    
    $output = "login <br />";
	if ( isset( $username ) ) {
		$output .= "<font color='red'>Your username or password was incorrect</font><br />";
	}
    $output .= "<br />";
    
    $output .= "<form action='".$phpbb_root_path."/ucp.php?mode=login' method='post'>
          <table>
		  <tr><td>Username:</td><td><input type='text' name='username' size='20' title='Username' /></td></tr>
          <tr><td>Password:</td><td><input type='password' name='password' size='20' title='Password' /></td></tr></table>
          <input id='login_button' type='submit' name='login' value='Login' />
		  <input type='hidden' id='login_redirect' name='redirect' value='http://qa.wokka.org/m477/web/index.php' />
          </form>";
?>

<HTML>
	<?php HeaderThing( 'Login' ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>