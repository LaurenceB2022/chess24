package pieces;

import java.util.ArrayList;

import game.*;

/**
 * <h1> Pawn Piece </h1>
 * This class implements the Piece interface to create a Pawn object.
 * It calculates all possible movements for the current Pawn.
 * It calculates the String representation of the current Pawn.
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */
public class Pawn extends Piece {
    public boolean enpassant;

    /**
     * Creates a Pawn object by using the parameters side, rowNum, colLet, and board to
     * implement the Piece interface.
     * @param side The player of the current turn's board side.
     * @param rowNum The y coordinate of the current location
     * @param colLet The x coordinate of the current location
     * @param board The 2D pieces array board.
     */
    public Pawn(boolean side, int rowNum, int colLet, Board board){
        super(side, rowNum, colLet, board);

    }

    
    /**
     * Calculates the String representation of the current Pawn Piece.
     * @return String of current piece.
     */
    @Override
    public String getName() {
       
        return (this.getSide() ? "w" : "b") +"p" + " ";
    }

    
    
    /**
     * Calculates all possible moves for the current Pawn Piece. 
     * @return ArrayList<String> of all possible moves.
     */
    public ArrayList<String> getMoves(){
        ArrayList<String> moves = new ArrayList<String>(); //In the arraylist, we have to convert to actual coordinates, so add 1 to each row index
        int row = this.getRowNum();
        int col = this.getColLet();

        if(this.getSide()==true){
            if(row==1 && board.board[3][col]==null){
                moves.add(""+Letter.getChar(col)+4); //Move twice on first
            }
            if(row<7 && board.board[row+1][col]==null){ //Moves forward once
                moves.add(""+Letter.getChar(col)+(row+2));
            }
            if(col>0 && row<7 && (board.board[row+1][col-1]!=null || (board.board[row][col-1]!=null && board.board[row][col-1] instanceof Pawn && ((Pawn)board.board[row][col-1]).enpassant==true && board.board[row][col-1].getMoved()==board.turn-1))){ //Kills diagonal right
                moves.add(""+Letter.getChar(col-1)+(row+2));
            }
            if(col<7 && row<7 && (board.board[row+1][col+1]!=null ||(board.board[row][col+1]!=null && board.board[row][col+1] instanceof Pawn && ((Pawn)board.board[row][col+1]).enpassant==true && board.board[row][col+1].getMoved()==board.turn-1))){ //Kills diagonal left
                moves.add(""+Letter.getChar(col+1)+(row+2));
            }

        }
        else{
            if(row==6 && board.board[4][col]==null){
                moves.add(""+Letter.getChar(col)+5); //Move twice on first
            }
            if(row>0 && board.board[row-1][col]==null){ //Moves forward once
                moves.add(""+Letter.getChar(col)+(row));
            }
            if(col<7 && row>0 && (board.board[row-1][col+1]!=null || (board.board[row][col+1]!=null && board.board[row][col+1] instanceof Pawn && ((Pawn)board.board[row][col+1]).enpassant==true && board.board[row][col+1].getMoved()==board.turn-1))){ //Kills diagonal right
                moves.add(""+Letter.getChar(col+1)+(row));
            }
            if(col>0 && row>0 && (board.board[row-1][col-1]!=null || (board.board[row][col-1]!=null && board.board[row][col-1] instanceof Pawn && ((Pawn)board.board[row][col-1]).enpassant==true && board.board[row][col-1].getMoved()==board.turn-1))){ //Kills diagonal left
                moves.add(""+Letter.getChar(col-1)+(row));
            }

        }

        
            
        return moves;

    }


    
    
}
