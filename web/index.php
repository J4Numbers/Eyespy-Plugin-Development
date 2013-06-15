<?php
	require_once './functions/page.php';
	require_once './functions/main.php';
	require_once './functions/config.php';
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
		}
    } else {
		header("Location: login.php");
		die();
	}
    
    $output = "<a href='".$phpbb_root_path."/ucp.php?mode=logout&sid=" . $user->data['session_id'] . "&redirect=http://qa.wokka.org/m477/web/login.php'>Log Out</a><br />";
    $output .= "Hello " . $user->data['username'] . "!<br />";
    $output .= "<a href='chat.php'>Chat</a><br />";
	$output .= "<a href='commands.php'>Commands</a><br />"
?>

<HTML>
	<?php HeaderThing( 'Index' ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>