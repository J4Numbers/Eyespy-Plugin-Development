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
		}
    } else {
		header("Location: login.php");
		die();
	}
	
$style = "<script>
			$(function() {
			$( '#dateto' ).datepicker();
			$( '#datefrom' ).datepicker();
			});
			
			$(function() {
			$( '#timefrom' ).timepicker();
			$( '#timeto' ).timepicker();
			});
		</script>";

if ( @$_POST["cPlayer"] == '' ) {
    $cPlayer = "";
} else {
    $cPlayer = $_POST["cPlayer"];
}

$a1 = 0;
$a2 = 99999999999;

if ( @$_POST["D1"] != '' ) {
	$a1 = strtotime( $_POST["D1"] );
}

if ( @$_POST["T1"] != '' ) {
	$time1 = explode( ':', $_POST["T1"] );
	$newtime = ( $time1[0] * 60 * 60 ) + ( $time1[1] * 60 );
	$a1 = $a1 + $newtime;
}

if ( @$_POST["D2"] != '' ) {
	$a2 = strtotime( $_POST["D2"]);
}

if ( @$_POST["T2"] != '' ) {
	$time2 = explode( ':', $_POST["T2"] );
	$newtime = ( $time2[0] * 60 * 60 ) + ( $time2[1] * 60 );
	$a2 = $a2 + $newtime;
}

$DateQuery = "AND ( `date` BETWEEN ".$a1." AND ".$a2." )";

sqlConnect( $dbES );
	$sql = "SELECT * FROM ".$prefix."commands
            JOIN ".$prefix."players ON ".$prefix."commands.player_id=".$prefix."players.player_id
            JOIN ".$prefix."servers ON ".$prefix."commands.ser_id=".$prefix."servers.ser_id
            WHERE pl_name LIKE '%" . mysql_real_escape_string( $cPlayer ) . "%'
            ".$DateQuery."
			ORDER BY `date` DESC";
    $cmdresult = mysql_query( $sql );
    $output .=    "<table><form name='input' action='chat.php' method='post'>
        			    <tr>
							<td>Player: </td>
							<td><input type='text' name='Player' /></td>
							<td>Server: </td>
							<td><input type='text' name='Server' /></td>
						</tr><tr>
							<td>Date From: </td>
							<td><input type='text' id='datefrom' name='D1' readonly='true' /></td>
							<td>Date To: </td>
							<td><input type='text' id='dateto' name='D2' readonly='true' /></td>
						</tr><tr>
							<td>Time From: </td>
							<td><input type='text' id='timefrom' name='T1' readonly='true' /></td>
							<td>Time To: </td>
							<td><input type='text' id='timeto' name='T2' readonly='true' /></td>
						</tr><tr>
							<td colspan='4'><span align='center'><input type='submit' value='Filter' /></span></td>
						</tr>
		</form></table>
		<table border='2'>
                <thead>
                    <td width='150px' >Date and Time</td>
                    <td width='110px' >Server</td>
                    <td width='125px' >Player</td>
                    <td>Command</td>
                </thead>";
    while($cmdrow = mysql_fetch_array($cmdresult))
        {
            $output .= "<tr>";
            $output .= "<td>" . date( "Y-m-d H:i:s", $cmdrow['date'] + DST($_SESSION['loggedIn']) ) . "</td>";
            $output .= "<td>" . $cmdrow['ser_name'] . "</td>";
            $output .= "<td>" . $cmdrow['pl_name'] . "</td>";
            $output .= "<td>" . $cmdrow['command'] . "</td></tr>";
        }
        $output .= "</table>";

mysql_close();
?> 

<HTML>
	<?php HeaderThing( 'Commands', $style ); ?>
		<?php echo $output;#.$sql; ?>
	<?php FooterThing(); ?>
</HTML>
