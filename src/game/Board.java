
package game;

import java.util.ArrayList;

import pieces.*;

/**
* <h1>The Board</h1>
* This class makes the board that 
* will be used for the chess game.
* the output on the screen.
*
* @author  Ismaeel Abdulghani and Laurence Bartram
* @version 1.0
* @since   2022-10-13
*/

public class Board {
    public Piece [][] board;
    public int turn;
    /**
     * Initializes a Board object containing a 2D array of Pieces. 
     * @param b The board 
     */



    public Board(Board b){
        board = new Piece[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece curr = b.board[i][j];
                if(curr==null){
                    continue;
                }
                if(curr instanceof Pawn){
                    board[i][j] = new Pawn(curr.getSide(), curr.getRowNum(), curr.getColLet(), this);
                }
                else if(curr instanceof Rook){
                    board[i][j] = new Rook(curr.getSide(), curr.getRowNum(), curr.getColLet(), this);
                }
                else if(curr instanceof Knight){
                    board[i][j] = new Knight(curr.getSide(), curr.getRowNum(), curr.getColLet(), this);
                }
                else if(curr instanceof Bishop){
                    board[i][j] = new Bishop(curr.getSide(), curr.getRowNum(), curr.getColLet(), this);
                }
                else if(curr instanceof Queen){
                    board[i][j] = new Queen(curr.getSide(), curr.getRowNum(), curr.getColLet(), this);
                }
                else if(curr instanceof King){
                    board[i][j] = new King(curr.getSide(), curr.getRowNum(), curr.getColLet(), this);
                }

            }
        }
        
    }
    /**
     * Initializes a board object and 2D array of Pieces.
     */
    public Board(){
        board = new Piece[8][8];
        for(int i=0;i<8;i++){
            board[1][i] = new Pawn(true, 1, i, this); //Adding all the white pawns 
            board[6][i] = new Pawn(false, 6, i, this); //Adding all the black pawns

            if(i == 0 || i == 7){
                board[0][i] = new Rook(true,0,i, this);
                board[7][i] = new Rook(false,7,i, this);
            }

            if(i == 2 || i == 5){
                board[0][i] = new Bishop(true,0,i, this);
                board[7][i] = new Bishop(false,7,i, this);
            }

            if(i == 1 || i == 6){
                board[0][i] = new Knight(true,0,i, this);
                board[7][i] = new Knight(false,7,i, this);
            }
            if(i == 1 || i == 6){
                board[0][i] = new Knight(true,0,i, this);
                board[7][i] = new Knight(false,7,i, this);
            }
            if(i == 4){
                board[0][i] = new King(true,0,i, this);
                board[7][i] = new King(false,7,i, this);
                
            }
            if(i == 3){
                board[0][i] = new Queen(true,0,i, this);
                board[7][i] = new Queen(false,7,i, this);
                
            }
        }
        
    }

    /**
   * This method is used to print the board after
   * each move to the console
   * @return Nothing.
   */
    public void printBoard(){
        System.out.println();
        for(int i=7;i>=0;i--){
            for(int j=0;j<8;j++){
                
                if (board[i][j]==null && (i+j)%2==0){
                    System.out.print("## ");
                }
                else if(board[i][j]==null){
                    System.out.print("   ");
                }
                else{
                    System.out.print(board[i][j].getName());
                }
            }
            System.out.println(i+1);
        }
        System.out.print(" a ");
        System.out.print(" b ");
        System.out.print(" c ");
        System.out.print(" d ");
        System.out.print(" e ");
        System.out.print(" f ");
        System.out.print(" g ");
        System.out.println(" h");
        System.out.println();
    }

    /**
   * This method is used to check whether a
   * specific space is in danger of being attacked.
   * Mainly used for checking if the king is in danger.
   * @param side This tells us whether we are checking for white or black
   * @param space  This tells us the specific space we are looking at
   * @return boolean Whether the space is in danger or not
   */
    public boolean targeted(boolean side, String space){ 
        ArrayList<String> moves = new ArrayList<String>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j]!=null && board[i][j].getSide()!=side){
                    moves.addAll(board[i][j].getMoves());
                }
            }
        }
        for(String e: moves){
            if(e.equals(space)){
                return true;
            }
        }

        return false;
    }

    /**
   * This method is used to check whether 
   * the king is in danger or not.
   * @param side This tells us whether we are checking for white or black
   * @return boolean Whether the king is in danger or not
   */
    public boolean check(boolean side){ 
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j]!=null && board[i][j].getSide()!=side && board[i][j] instanceof King){
                    
                    return targeted(!side, ""+Letter.getChar(j)+(i+1));
                }
            }
        }

        
        return false;
    }

    /**
   * This method is used to check after we confirm
   * the king is in danger, to see if that side can
   * make any more moves or not.
   * @param side This tells us whether we are checking for white or black
   * @return boolean Whether there is a checkmate or not
   */
    public boolean checkmate(boolean side){ 
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j]!=null && board[i][j].getSide()!=side){ 
                    ArrayList<String> moves = board[i][j].getMoves(); //We'll check every possible move the opponent could make and see if they're still in check
                    for(String e: moves){
                        Board copy = new Board(this);
                        copy.turn++;
                        Piece curr = copy.board[i][j];
                        copy.board[Character.getNumericValue(e.charAt(1))-1][Letter.getInt(e.charAt(0))] = curr;
                        copy.board[i][j]=null;
                        curr.setNewCoords(Character.getNumericValue(e.charAt(1))-1, Letter.getInt(e.charAt(0)));

                        if(copy.check(side)==false && copy.check(!side)==false){
                            return false;
                        }

                    }

                    
                    
                }
            }
        }

        
        return true;
    }



    
}
