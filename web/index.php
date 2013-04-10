<?php
	session_start();
    if ( $_SESSION['loggedIn'] != '1' ) {
        if (isset($_SESSION['active']))
            session_destroy();
        session_start();
        $_SESSION['active'] = "1";
        header("Location: login.php");
    }
    
    echo "<a href='logout.php'>Log Out</a>";
    echo "Hello!";
    echo "loggedIn: " . $_SESSION['loggedIn'];
    echo "Active: " . $_SESSION['active'];
    echo "<a href='test.php'>Chat</a>";
?>