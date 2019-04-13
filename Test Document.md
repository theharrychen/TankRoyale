# Tank Royale Testing Instructions
Before running any JUnit tests please go through these simple in-game tests to ensure the game was downloaded properly and the correct version is running. 

(Instructions to compile and run the game as well as game controls can be found in the README document)

# In-Game Testing
Simple tests to check to see if all the game mechanics are working as intended. If any errors occur please check your installation or download.

  ## Text Based
  1. When the game is run ensure that a menu screen is the first to appear.
  2. Once a map is selected check to see if the map generated matches the selected map.
  3. Once in game ensure each console command is working as intended and walls block movement and bullets (i.e, the moveup command only moves the player up if there is no wall above the player)
  4. Check to see once a player is hit with a bullet the game ends and a end screen is shown. To do this ensure the players are aligned and use the shoot command in the direction of the other player.
  5. Check to see that the end game screen displays the correct winner and prompts the user to restart the game.
  6. Check to see if restart is chosen the game restarts with the menu screen, or that the program ends if restart is not chosen.
  
  ## GUI
  1. When running the game ensure the first screen is the main menu screen. (See README for an image of the menu screen).
  2. Check to see if the selection buttons are working (map selection and maximum point count) and the game only starts once the start button is pressed.
  3. Check all the controls to ensure each one is working as intended.
  4. Check to see if a player is hit with a bullet the point is awarded to the other player and both tanks disappear and respawn to start the next round.
  5. Check to see that once the selected point limit is reached the game ends.
  6. Check to see that once the game ends a end game screen is shown declaring the correct winner and provides the user with a mini menu screen and a restart button.
  7. Check to see if options are changed in the end game screen and restart is pressed, the changes occur in the new game.
  
# Manual Testing
This section will explain how to test how the program handles certain bugs that may appear while running or attempting to run the program.
When errors occur that prevent the game from starting properly, such as a corrupted text file, a pop-up window will appear with an appropriate error message and the program will terminate. 

  ## Testing Safe Crashes
  1. Testing invalid text file name: navigate to the src folder within demo 3. Once there navigate to resources then gui. Now, in gui change the names of one of the maps to anything you want. Then recompile and run the game and in the menu screen select the map that you renamed. Next, press the start button and check to see if you get a pop-up window appears. When the pop-up window appears press OK and the program should terminate.
  
  2. Testing corrupted text file: navigate to the src folder within demo 3. Once there navigate to resources then gui. Now, in gui open any one of the text files and add or change a characters to an invalid character, i.e, any letter in the alphabet. Then recompile and run the game and once in the menu screen select the corrupted map file. Next, press the start button and watch for a pop-up window. When the pop-up window appears press OK and the program should terminate.
  
  3. Testing the textbased game: navigate to the src folder within demo 3. Once there navigate to resources then textbased. Now, with textbased open either corrupt or change the name of one of the text files in the folder. Then recompile and run the textbased version of the game by typing java drivers.MainTB into the console in the correct directory. Next, in the menu screen select the map that was changed and a message should be printed with a pop-up window and the program should terminate.

# Automated Testing
Automated tests are run using JUnit. To run these automated tests,
  1. Open the correct directory in command prompt by typing cd [filepath to unzipped location]\TankRoyale-master\TankRoyale
        master\Demo 3\src.
  2. Check to ensure the hamcrest core and junit jar files are within the src folder.
  3. Compile the test java files by typing javac -cp .;junit 4.12.jar;hamcrest-core-1.3.jar drivers/*.java handlers/*.java logic/*.java visuals/*.java tests/*.java into the console.
  4. To run the tests type java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore tests.TestSuite into the console.
  5. After the tests are run a message should be printed onto the console confirming all the tests have passed.
