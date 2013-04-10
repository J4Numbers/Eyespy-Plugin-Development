Readme

Welcome to the EyeSpy plugin help. So follows the documentation and help information about this plugin

1.1: Plugin Installation

HeroChat, found at [INSERT LINK HERE], is required as a prerequisite to load this plugin. To install HeroChat on your server, drag and drop the downloaded .jar file into your server plugins folder.

To install the EyeSpy plugin itself, simply open up the plugins folder located within your server folder and drop the .jar file for EyeSpy inside. After this, run the server once (yes you will get a lot of errors, but that is normal) and stop it a moment later.

Go into your SQL console and add a user for this plugin, the user will need to have complete control over a single database which you create, for example: the user Username will need to have complete control over the database EyeSpy. That's not all though, the username will need to be able to at least view the tables inside your phpbb database in order to authorise the web section later on.

Move into the plugins folder again and into the newly-generated ‘EyeSpy’ folder where an EyeSpy.yml file should be waiting. Open this in the word editor of your choice and enter the details as pertain to you.

•	Host		: This needs to be the ip address of your SQL server
•	Username	: This needs to be the username that you have allocated access to
•	Password	: The password of the username above. Should be encapsulated by 's (so 'password')
•	Database	: The database that has been created and the user above has access to

Once these options have been altered, save the config file and start the server once more. There will (hopefully) be a lot less in the way of errors now that you are connected to your SQL database and there will be a few messages with the prefix [EyeSpy] that will appear on the console.

Once the server has started up completely, that's it... you're done, the plugin has been loaded.

1.2: Web Interface Installation

Now assuming that you don't want to search through the SQL tables themselves unaided, you'll have also downloaded the web.zip file which contains a number of files of its own. Extract these files into your web directory and fill in the fields inside the config.php file as above, filling out the dbESdb variable with the EyeSpy database and the dbforum variable with the database that houses your phpbb forums.

From there, use your PHP server to access the files and test them out, anyone within the 'server_mod' group should be able to log into the website and view any and all of the logs.

2.1: Viewing and Filtering the Chat Logs