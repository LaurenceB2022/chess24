package pieces;

import java.util.ArrayList;
import game.*;

/**
 * <h1> Knight Piece </h1>
 * This class implements the Piece interface to create a Knight Object.
 * It calculates all possible moves for the current Knight object,
 * and the String representation.
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */

public class Knight extends Piece{

    /**
     * Implements the Piece class using the parameters side, rowNum, colLet, and board
     * to create a Knight object.
     * @param side The player of the current turn's board side.
     * @param rowNum The y coordinate of the current location
     * @param colLet The x coordinate of the current location
     * @param board The 2D pieces array board.
     */
    public Knight(boolean side, int rowNum, int colLet, Board board){
        super(side, rowNum, colLet, board);
    }

   
    /**
     * Calculates the string representation of the current piece
     * using the method getSide()
     * @return String representation of current Knight.
     */
    @Override
    public String getName() {
        return (this.getSide() ? "w" : "b") +"N" + " ";

    }

    /**
     * Calculates all possible moves for the current Knight piece. 
     * Utilizes the method checkMovement which is implemented in the Piece interface.
     * @return ArrayList<String> of all possible moves.
     */
    @Override
    public ArrayList<String> getMoves() {
        ArrayList<String> moves = new ArrayList<String>(); //Remember to check for friendly pieces later, but should be good for now
        int row = this.getRowNum()+1;
        int col = this.getColLet();
        if(checkMovement(row, col+2, getSide())){
            moves.add("" + Letter.getChar(col+2)+(row+1));
        }
        if(checkMovement(row-2, col+2, getSide())){
            moves.add("" + Letter.getChar(col+2)+(row-1));
        }
        if(checkMovement(row, col-2, getSide())){
            moves.add("" + Letter.getChar(col-2)+(row+1));
        }
        if(checkMovement(row-2, col-2, getSide())){
            moves.add("" + Letter.getChar(col-2)+(row-1));
        }
        if(checkMovement(row+1, col+1, getSide())){
            moves.add("" + Letter.getChar(col+1)+(row+2));
        }
        if(checkMovement(row-3, col+1, getSide())){
            moves.add("" + Letter.getChar(col+1)+(row-2));
        }
        if(checkMovement(row+1, col-1, getSide())){
            moves.add("" + Letter.getChar(col-1)+(row+2));
        }
        if(checkMovement(row-3, col-1, getSide())){
            moves.add("" + Letter.getChar(col-1)+(row-2));
        }
        
        
        return moves;
    }
    
}
