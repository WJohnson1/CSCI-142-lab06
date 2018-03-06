package battleship;

import java.io.Serializable;
/**
 * Creates an CellPlayedException
 * @author William Johnson
 */
public class CellPlayedException extends BattleshipException  implements Serializable {
    private static final String ALREADY_HIT = "ALREADY_HIT";


    /**
     * The constructor stores the coordinates where the violation occurred and sets the error message to ALREADY_HIT.
     * @param row the row that the error occurred
     * @param column the column that the error occured
     */
    public CellPlayedException(int row, int column) {

        super(ALREADY_HIT + ", row=" + row + ", column=" + column );

    }


}
