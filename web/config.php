<?php
///////////////////////////////////////////
////                                   ////
////                                   ////
///////////////////////////////////////////

	$EyeSpyConfig = array
					//Enter your MySQL database information
					//Do not change 'dbTable'
					"dbHost"  => "localhost:3306",
					"dbDbase" => "eyespy",
					"dbUser"  => "minecraft",
					"dbPass"  => "plutarch",
					"dbBlocksTable" => "blocks",
					"dbChatTable" => "chat",
					"dbCCTable" => "chat channels",
					"dbChestTable" => "chests",
					"dbPlayerTable" => "players",
					"dbWorldTable" => "worlds",
					
					//Set this to the password you want people to have to use to access the interface
					//Leave blank for no password
					"password" => "",
					
					//Default radius to search for if user supplies a location
					"radius" => 30,
					
					//Limit the maximum number of results that can be returned. Do not use quotes
					//Set to 0 for no limit
					"maxResults" => 0,
					
					//Language pack - default is english.php
					//You can create your own based off the english.php file,
					//then change the name below to your language file name
					"langFile" => "english.php",
					
					//Log queries to log.txt
					//Useful to keep track of who is querying what
					"logQueries" => true,
					
					//Use forum for authentication?
					//Default is false
					"forumAuth" => false,

					//The relative location for the forum to this file.
					//Default is "../forum/"
					"forumDir" => "../forum/",

					//The type of forum that will be used.
					//Default is phpbb3
					//Available types are: phpbb3 and smf
					"forumType" => "phpbb3"
					
					);
					
	//The user wants to use forumAuth
	if ($EyeSpyConfig["forumAuth"])
	{
		//We need to require the specified forumType.  Hopefully we support it (Or the user is using their own file)
		require('./forumAuth/'.$EyeSpyConfig['forumType'].'.php');
	}
	
	$con = mysql_connect($EyeSpyConfig["dbHost"], $EyeSpyConfig["dbUser"], $EyeSpyConfig["dbPass"]);
	if (!$con)
		return error("Could not connect to MySQL Database!");
	if (!mysql_select_db($EyeSpyConfig["dbDbase"], $con))
		return error(mysql_error());
		
	mysql_query("SET NAMES UTF8");
	
	function handleError($errno, $errstr, $errfile, $errline, array $errcontext) {
    	// error was suppressed with the @-operator
    	if (0 === error_reporting()) return false;
    	throw new ErrorException($errstr, 0, $errno, $errfile, $errline);
	}
		
?>
