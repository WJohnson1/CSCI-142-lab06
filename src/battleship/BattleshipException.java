package battleship;

import java.io.Serializable;

/**
 * Creates a  BattleshipException
 * @author William Johnson
 */
public class BattleshipException extends Exception implements Serializable{

    public static final int UNSET = -1;
    public int row;
    public int column;

    // Complete this constructor so that the row and column
    // are stored in the exception instance.

    public BattleshipException( int row, int column, String message ) {
        super( message + ", row=" + row + ", column=" + column );
        this.row = row;
        this.column = column;
    }

    // A second (overloading) constructor that only takes a
    // message string. It should pass the string up to its superclass
    // and set row and column to UNSET.
    public BattleshipException(String msg){
        super(msg);
        row = UNSET;
        column = UNSET;
    }
}
