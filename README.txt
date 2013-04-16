Readme

Welcome to the EyeSpy plugin help. So follows the documentation and help information about this plugin

1.0: Installation
1.1: Plugin Installation

HeroChat, found at http://dev.bukkit.org/server-mods/herochat/, is required as a prerequisite to load this plugin. To install HeroChat on your server, drag and drop the downloaded .jar file into your server plugins folder.

To install the EyeSpy plugin itself, simply open up the plugins folder located within your server folder and drop the .jar file for EyeSpy inside. After this, run the server once (yes you will get a lot of errors, but that is normal) and stop it a moment later.

Go into your SQL console and add a user for this plugin, the user will need to have complete control over a single database which you create, for example: the user Username will need to have complete control over the database EyeSpy. That's not all though, the username will need to be able to at least view the tables inside your phpbb database in order to authorise the web section later on.

Move into the plugins folder again and into the newly-generated ‘EyeSpy’ folder where a config.yml file should be waiting. Open this in the word editor of your choice and enter the details as pertain to you.

•	Host		: This needs to be the IP address of your SQL server
•	Username	: This needs to be the username that you have allocated access to
•	Password	: The password of the username above. Should be encapsulated by 's (so 'password')
•	Database	: The database that has been created and the user above has access to

Once these options have been altered, save the config file and start the server once more. There will (hopefully) be a lot less in the way of errors now that you are connected to your SQL database and there will be a few messages with the prefix [EyeSpy] that will appear on the console.

Once the server has started up completely, that's it... you're done, the plugin has been loaded.

1.2: Web Interface Installation

Now assuming that you don't want to search through the SQL tables themselves unaided, you'll have also downloaded the web.zip file which contains a number of files of its own. Extract these files into your web directory and fill in the fields inside the config.php file as above, filling out the dbES variable with the EyeSpy database and the dbforum variable with the database that houses your phpbb forums.

From there, use your PHP server to access the files and test them out, anyone within the 'server_mod' group should be able to log into the website and view any and all of the logs.

2.0: The Website
2.1: Viewing and Filtering the Chat Logs

For viewing and filtering the files, the user must be logged in. Navigate to the index page and select the Chat logs page which will take you to said page. On here the logs are filtered chronologically by last entry first. This will show you the last thirty pieces of chat across every channel for every player.

Use the arrow keys to navigate in between the chat pages as you see fit, but if you wish to filter the logs to a specific time across specific channels for specific players, you should use the filter boxes located below the chat to optimise your search. If you are selecting multiple players, please insert a comma between their names, the same goes for if you are selecting multiple channels too.

Once you have refined your search criteria, click search and the page will reload and find any and all items that you have requested, again in chronological order with the most recent chat event appearing first.

2.2: Viewing and Filtering the Command Logs

Viewing the command logs is very similar to viewing the chat logs, you navigate via the index page to the command log page, and you are presented with the latest thirty commands executed on the server. You can navigate back and forth down the timeline using the arrows at the top of the page.

You can also filter the commands too by aid of the filter boxes below the page; these allow you to search between specific dates and times and for specific players. Multiple players should be searched for with a comma between their names.

Once you have selected your criteria, select search and the page will show you with the latest thirty results that are compatible to your search.

2.3: Viewing and Filtering the Block Logs

Viewing and filtering any block logs is somewhat impossible right now since the block logging is not included yet. But the page still exists and you can navigate to it via the index page. On arrival, you will be greeted with the latest thirty block events on the server and the buttons to navigate through the timeline.

The bottom of the page will also include an extensive option for filtering, including selecting what blocks you want to search for, whether it's a block placement or breakage, the player, the world and other options pertaining to the data above.

Once you have chosen at least one search filter, you can click search and the logs shall be filtered into pages of thirty which all fulfil the filtered requests.

3.0: Troubleshooting
3.1: The plugin doesn't load on start-up!

This could be due to a number of reasons, firstly check that your details for the config.yml are correct and that you are indeed connecting to the SQL server. Also check that the user you have assigned to the database can create tables and rows inside them. If this is not the case, check that Herochat is installed on your server so that EyeSpy can use the functions it offers. If neither of these suffice, send a message to the contact address below with the full error log that you were given regarding EyeSpy.

3.2: The console is giving out errors whenever someone does something!

This means that the tables are not letting you enter data. Go into your SQL console and check that the user you have assigned to this database has editing rights for the tables, and check that the tables are there for that matter. The error given in the console will be as specific as it can be, but it is always possible that something else entirely has broken in regards to the plugin. In this case, send the console log to the contact address below for the logs pertaining to EyeSpy.

3.3: The website isn't letting me log in!

First of all, check that you are using your forum name and password for signing into the database, then check that you are within the 'server mod' group. Only people within the server mod group will be allowed to log in to the website, therefore if you are not in said group, this means you cannot log in. Case and point.

4.0: Contact Details

If there is something problematic or glitchy within the plugin which is due to a fault within the server, please contact me at 'm.477h3w@live.co.uk' with the subject of the e-mail being 'EyeSpy problem'.

Thank you