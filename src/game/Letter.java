package game;

/**
 * <h1> Letter Enums </h1>
 * This class is responsible for handling the translation between the letter enums
 * and the respective integer values.
 * 
 * @author Ismaeel Abdulghani and Laurence Bartram
 * @version 1.0
 * @since   2022-10-13
 */

public class Letter{

    /**
     * Translates letter moves into their respective grid coordinates.
     * Valid letters are as follows:
     * {@link #A}
     * {@link #B}
     * {@link #C}
     * {@link #D}
     * {@link #E}
     * {@link #F}
     * {@link #G}
     * {@link #H}
     */
    public enum Letters{
        
        A('a', 0), B('b', 1), C('c', 2), D('d', 3), E('e', 4), F('f', 5), G('g', 6), H('h', 7);
        private int num;
        private char character;

        Letters(char let, int num){
            this.character = let;
            this.num = num;
        } 

    }

    /**
     * Takes in a letter move and uses enums to return it's respective grid coordinate.
     * @param s The current letter move
     * @return The respective x coordinate.
     */
    public static int getInt(char s){
        Letters letters [] = Letters.values();
        
        for(Letters l: letters){

            if(l.character == (s)){
                return l.num;
            }
        }
        return -1;
        
    }
    /**
     * Takes in an x-coordinate move and uses enums to return it's respective grid coordinate.
     * @param n The current x-coordinate move.
     * @return The respective letter move.
     */
    public static char getChar( int n){
        Letters letters [] = Letters.values();
        
        for(Letters l: letters){

            if(l.num == (n)){
                return l.character;
            }
        }
        return 'Q';
    }
}
    

