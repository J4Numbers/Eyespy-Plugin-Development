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

if ( @$_POST["cPlayer"] == '' ) {
    $cPlayer = "%";
} else {
    $cPlayer = $_POST["cPlayer"];
}

sqlConnect( $dbES );

    $output = "CPlayer = " . $cPlayer . "<br />";
	
    $cmdresult = mysql_query("SELECT * FROM commands
                                JOIN players ON commands.player_id=players.player_id
                                WHERE pl_name LIKE '%" . mysql_real_escape_string( $cPlayer ) . "%'
								ORDER BY `date` DESC;");
    $output .=    "<table border='2'>
                <thead>
                    <td>Command ID</td>
                    <td>Date and Time</td>
                    <td>Player</td>
                    <td>Command</td>
                </thead>";
    while($cmdrow = mysql_fetch_array($cmdresult))
        {
            $plResult = mysql_query("SELECT * FROM players WHERE player_id='" . $cmdrow['player_id'] . "'");
            $plRow = mysql_fetch_array($plResult);
            $output .= "<tr>";
            $output .= "<td>" . $cmdrow['cmd_id'] . "</td>";
            $output .= "<td>" . $cmdrow['date'] . "</td>";
            $output .= "<td>" . $plRow['pl_name'] . "</td>";
            $output .= "<td>" . $cmdrow['command'] . "</td>";
            $output .= "<br />";
        }
        $output .= "</table>";
        $output .= "<form name='command' action='commands.php' method='post'>
            Player: <input type='text' name='cPlayer' /><br />
            <input type='submit' value='Filter' />
            </form>";

mysql_close();
?> 

<HTML>
	<?php HeaderThing( 'Commands' ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>