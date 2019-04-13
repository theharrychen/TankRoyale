# TankRoyale
•    Tank Royale is a 2D topdown deathmatch multiplayer game designed to be played on a single keyboard and computer.

•    The objective of the game is to eliminate all of the other tanks in order to be the last tank standing. 

•    The latest version has accurate collision detection, bullet richochet, various maps, round configuration, and a "sicko" mode.

•    Implemented using Java and the JavaFX library. 
 
  ![image](https://user-images.githubusercontent.com/45050947/55135240-bb85ec00-50f0-11e9-845f-a72cedeb0f32.png)
  ![image](https://user-images.githubusercontent.com/45050947/55135320-f38d2f00-50f0-11e9-8332-f0cfdc7086d1.png)

# Run the Game
    You can test the game out straight away by having a Java Runtime Environment installed and then running Release.jar
    
# Prerequisites for Compilation
To compile Tank Royale you must have a recent version of:

•	java development kit(JDK) installed on your computer. If not, follow this link: 
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

•	JavaFX installed on your computer. If not, follow this link:  
https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm

•	JUnit installed on your computer. If not, follow this link:  
https://www.vogella.com/tutorials/JUnit/article.html


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
    
    2. Type: "cd [filepath to unzipped location]\TankRoyale-master\TankRoyale-master\Demo 3\src" in the command 
    prompt to change directory. Example is below.     
   ![image](https://user-images.githubusercontent.com/45050947/55134671-13bbee80-50ef-11e9-8feb-4f72efdd2eb5.png)
   
    3. To compile, type in the command prompt: javac drivers/*.java
   ![image](https://user-images.githubusercontent.com/45050947/55134686-1e768380-50ef-11e9-89c3-67448bc68577.png)
   
   ## Running the game through the commandline
    1. To run the GUI version of the game, type in the command prompt: Java drivers.MainGUI
       Make sure to have compiled the game first.
   ![image](https://user-images.githubusercontent.com/45050947/55134699-28988200-50ef-11e9-940e-3151798ecb00.png)
   
    2. To run the TextBased version of the game, type in the command prompt: Java drivers.MainTB
       Make sure to have compiled the game first.
   ![image](https://user-images.githubusercontent.com/45050947/55135406-33541680-50f1-11e9-8f74-0264bf613963.png)

   
   ## Testing
    1.To test the code, Type: "cd [filepath to unzipped location]\TankRoyale-master\TankRoyale
        master\Demo 3\src" in the command prompt to change directory as shown above in the Compile section.

    2.To compile the code for testing using the following command: javac -cp .;junit
        4.12.jar;hamcrest-core-1.3.jar drivers/*.java handlers/*.java logic/*.java visuals/*.java 
   ![image](https://user-images.githubusercontent.com/45050947/55151447-e7669900-5113-11e9-8f4a-3d2ebc818c79.png)
 
    3.To run the Junit tests, type: “java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore logic.[Test document        name]” To run all tests at once input TestSuite as the Test document name. Example is shown below.
   ![image](https://user-images.githubusercontent.com/45050947/55151471-f51c1e80-5113-11e9-84b0-fb2fb3f912ee.png)
 


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
  

 # Versions
    - Current Version 3.0: GUI Release
    
    - Previous Version 2.0: GUI Beta
    
   ![image](https://user-images.githubusercontent.com/45050947/55134763-541b6c80-50ef-11e9-9b9d-a9a0338e3c0f.png)
   
    -Previous Version 1.5: GUI Beta
        •   Tanks were visualized as randomly colored Rectangles
        •   Known Bugs (mostly fixed): Passing through walls, random self-implosion, and errors with ricochet

   ![image](https://user-images.githubusercontent.com/45050947/55135503-731afe00-50f1-11e9-8bb5-422b64bf5ebe.png)
   
    -Previous Version 1.0: Text-based format   
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
