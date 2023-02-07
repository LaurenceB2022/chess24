package game;

import java.util.ArrayList;
import java.util.Scanner;

import pieces.*;

/**
* <h1>The Game</h1>
* This class that is mainly used
* to handle game logic. This includes keeping track of the current turn, and each playeer's moves.
*
* @author  Ismaeel Abdulghani and Laurence Bartram
* @version 1.0
* @since   2022-10-13
*/

public class Game {

    
    private boolean whiteTurn;
    public Board board;
    Scanner sc = new Scanner(System.in);

    

    /*
     * Initializes a game object to run the board of chess, and sets the current turn using a boolean.
     */
    public Game(){
        board = new Board();
        whiteTurn = true;
    }

    /**
   * This method is where the majority
   * of the game takes place. It calls
   * other methods to allow the game to
   * run smoothly.
   * @return Nothing.
   */
    public void startGame(){
        
        boolean play = true;
        board.turn = 0;
        
        while(play){
            board.turn++;
            board.printBoard();
            prompt();
            
            String move = sc.nextLine();

            //Checks if the players are resigning or drawing the game
            if(checkResignDraw(move)){
                System.exit(0);
            }

            while (checkMove(move) == false){
                System.out.println("Illegal move, try again");
                prompt();
                move = sc.nextLine();
            }

            
            makeMove(move);

            if(board.check(whiteTurn)){
                if(board.checkmate(whiteTurn)){
                    System.out.println("Checkmate");
                    if(whiteTurn == true){
                        System.out.println("White wins");
                    }
                    else{
                        System.out.println("Black wins");
                    }
                    return;
                }
                else{
                    System.out.println("Check");
                }
                
            }


            whiteTurn = !whiteTurn;
        }
        sc.close();
        

    }

    /**
   * This method is used to check whether a
   * specific space is in danger of being attacked.
   * Mainly used for checking if the king is in danger.
   * @param move This is the string given by the player
   * @return boolean whether the player has resigned or drawed (both cases are handled in the method)
   */
    public boolean checkResignDraw(String move){
        //Checks if the player is resigning this move
        if(move.equals("resign")){
            if(whiteTurn == true){
                System.out.println("Black wins");
            }
            else{
                System.out.println("White wins");
            }
            return true;
        } //Checks if player is drawing this move
        else if(move.contains("draw?")){
            if(checkMove(move.substring(0,5))){
                makeMove(move.substring(0,5));
            }
            board.printBoard();
            whiteTurn = !whiteTurn;
            prompt();
            String draw = sc.nextLine();
            System.out.println("draw");
            return true;
        }
        return false;
    }


     /**
   * This method is used to check whether
   * the string given by the player is for
   * a valid more or not. Implements the {@link Letter.Letters} enums.
   * @see Letter.Letters
   * @param move This is the string given by the player
   * @return boolean whether the move is valid or not.
   */
    public boolean checkMove(String move){
        if(checkResignDraw(move)){
            System.exit(0);
        }

        move = move.trim();
        //Letters letter1;
        //Letters letter2;

        if(move.charAt(2)!=' '){
            return false;
        }

        int c1 = Letter.getInt(move.charAt(0));
        int r1 = Character.getNumericValue(move.charAt(1))-1;
        

        //Checks if the piece exists, and the coordinates are within the board's range
        if( (c1 < 0 || c1 >7 || r1<0 ||r1 > 7 ) || board.board[r1][c1]==null){
            return false;
        }
        

        int c2 = Letter.getInt(move.charAt(3));
        int r2 = Character.getNumericValue(move.charAt(4))-1;


        //Checks if the move is within the board
        if(c2 < 0 || c2 >7 || r2<0 ||r2 > 7 ){
            return false;
        }
        
        //Sets the current piece you want to move
        Piece curr = board.board[r1][c1];

        //Checks if the piece chosen belongs to the same side
        if(curr.getSide() != whiteTurn){
            return false;
        }

        
        Piece opp = board.board[r2][c2];

        //Checks to see if there exists a piece at the destination, and if it's of the same side
        if(opp!=null && opp.getSide()==curr.getSide()){
            return false;
        }

        

        ArrayList<String> possibilities = curr.getMoves();

        Board copy = new Board(board); //Checking if it puts us in check
        Piece copyCurr = copy.board[r1][c1];
        copy.turn++;

        copy.board[r1][c1]=null;
        copy.board[r2][c2]=copyCurr;
        copyCurr.setNewCoords(r2, c2);

        if(copy.check(!whiteTurn)){
            return false;
         }

        for(String e:possibilities){
            if (e.equals(move.substring(3,5))){
        
                return true;
            }
        }

    if(curr instanceof King){
        possibilities=((King) curr).getCastlingMoves();
        for(String e:possibilities){
           
                System.out.println(e);
            
        }
        for(String e:possibilities){
            if (e.equals(move.substring(3,5))){
                return true;
            }
        }
    }
    
        return false;

    }

    /**
   * This method is used to make the actual move 
   * the player intended
   * @param move This is the string given by the player
   * @return Nothing.
   */
    public void makeMove(String move){
        move = move.trim();

    

        int c1 = ((char) move.charAt(0) ) - 'a';
        int r1 = ((int) (move.charAt(1) -'0')) - 1;


        int c2 = ((char) move.charAt(3) ) - 'a';
        int r2 = ((int)(move.charAt(4)-'0')) - 1;

        Piece curr = board.board[r1][c1];
        Piece dest = board.board[r2][c2];

        if(curr instanceof Pawn){
            if(Math.abs(r1-r2)==2){
                ((Pawn) curr).enpassant = true;
            }
            else{
                ((Pawn) curr).enpassant = false;
            }
            
        }

        if(curr instanceof Pawn && c1!=c2 && board.board[r2][c2]==null){
            if(whiteTurn){
                board.board[r2-1][c2]=null;
            }
            else{
                board.board[r2+1][c2]=null;
            }
            
        }

        if(curr instanceof Pawn && ((curr.getSide() && r2==7) || (curr.getSide()==false && r2==0))){ //If pawn reached the end, we'll promote it
            if(move.length() >= 7 && move.charAt(6)=='N'){
                curr = new Knight(curr.getSide(),r1,c1,board);
            }
            else if(move.length()>=7 && move.charAt(6)=='R'){
                curr = new Rook(curr.getSide(),r1,c1,board);
            }
            else if(move.length()>=7 && move.charAt(6)=='B'){
                curr = new Bishop(curr.getSide(),r1,c1,board);
            }
            else{
                curr = new Queen(curr.getSide(),r1,c1,board);
            }

        }

        if(curr instanceof King && Math.abs(c1 - c2) == 2){ //If the current King is performing a valid castling move
            if(c1 - c2 == 2){ //Castling to the left
                Piece rookLeft = board.board[r1][0];
                curr.setNewCoords(r2, c2);
                rookLeft.setNewCoords(r2, c2+1); //Rook moves right over the king 1 space
                board.board[r1][c1] = null;
                board.board[r1][0]=null;
                board.board[r2][c2] = curr;
                board.board[r2][c2+1] = rookLeft;
                curr.setMove(board.turn);
                rookLeft.setMove(board.turn);

            }
            else if(c1 - c2 == -2){ //Castling to the right
                Piece rookRight = board.board[r1][7];
                curr.setNewCoords(r2, c2);
                rookRight.setNewCoords(r2, c2-1); //Rook moves left over the king 1 space
                board.board[r1][c1] = null;
                board.board[r1][7]=null;
                board.board[r2][c2] = curr;
                board.board[r2][c2-1] = rookRight;
                curr.setMove(board.turn);
                rookRight.setMove(board.turn);
            }

            return;
        }
        


        board.board[r1][c1]=null;
        board.board[r2][c2]=curr;
        curr.setNewCoords(r2, c2);
        curr.setMove(board.turn);

    }


     /**
   * Prints the prompt for the next
   * player to make their move.
   * @return Nothing.
   */
    public void prompt(){
        if(whiteTurn){
            System.out.print("White's move: ");
        }
        else{
            System.out.print("Black's move: ");
        }
        
    }

    

 
}
