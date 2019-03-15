# TankRoyale
    Tank Royale is a multiplayer game in which players control tanks by moving and shooting.The objective of the 
    game is to eliminate all the other tanks and be the last tank standing. The current version is a GUI beta on 
    the JavaFX platform. Future versions will fix current bugs and improve graphics.
  
# Prerequisites
To play Tank Royale you must have a recent version of:

•	java development kit(JDK) installed on your computer. If not, follow this link: 
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

•	JavaFX installed on your computer. If not, follow this link:  
https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm


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
    
    2. Type: "cd [filepath to unzipped location]\TankRoyale-master\TankRoyale-master\Trial GUI" in the command 
    prompt to change directory. Example is below.   
   ![image](https://user-images.githubusercontent.com/45050947/54311408-1ddfd800-459a-11e9-9a43-d90ba7e4a0b9.png)
   
    3. To compile, type in the command prompt: javac src/codes/*.java
   ![image](https://user-images.githubusercontent.com/45050947/54306612-575f1600-458f-11e9-9fdc-70a9b3cbd05c.png)
   
   ## Running the game
    1. To run the game, type in the command prompt: java -cp src codes.Main
       Make sure to have compiled the game first.
   ![image](https://user-images.githubusercontent.com/45050947/54306625-65149b80-458f-11e9-93a6-92cf57916d3e.png)

# Controls
  The controls for this program are done through user input into the console using a set command list. After each input is given the game's map will redraw itself to match the users input. The console will then prompt the user for another input. This repeats until the game ends.
  
  ## Command list:
  _Player One_
  
  _Control/Command_     | _Action_         
  --------------------- | -------------
  Up arrow              | Moves tank forwards
  Down arrow            | Moves tank backwards
  Right arrow           | Rotate tank clockwise
  Left arrow            | Rotate tank counter clockwise
  Enter Key             | Shoot bullet
  
  _Player Two_
  
  _Control/Command_     | _Action_         
  --------------------- | -------------
  'W'                   | Moves tank forwards
  'S'                   | Moves tank backwards
  'D'                   | Rotate tank clockwise
  'A'                   | Rotate tank counter clockwise
  'Q'                   | Shoot bullet
  
  # Current Bugs
  - Errors with collision detection between walls and tanks
  - Errors with collision detection between walls and bullets (Possibly due to how the map is synthesized)
  
 # Versions
    -Current Version 1.5: GUI Beta
    -Previous Version 1.0: Text-based format
   ![image](https://user-images.githubusercontent.com/45050947/54311996-6ba91000-459b-11e9-9d59-e9c1c916af54.png)
        
        •   Text-based and Turn-based
        •	Movement and shooting occurred in all four cardinal directions. 
        •	Commands were implemented by user input into the console using a set command list. 
            After each input the game's map redrew itself to match the users input and prompt the 
            user for another input. This repeated until the game ended.       
            

    
# Authors
**Code Repository Manager: Harry Chen**- Sets up code repository, versions code for the various deliverables, helps other team members use repository, ensures code in repository compiles and runs.

**Code Reviewer: Andre Staffa** - Provides feedback on the quality of all code submitted to the code repository. 

**Team Lead: Anjola Adeboye** - Facilitates division of labour.

**Meeting Facilitator: Anjola Adeboye** - Organizes time/ location of meetings, ensures meetings stay on track, and all team members can and do contribute during meetings.

**Coordinator: Mei Hou** - Follows up with individual team members to ensure deadlines will be met or if meetings or deadlines are missed.

**Technical Writer: Josh Kim** - Creates documentation required for project such as README file and test document. 

**Architect: Andre Staffa** - Manages overall design of implementation and classes and maintains class diagram and other UML documentation. 

**Communications & Snack Managers: Anjola Adeboye, Mei Hou** - Provide snacks during especially long meetings, encourages team members. Will be rotated through all the members of the group. 


# Acknowledgements
The game was Inspired by the browser game "Tank Trouble", which can be found at: https://tanktrouble.com/
