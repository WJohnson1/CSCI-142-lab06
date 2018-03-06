package battleship;

import java.io.Serializable;
/**
 * Creates an OutOfBoundsException
 * @author William Johnson
 */
public class OutOfBoundsException extends Exception  implements Serializable {
    public static final String PAST_EDGE = "PAST_EDGE";

    /**
     * The constructor stores the illegal coordere the violation occured and sets the error message to PAST_EDGE
     * @param row the row that the error occured
     * @param column the column that the error occured
     */
    public OutOfBoundsException(int row, int column){
        super( PAST_EDGE + ", row=" + row + ", column=" + column );

    }
}
