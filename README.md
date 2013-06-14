Eyespy-Plugin-Development
=========================

Hello and welcome. Numbers here, and welcome to EyeSpy, the logging plugin for bukkit. This plugin can also interface with CraftIRC3 as long as you modify the respective function in there.

Versions
--------

The current version of CraftBukkit ( 1.5.2 R0.2 ) is supported by this plugin.
However, this plugin does require HeroChat version 5, and as a consequence, Vault, to run.

The current version of the plugin itself is still in the pre-alpha stage while testing of its initial chat functions is continuing.

Config
------

Inside the config, you have two sections. The database section is pretty much monkey see, monkey fill in the blanks.

The second part of the config is the server options. If you are running a multiworld server, the 'server' option allows you to name the individual servers. The debug is pretty sparse at the moment, only echoing the data that passes through the plugin. Finally, the ignore option is for anyone that you wish to be excempt from chat logging. Each person must be separated by a comma, or alternatively using the other list format as follows:

ignore:
  - person1
  - person2
  - person3

~M4Numbers