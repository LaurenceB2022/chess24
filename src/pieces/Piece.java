package pieces;


import java.util.ArrayList;

import game.*;

/**
 * <h1> Piece Interface </h1>
 * This class outlines a Piece interface, which will be implemented by the different
 * types of Pieces. The getter / setter / Piece object are defined, while the
 * getName and getMoves methods are abstract. Basic method checkMovement is defined. 
 * 
 * @author  Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */

public abstract class Piece{
    private int rowNum;
    private int colLet;
    public Board board;
    private boolean side;
    private int turnMoved;

    /**
     * Implements the parameters side, rowNum, colLet, and board to create a standard chess piece.
     * @param side The player of the curren turn's board side.
     * @param rowNum The y coordinate of the current location
     * @param colLet The x coordinate of the current location
     * @param board The 2D pieces array board.
     */
    public Piece(boolean side, int rowNum, int colLet, Board board){
        this.setSide(side);
        this.setNewCoords(rowNum, colLet);
        this.board = board;
        this.turnMoved = 0;

    }

    /**
     * Finds and returns the row number of the current Piece.
     * @return int rowNum
     */
    public int getRowNum(){
        return rowNum;
    }

    /**
     * Find and returns the column number of the current Piece.
     * @return int colNum
     */
    public int getColLet(){
        return colLet;
    }

    /**
     * Finds and returns the side of the current Piece.
     * @return boolean side
     */
    public boolean getSide(){
        return this.side;
    }
    
    /**
     * Returns the turn number that this piece first moved.
     * @return int turnMoved.
     */
    public int getMoved(){
        return this.turnMoved;
    }

    /**
     * Sets the move that the piece moved.
     * @param a turn moved
     */
    public void setMove(int a){
        this.turnMoved = a;
    }
    /**
     * Sets the current side of the piece.
     * @param side current turn side.
     */
    public void setSide(boolean side){
        this.side = side;
    }

    /**
     * Sets the new integer coordinates of the Piece
     * @param rowNum the row number
     * @param colLet the column number
     */
    public void setNewCoords(int rowNum, int colLet){
        this.rowNum = rowNum;
        this.colLet = colLet;
    }

    /**
     * Performs a basic movement check on the piece to see if the destination is within
     * board parameters, and doesn't collide with a piece on the same side.
     * @param xCoord the column coordinate of the destination
     * @param yCoord the row coordinate of the destination
     * @param side the current turn's side.
     * @return if the movement is valid.
     */
    public boolean checkMovement(int xCoord, int yCoord, boolean side){
        //Checks if the move is within board parameters
        if(xCoord >= 0 && xCoord < board.board.length && yCoord >= 0 && yCoord < board.board.length){
            //Checks if piece of the same color is in the way
            if(board.board[xCoord][yCoord]==null || board.board[xCoord][yCoord].getSide() != side){
                
                
                    return true;
                
            }
            else{
                return false;
            }
            
        }
        return false;
    }

    
    /**
     * Name getter method to be implemented by subclasses.
     * @return String of the current Piece's name.
     */
    public abstract String getName();

    /**
     * Gets all possible moves for the current Piece, to be implemented by the subclasses.
     * @return ArrayList<String> of all possible moves.
     */
    public abstract ArrayList<String> getMoves();

    

    



}