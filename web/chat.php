<?php
require_once './functions/page.php';
require './functions/config.php';
require_once './functions/main.php';

session_start();

if ( !isset($_SESSION['loggedIn']) ) {
    if (isset($_SESSION['active']))
        session_destroy();
    session_start();
    $_SESSION['active'] = "1";
    header("Location: login.php");
}

if ( @$_POST["Player"] == '' ) {
    $Player = "%";
} else {
    $Player = $_POST["Player"];
}

if ( @$_POST["Channel"] == '' ) {
    $Channel = "%";
} else {
    $Channel = $_POST["Channel"];
}

sqlConnect( $dbES );

    $output = "Player = " . $Player . "<br />";
    $output .= "Channel = " . $Channel ;
	$result = mysql_query("SELECT * FROM chat 
                            JOIN chatchannels ON chat.ch_id=chatchannels.ch_id
                            JOIN players ON chat.player_id=players.player_id
                            WHERE ch_name LIKE '%" . mysql_real_escape_string( $Channel ) . "%' 
							AND pl_name LIKE '%" . mysql_real_escape_string( $Player ) . "%'
							ORDER BY `date` DESC");

    $output .=    "<table border='2'>
                <thead>
                    <td>Chat ID</td>
                    <td>Date and Time</td>
                    <td>Player</td>
                    <td>Channel</td>
                    <td>Message</td>
                </thead>";
    
    while($row = mysql_fetch_array($result))
        {
            $output .= "<tr>";
            $output .= "<td>" . $row['chat_id'] . "</td>";
            $output .= "<td>" . $row['date'] . "</td>";
            $output .= "<td>" . $row['pl_name'] . "</td>";
            $output .= "<td>" . $row['ch_name'] . "</td>";
            $output .= "<td>" . $row['message'] . "</td>";
            $output .= "<br />";
        }
    
    $output .=    "</table>";
    
    $output .= "<form name='input' action='chat.php' method='post'>
            Player: <input type='text' name='Player' /><br />
            Channel: <input type='text' name='Channel' /><br />
            <input type='submit' value='Filter' />
            </form>";
mysql_close();
?> 

<HTML>
	<?php HeaderThing( 'Chat' ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>