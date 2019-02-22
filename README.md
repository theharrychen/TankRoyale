# TankRoyale
  - Here goes an overview (and features?) of the game and what it needs
  - Tank Royale is a single or multi-player game in which players control tanks by moving and shooting. The objective of the game is to eliminate all the other tanks being the last tank standing. 
  - include that the current version is text based and turn-based
  - also that it features movement and shooting in all four cardinal directions
# Prequisites
To play Tank Royale you must have a recent version of java development kit(JDK) installed on your computer. 
If not, follow this link:
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

# Getting Started 
  These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
  
  ## Installation 
    1. Download the TankRoyale Master zip file: https://github.com/AnjolaAA/TankRoyale.git
   ![image](https://user-images.githubusercontent.com/45050947/53202462-c0ccb400-35e3-11e9-87d7-69156973d394.png)
    
    2. Locate TankRoyale-Master.zip folder inside your directory (By default, it should be in the downloads folder)
    3. Unzip file to a directory of your choice by right-clicking TankRoyale-Master.zip and choosing extract all. 
   ![image](https://user-images.githubusercontent.com/45050947/53202374-83682680-35e3-11e9-9983-e4843f387164.png)
    
  ## Compilation
    1. Open the terminal (command prompt) 
    2. Type: "cd [filepath to unzipped location]\TankRoyale Text Based" in the command prompt to change directory. 
       Example is below.
   ![image](https://user-images.githubusercontent.com/45050947/53205726-62580380-35ec-11e9-9def-bc3ff0791233.png)
   
    3. To compile, type in the command prompt: javac src/codes/*.java
   ![image](https://user-images.githubusercontent.com/45050947/53205759-7f8cd200-35ec-11e9-9373-d5786f76a78b.png)
   
    4. To run the game, type in the command prompt: java -cp src codes.Main
   ![image](https://user-images.githubusercontent.com/45050947/53205831-aea34380-35ec-11e9-8639-fda12dbf2fd5.png)

# Controls
  The controls for this program are done through user input into the console using a set command list. After each input is given the games map will redraw itself to match the users input and the console will then prompt the user for another input. This repeats until the game ends.
  ## Command list:
  _Movement_    | _Shooting_
  ------------- | -------------
  Moveup        | Shootup
  Movedown      | Shootdown
  Moveright     | Shootright
  Moveleft      | Shootleft
  
*Commands are not case-sensitive
 # Versions
    - Version 1.0:Text-based format
    
# Authors
Harry Chen, Mei Hou, Joshua Kim, Anjola Adeboye, Andre Staffa

# Acknowledgements
The game was Inspired by the browser game "Tank Trouble", which can be found at: https://tanktrouble.com/

# File List
  - where we list all the files needed/included for this game/program to run well
