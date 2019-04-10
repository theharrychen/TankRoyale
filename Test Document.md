# Tank Royale Testing Instructions
Before running any JUnit tests please go through these simple in-game tests to ensure the game was downloaded properly and the correct version is running. 

# In-Game Testing
Simple tests to check to see if all the game mechanics are working as intended.

  ## Text Based
  1. When the game is run ensure that a menu screen is the first to appear.
  2. Once a map is selected check to see if the map matches the selected map.
  3. Once in game ensure each console command is working as intended and walls block movement and bullets.
  4. Check to see once a player is hit with a bullet the game ends and a end screen is shown.
  5. Check to see that the end game screen displays the correct winner and prompts the user to restart or end the game.
  6. Check to see if restart is chosen the game restarts with the menu screen, or that the program ends if restart is not chosen.
  
  ## GUI
  1. When running the game ensure the first screen is the main menu screen.
  2. Check to see if the selection buttons are working and the game only starts once the start button is pressed.
  3. Check all the controls to ensure each one is working as intended.
  4. Check to see if a player is hit with a bullet the point is awarded to the other player.
  5. Check to see that once the selected point limit is reached the game ends.
  6. Check to see that once the game ends a end game screen is shown declaring the correct winner and prompts the user to restart or return      to the main menu.
  7. Check to see if the correct action occurs according to the selection.
  
# Manual Testing
This section will explain how to test how the program handles certain bugs that may appear while running or attempting to run the program.
When errors occur, such as a corrupted text file, a pop-up window will appear with the appropriate error message and the program will terminate. 

# Automated Testing
Automated tests are run using JUnit. To run these automated tests,
  1. Open the correct directory in command prompt (See readme file)
