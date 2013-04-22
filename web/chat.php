<?php
require_once './functions/page.php';
require './functions/config.php';
require_once './functions/main.php';

session_start();

$style = "<link rel='stylesheet' href='./js/jquery-ui.css' />
		<link rel='stylesheet' href='./js/jquery.ui.timepicker.css' />
		<script src='./js/jquery-1.9.1.js'></script>
		<script src='./js/jquery-ui.js'></script>
		<script src='./js/jquery.ui.timepicker.js'></script>

		<script>
			$(function() {
			$( '#datepicker' ).datepicker();
			});
			
			$(function() {
			$( '#timepicker' ).timepicker();
			});
		</script>";

if ( !isset($_SESSION['loggedIn']) ) {
    if (isset($_SESSION['active']))
        session_destroy();
    session_start();
    $_SESSION['active'] = "1";
    header("Location: login.php");
}

if ( @$_POST["Player"] == '' ) {
    $Player = "";
} else {
    $Player = $_POST["Player"];
}

if ( @$_POST["Channel"] == '' ) {
    $Channel = "";
} else {
    $Channel = $_POST["Channel"];
}

sqlConnect( $dbES );

	$result = mysql_query("SELECT * FROM chat 
                            JOIN chatchannels ON chat.ch_id=chatchannels.ch_id
                            JOIN players ON chat.player_id=players.player_id
                            WHERE ch_name LIKE '%" . mysql_real_escape_string( $Channel ) . "%' 
							AND pl_name LIKE '%" . mysql_real_escape_string( $Player ) . "%'
							ORDER BY `date` DESC");

    $output .= "<table border='2'>
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
            $output .= "<td>" . date( "Y-m-d H:i:s" , $row['date'] + DST($_SESSION['loggedIn']) ) . "</td>";
            $output .= "<td>" . $row['pl_name'] . "</td>";
            $output .= "<td>" . $row['ch_name'] . "</td>";
            $output .= "<td>" . $row['message'] . "</td>";
            $output .= "<br />";
        }
    
    $output .=    "</table>";
    
    $output .= "<form name='input' action='chat.php' method='post'>
            Player: <input type='text' name='Player' /><br />
            Channel: <input type='text' name='Channel' /><br />
			Date From: <input type='text' id='datepicker' name='D1' /><br />
			Time From: <input type='text' id='timeFrom' name='T1' /><br />
			Date To: <input type='text' id='datepicker' name='D2' /><br />
			Time To: <input type='text' id='timeTo' name='T2' /><br />
            <input type='submit' value='Filter' />
            </form>";
mysql_close();
?> 

<HTML>
	<?php HeaderThing( 'Chat', $style ); ?>
		<?php echo $output; ?>
	<?php FooterThing(); ?>
</HTML>