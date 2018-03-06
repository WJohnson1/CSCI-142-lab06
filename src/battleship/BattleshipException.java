package battleship;

public class BattleshipException extends Exception {

    public static final int UNSET = -1;
    public int row;
    public int column;
    // TODO Create public integer fields row and column.
    // Make them so they cannot be changed, and non-static.

    // TODO Complete this constructor so that the row and column
    // are stored in the exception instance.
    //
    public BattleshipException( int row, int column, String message ) {
        super( message + ", row=" + row + ", column=" + column );
    }

    // TODO Add a second (overloading) constructor that only takes a
    // message string. It should pass the string up to its superclass
    // and set row and column to UNSET.
    public BattleshipException(String msg){
        super(msg);
        row = UNSET;
        column = UNSET;
    }
}
