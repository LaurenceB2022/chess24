package pieces;

import java.util.ArrayList;

import game.*;
/**
 * <h1> The Bishop Piece </h1>
 * This class implements Piece to create a Bishop.
 * It also calculates all possible moves for a Bishop piece, and can return it's name.
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */

public class Bishop extends Piece{


    /**
     * Takes in side, rowNum, colLet, and board parameters to create a Piece of subtype bishop.
     * @param side The player of the current turn's board side.
     * @param rowNum The y coordinate of the current location
     * @param colLet The x coordinate of the current location
     * @param board The 2D pieces array board.
     */
    public Bishop(boolean side, int rowNum, int colLet, Board board){
        super(side, rowNum, colLet, board);

    }


    /** 
     * Calculates the string representation of the current Piece.
     * @return String of name.
     * */ 
    @Override
    public String getName(){
        return (this.getSide() ? "w" : "b") +"B" + " ";
    }

    /**
     * This method implements the basic checkMovement class to see if a legal move is being made at each
     * possible spot. Returns an array of all possible moves for the current Bishop piece. 
     * @return ArrayList<String> of all possible moves.
     */
    public ArrayList<String> getMoves(){
        ArrayList<String> moves = new ArrayList<String>();
        int row = this.getColLet();
        int col = this.getRowNum();
        int xCoord;
        int yCoord;
        //Checks if there is a piece in the way or if the max movement has been exceeded
        boolean NEPiece = false;
        boolean NWPiece = false;
        boolean SWPiece = false;
        boolean SEPiece = false;
        
        //Max length of movement is 8, minimum length is 1
        for(int distance = 1; distance < board.board.length; distance++){
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