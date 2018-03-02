package battleship;

public class CellPlayedException extends BattleshipException {
    private static String ALREADY_HIT;

    public CellPlayedException(int row, int column, String message) {
        super(row, column, message);
    }
    /**
    public CellPlayedException(int row, int column){
        this.row = row;
        this.column = column;
        super.UNSET = ALREADY_HIT;
    }
     */

}
