package battleship;

import java.io.Serializable;

/**
 * Creates an OverlapException
 * @author William Johnson
 */
public class OverlapException extends Exception implements Serializable {
    public static final String OVERLAP = "OVERLAP";

    /**
     * The constructor stores the coodinates of intersection and sets the error to OVERLAP
     * @param row the row that the error occured
     * @param column the column that the error occured
     */
    public OverlapException(int row, int column){
        super( OVERLAP + ", row=" + row + ", column=" + column );
    }
}
