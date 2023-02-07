package pieces;

import java.util.ArrayList;

import game.Board;
import game.Letter;
/**
 * <h1> Queen Piece </h1>
 * This class initializes and implements a Queen piece. 
 * It calculates all possible moves for the Queen piece, 
 * and returns the String representation of the current piece.
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */

public class Queen extends Piece{

    /**
     * Implements the Piece method to create a Queen piece using the arguments
     * boolean side which is the current turn's side, int rowNum which the the current row number
     * integer colLet which is the current column number, and Board board which is a
     * 2D array of Piece objects.
     * @param side the current turn's side.
     * @param rowNum the row number of the Piece.
     * @param colLet the column number of the Piece.
     * @param board the current board of Piece objects.
     */
    public Queen(boolean side, int rowNum, int colLet, Board board) {
        super(side, rowNum, colLet, board);
        
    }

    /**
     * Calculates and returns the String representation of the current piece.
     * @return String of current piece.
     */
    @Override
    public String getName() {
        return (this.getSide() ? "w" : "b") +"Q" + " ";
    }

    /**
     * Calculates and returns all possible moves for the current Queen piece. 
     * This utilizes the basic checkMovement method from the Piece class.
     * @return Array<String> of all possible moves.
     */
    @Override
    public ArrayList<String> getMoves() {

        ArrayList<String> moves = new ArrayList<String>();
        int row = this.getColLet();
        int col = this.getRowNum();
        int xCoord;
        int yCoord;
        boolean NPiece = false;
        boolean WPiece = false;
        boolean EPiece = false;
        boolean SPiece = false;
        boolean NEPiece = false;
        boolean NWPiece = false;
        boolean SWPiece = false;
        boolean SEPiece = false;

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

            //North East
            if(!NEPiece ){
                xCoord = col + distance;
                yCoord = row + distance;

                if(checkMovement(xCoord, yCoord, this.getSide())){ //Adds piece movement if true
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1));
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        NEPiece = true;
                    }   
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    NEPiece = true;
                }
            }
            //North West
            if(!NWPiece){
                xCoord = col - distance;
                yCoord = row + distance;
                if(checkMovement(xCoord, yCoord, this.getSide())){
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1)); 
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        NWPiece = true;
                    }
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    NWPiece = true;
                }
            }
            //South West
            if(!SWPiece){
                xCoord = col - distance;
                yCoord = row - distance;
                if(checkMovement(xCoord, yCoord, this.getSide()) ){
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1)); 
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        SWPiece = true;
                    }
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    SWPiece = true;
                }
            }
            //South East
            if(!SEPiece){
                xCoord = col + distance;
                yCoord = row - distance;
                if (checkMovement(xCoord, yCoord, this.getSide()) ){
                    moves.add("" + Letter.getChar(yCoord) + (xCoord+1)); 
                    if(board.board[xCoord][yCoord]!=null && board.board[xCoord][yCoord].getSide() != this.getSide()){ //Opposite side piece is in the way, no more moves possible in this direction
                        SEPiece = true;
                    }
                }
                else{ //Max movement has been exceeded or same side piece is in the way
                    SEPiece = true;
                }
            }
        }
        
        return moves;
        


    }
    
}
