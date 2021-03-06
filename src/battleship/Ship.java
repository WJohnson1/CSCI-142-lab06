package battleship;

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 * @author William Johnson
 */
public class Ship implements Serializable {

    public static final String SUNK_MESSAGE = "A battleship has been sunk!";

    public Board getBoard() {
        return board;
    }

    public int getuRow() {
        return uRow;
    }

    public int getlCol() {
        return lCol;
    }

    public Orientation getOrt() {
        return ort;
    }

    /**
     * Orientation is a property of a ship.
     * The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration
     * files.
     *
     */
    public enum Orientation {
        HORIZONTAL( 0, 1 ), VERTICAL( 1, 0 );

        /**
         * Use it to loop through all of the cell locations a ship
         * is on, based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number changes
         *               in this direction
         */
        Orientation( int rDelta, int cDelta ) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
        }
    }
    private Board board;
    private int uRow;
    private int lCol;
    private Orientation ort;
    private int length;
    private int hit;
    /**
     * Initialize this new ship's state. Tell the Board object
     * and each involved Cell object about the existence of this
     * ship by trying to put the ship at each applicable Cell.
     * @param board holds a collection of ships
     * @param uRow the uppermost row that the ship is on
     * @param lCol the leftmost column that the ship is on
     * @param ort the ship's orientation
     * @param length how many cells the ship is on
     * @throws OverlapException if this ship would overlap another one
     *              that already exists
     * @throws OutOfBoundsException if this ship would extend beyond
     *              the board
     */
    public Ship(Board board, int uRow, int lCol, Orientation ort, int length) throws OverlapException, OutOfBoundsException{
        if (ort == Orientation.HORIZONTAL){
            if (lCol + length>board.getWidth()){
                throw new OutOfBoundsException(uRow, lCol +length);
            }
            else{
                boolean a = false;
                int o = 0;
                int p = 0;
                for (int i = 0; i<length;i++){
                    if (board.getCell(uRow,lCol+i).displayChar() == 'S' || board.getCell(uRow,lCol+i).displayChar() == '☐' ||
                            board.getCell(uRow,lCol+i).displayChar() == '.'){
                        a = true;
                        o = uRow;
                        p = lCol + i;
                    }
                }
                if (a){
                    throw new OverlapException(o,p);
                }
                else{
                    this.board = board;
                    this.uRow = uRow;
                    this.lCol = lCol;
                    this.ort = ort;
                    this.length = length;
                    this.hit = 0;
                }
            }
        }
        else{
            if (uRow + length>board.getHeight()){
                throw new OutOfBoundsException(uRow + length, lCol);
            }
            else{
                boolean a = false;
                int o = 0;
                int p = 0;
                for (int i = 0; i<length;i++){
                    if (board.getCell(uRow+i,lCol).displayChar() == 'S' || board.getCell(uRow+i,lCol).displayChar() == '☐' ||
                            board.getCell(uRow+i,lCol).displayChar() == '.'){
                        a = true;
                        o = uRow+i;
                        p = lCol;
                    }
                }
                if (a){
                    throw new OverlapException(o,p);
                }
                else{
                    this.board = board;
                    this.uRow = uRow;
                    this.lCol = lCol;
                    this.ort = ort;
                    this.length = length;
                    this.hit = 0;
                }
            }
        }

    }

    /**
     * Increases the hit value of the ship
     */
    public void hit() {
        this.hit += 1;
        isSunk();
    }

    /**
     * Returns true if the ship has sunk else returns false if it hasn't
     * @return true or false
     */
    public boolean isSunk(){
        if (this.hit == this.length){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the length of the ship
     * @return the length of the ship
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the string representation of the ship
     * @return the string representation of the ship
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
