package pieces;

import java.util.ArrayList;

import game.Board;
import game.Letter;

/**
 * <h1> King Piece </h1>
 * This class implements the Piece interface to create a King. 
 * It calculates all possible moves for the current King, and returns it's string
 * representation.
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */

public class King extends Piece {

    /**
     * Takes in parameters board side, the current row number, the current column number,
     * and the current Board to create a King object out of the piece interface.
     * @param side The player of the current turn's board side.
     * @param rowNum The y coordinate of the current location
     * @param colLet The x coordinate of the current location
     * @param board The 2D pieces array board.
     */
    public King(boolean side, int rowNum, int colLet, Board board){
        super(side, rowNum, colLet, board);
    }

   
    /**
     * Calculates the string representation of the current King Piece, and returns it.
     * @return String representation.
     */
    @Override
    public String getName() {
        return (this.getSide() ? "w" : "b") +"K" + " ";

    }

    /**
     * Takes in parameters of the current piece, and the column and row of the target destination.
     * It checks if a castling option is possible given the state of the King, Rooks, and possible
     * pieces in between the 2.
     * @param curr
     * @param col2
     * @param row2
     * @return boolean if casting possible.
     */
    public boolean checkCastling(Piece curr, int col2, int row2){
        if(board.check(!this.getSide())){
            return false;
        }
        
        //Checks if the king has moved yet and that it is in the same row as the destination. Checks if it is a legal distance for castling
        if( ( (curr.getMoved() == 0 )) && (curr.getRowNum() == row2) && (Math.abs(curr.getColLet() - col2) == 2) ){
                String space;
                boolean currentSide = curr.getSide();
                int x_current = curr.getColLet();
                int y_current = curr.getRowNum();
                Piece rook;
            
                
                //first piece is to the right of the target space
                if((x_current - col2) > 0){
                    rook = board.board[y_current][0];
                    //Rook does not exist in it's designated space, or it has moved before
                    if(rook ==null || rook.getMoved()>0 || !(rook instanceof Rook)){
                        return false;
                    }
                    //Checks to see if there are any pieces in the way
                    for(int i = x_current-1 ; i >= col2; i--){
                        //Checks to see if a piece is blocking the castling 
                        if(board.board[y_current][i] != null){
                            return false;
                        }
                        space = ""+Letter.getChar(i) + (y_current+1);
                        //Ensures that the king doesn't pass through check
                        if(board.targeted(curr.getSide(), space)){
                            return false;
                        }
                        
                    }
                }
                else if ((x_current - col2) < 0 ){ //First piece is to the left of the destination
                    rook = board.board[y_current][7];
                    //Rook does not exist in it's designated spot, or it has moved before
                    if(rook.getMoved()>0 || !(rook instanceof Rook)){
                        return false;
                    }
                    //Checks to see if there are any pieces in the way
                    for(int i = x_current+1 ; i <= col2; i++){

                        //Checks to see if a piece is blocking the castling 
                        if(board.board[y_current][i] != null){
                            return false;
                        }
                        space =""+Letter.getChar(i) + (y_current+1);
                        //Ensures that the king doesn't pass through check
                        if(board.targeted(curr.getSide(), space)){
                            return false;
                        }
                    }
                }
                else{ //King space and destination are equal, invalid move
                    return false;
                }   
                //Passed all of the checks, returns true
                return true; 
        }
        return false;
    }

    /**
     * Calculates all possible castling moves by calling the checkCastling method twice, 
     * once for each rook to castle with.
     * @return ArrayList<String> of all castling moves.
     */
    public ArrayList<String> getCastlingMoves(){
        //Checks for possible castling moves
        int row = this.getRowNum();
        int col = this.getColLet();
        Piece current = board.board[row][col];
        ArrayList<String> moves = new ArrayList<String>();
        if(checkCastling(current, col + 2, row)){
            moves.add("" + Letter.getChar(col + 2)+(row+1));
        }
        if(checkCastling(current, col - 2, row)){
            moves.add("" + Letter.getChar(col - 2) + (row+1));
        }
        return moves;
    }

    /**
     * Calculates all possible moves for a King piece by calling the implemented 
     * checkMovement(row, col, getSide()) method.
     * @return ArrayList<String> of all possible moves (excluding castling)
     */
    @Override
    public ArrayList<String> getMoves() {
        ArrayList<String> moves = new ArrayList<String>(); //Remember to check for friendly pieces later, but should be good for now
        int row = this.getRowNum()+1;
        int col = this.getColLet();
        
        if(checkMovement(row, col, getSide())){
            moves.add("" + Letter.getChar(col)+(row+1));
        }
        if(checkMovement(row-2, col, getSide())){
            moves.add("" + Letter.getChar(col)+(row-1));
        }


        if(checkMovement(row-1, col-1, getSide())){
            moves.add("" + Letter.getChar(col-1)+(row));
        }
        
        if(checkMovement(row, col-1, getSide())){
            moves.add("" + Letter.getChar(col-1)+(row+1));
        }
        
        if(checkMovement(row-2, col-1, getSide())){
            moves.add("" + Letter.getChar(col-1)+(row-1));
        }


        if(checkMovement(row-2, col+1, getSide())){
            moves.add("" + Letter.getChar(col+1)+(row-1));
        }
        if(checkMovement(row-1, col+1, getSide())){
            moves.add("" + Letter.getChar(col+1)+(row));
        }
        if(checkMovement(row, col+1, getSide())){
            moves.add("" + Letter.getChar(col+1)+(row+1));
        }

        
        
        
        
        
         
        
        return moves;
        
    }
    
}
