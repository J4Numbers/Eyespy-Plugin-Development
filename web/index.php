<?php
	require_once './functions/page.php';
	session_start();
    if ( !isset($_SESSION['loggedIn']) ) {
        if (isset($_SESSION['active']))
            session_destroy();
        session_start();
        $_SESSION['active'] = "1";
        header("Location: login.php");
    }
    
    $output = "<a href='logout.php'>Log Out</a><br />";
    $output .= "Hello " . $_SESSION['loggedIn'] . "!<br />";
    $output .= "<a href='chat.php'>Chat</a><br />";
	$output .= "<a href='commands.php'>Commands</a><br />"
?>

<HTML>
	<?php HeaderThing( 'Index' ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>