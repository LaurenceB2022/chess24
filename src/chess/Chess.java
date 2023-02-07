package chess;

import game.Game;

/**
* <h1>The Main Chess Class</h1>
* This is the main class that
* will run the game.
*
* @author  Ismaeel Abdulghani and Laurence Bartram
* @version 1.0
* @since   2022-10-13
*/

public class Chess {

    /**
   * This is the main method which will then
   * run the chess game.
   * @param args Unused.
   * @return Nothing.
   */
    public static void main(String[]args){
        Game game = new Game();
        game.startGame();
        
    }
    
}
