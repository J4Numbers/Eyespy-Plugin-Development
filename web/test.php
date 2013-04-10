<?php
require 'config.php';

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

if ( @$_POST["cPlayer"] == '' ) {
    $cPlayer = "%";
} else {
    $cPlayer = $_POST["cPlayer"];
}

mysql_connect($dbhost, $dbuser, $dbpass);
mysql_select_db( $dbESdb );

    echo "Player = " . $Player . "<br />";
    echo "Channel = " . $Channel . "<br />";
    echo "CPlayer = " . $cPlayer . "<br />";
	echo "SELECT * FROM chat 
                JOIN chatchannels ON chat.ch_id=chatchannels.ch_id
                JOIN players ON chat.player_id=players.player_id
                WHERE ch_name LIKE '%" . $Channel . "%' AND pl_name LIKE '%" . $Player . "%';<br />";
	$result = mysql_query("SELECT * FROM chat 
                            JOIN chatchannels ON chat.ch_id=chatchannels.ch_id
                            JOIN players ON chat.player_id=players.player_id
                            WHERE ch_name LIKE '%" . $Channel . "%' AND pl_name LIKE '%" . $Player . "%';");

    echo    "<table border='2'>
                <thead>
                    <td>Chat ID</td>
                    <td>Date and Time</td>
                    <td>Player</td>
                    <td>Channel</td>
                    <td>Message</td>
                </thead>";
    
    while($row = mysql_fetch_array($result))
        {
            echo "<tr>";
            echo "<td>" . $row['chat_id'] . "</td>";
            echo "<td>" . $row['date'] . "</td>";
            echo "<td>" . $row['pl_name'] . "</td>";
            echo "<td>" . $row['ch_name'] . "</td>";
            echo "<td>" . $row['message'] . "</td>";
            echo "<br />";
        }
    
    echo    "</table>";
    
    echo "<form name='input' action='test.php' method='post'>
            Player: <input type='text' name='Player' /><br />
            Channel: <input type='text' name='Channel' /><br />
            <input type='submit' value='Filter' />
            </form>";
    
    $cmdresult = mysql_query("SELECT * FROM commands
                                JOIN players ON commands.player_id=players.player_id
                                WHERE pl_name LIKE '%" . $cPlayer . "%';");
    echo    "<table border='2'>
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
            echo "<tr>";
            echo "<td>" . $cmdrow['cmd_id'] . "</td>";
            echo "<td>" . $cmdrow['date'] . "</td>";
            echo "<td>" . $plRow['pl_name'] . "</td>";
            echo "<td>" . $cmdrow['command'] . "</td>";
            echo "<br />";
        }
        echo "</table>";
        echo "<form name='command' action='test.php' method='post'>
            Player: <input type='text' name='cPlayer' /><br />
            <input type='submit' value='Filter' />
            </form>";

mysql_close();
?> 

<HTML>
    <head>
        <title>Chat Test</title>
    </head>
    <body>
        <a href="index.php">Index</a>
    </body>
</HTML>