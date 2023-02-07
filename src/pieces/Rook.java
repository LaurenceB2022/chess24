package pieces;

import java.util.ArrayList;

import game.*;

/**
 * <h1> Rook Piece </h1>
 * This class implements the Piece class to create a Rook object.
 * It calculates all possible moves for the Rook Piece, and returns them.
 * It also returns the String representation of the current Rook Piece.
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */
public class Rook extends Piece{

    /**
     * Initializes a Rook object which implements the Piece interface. 
     * This is done using the parameters boolean side which is the side of the current turn,
     * integer rowNum the current row number, integer colLet the current column number, and 
     * Board board the current 2D array of Pieces.
     * @param side the current turn's side.
     * @param rowNum the row number of this Piece.
     * @param colLet the column number of this Piece
     * @param board the current Board for this game.
     */

    public Rook(boolean side, int rowNum, int colLet, Board board){
        super(side, rowNum, colLet,board);

    }

    



    /**
     * Calculates and returns the String representation of the current Piece.
     * @return String of Piece Rook.
     */
    @Override
    public String getName() {
        return (this.getSide() ? "w" : "b") +"R" + " ";
    }
    /**
     * Calculates and returns an ArrayList<String> of all possible moves for the current
     * Rook Piece. It utilizes the checkMovement method implemented in the Piece interface.
     * @return ArrayList<String> of all possible moves.
     */
    public ArrayList<String> getMoves(){
        ArrayList<String> moves = new ArrayList<String>();
        int row = this.getColLet();
        int col = this.getRowNum();
        int xCoord;
        int yCoord;
        boolean NPiece = false;
        boolean WPiece = false;
        boolean EPiece = false;
        boolean SPiece = false;

        //Max length of movement is 8, minimum length is 1
        for(int distance = 1; distance < board.board.length; distance++){
            //North
            if(!NPiece ){
                xCoord = col;
                yCoord = row + distance;
                
                if(checkMovement(xCoord, yCoord, this.getSide())){ //Adds piece movement if true
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1));
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        NPiece = true;
                    }   
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    NPiece = true;
                }
            }
            //South
            if(!SPiece){
                xCoord = col;
                yCoord = row - distance;
                if(checkMovement(xCoord, yCoord, this.getSide())){ //Adds piece movement if true
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1));
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        SPiece = true;
                    }   
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    SPiece = true;
                }
            }
            //West
            if(!WPiece){
                xCoord = col - distance;
                yCoord = row;
                if(checkMovement(xCoord, yCoord, this.getSide())){ //Adds piece movement if true
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1));
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        WPiece = true;
                    }   
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    WPiece = true;
                }
            }
            //East
            if(!EPiece){
                xCoord = col + distance;
                yCoord = row;
                if(checkMovement(xCoord, yCoord, this.getSide())){ //Adds piece movement if true
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1));
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        EPiece = true;
                    }   
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    EPiece = true;
                }
            }
        }
        
        return moves;
    }
}