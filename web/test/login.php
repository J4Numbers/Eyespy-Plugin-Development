<?php
	IF (strlen($_POST["username"])==0)
		{
			echo "Nothing Entered";
		}
	else
		{
			//connect to the web server
			$con = mysql_connect("sql.wokka.org", "root","Nopants!");
			//Check if the connection has been established
			if (!$con)
				{
					//report the error
					die('Cound not connect: ' . mysql_error());
				}
				
			//Connect to the database
			mysql_select_db("escapecr_forum", $con);
			
			//Generate the SQL statement
			$sql="SELECT username,group_id FROM phpbb_users WHERE username='$_POST[username]' AND group_id='30' OR group_id='8' OR group_id='11' OR group_id='29'";
			//Execute SQL with error checking
			if (!mysql_query($sql,$con))
				{
					//Report the error
					die('Error: ' . mysql_error());
				}
			else
				{
					$result = mysql_query($sql);
					IF (empty($result)) {
						Echo "This person is not authorised"; }
					ELSE {
						Echo "This person is authorised": }
					mysql_close($con);
				}
		}
?>