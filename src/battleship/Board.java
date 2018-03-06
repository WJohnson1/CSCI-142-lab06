package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board
 * can be asked if the game is over.
 * @author William Johnson
 */
public class Board implements Serializable {
    private int height;
    private int width;
    private Cell[][] cells;
    private ArrayList<Ship> ships;

    /**
     * Creates the board with a certain height and width
     * @param height the height of the board
     * @param width the width of the board
     */
    public Board(int height, int width){
        this.height = height;
        this.width = width;
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i<height;i++){
            for (int j = 0; j<width;j++){
                cells[i][j] = new Cell(i,j);
            }
        }
        this.cells = cells;
        this.ships = new ArrayList<Ship>();
    }
    /**
     * Fetch the Cell object at the given location.
     * @param row row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    public Cell getCell(int row, int column) throws OutOfBoundsException{
        if (row >= getHeight() || row<0 || column>=getWidth() || column<0){
            throw new OutOfBoundsException(row,column);
        }
        else{
            return cells[row][column];
        }
    }

    /**
     * Returns the height of the board
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the board
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }
    /**
     * Add a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     * @see Cell#putShip(Ship)
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     *    that are involved.
     */
    public void addShip(Ship ship) throws OverlapException {

        this.ships.add(ship);
        if (ship.getOrt() == Ship.Orientation.HORIZONTAL){
            for (int i = 0; i<ship.getLength();i++){
                this.cells[ship.getuRow()][ship.getlCol()+i].putShip(ship);
            }
        }
        else{
            for (int i = 0; i<ship.getLength();i++){
                this.cells[ship.getuRow()+i][ship.getlCol()].putShip(ship);
            }
        }
    }

    /**
     * Creates a string representation of the board
     * @return the string representation of the board
     */
    @Override
    public String toString() {
        String s = "  ";
        for (int i = 0; i<this.getHeight()+1;i++){
            if (i != 0 && i<this.getHeight()+1){
                s = s + String.valueOf(i-1) + " ";
            }
            for (int j = 0; j<this.getWidth();j++) {
                if (i == 0) {
                    s = s + j + " ";
                }
                else {
                    s = s + this.cells[i-1][j].displayChar() + " ";
                }
            }
            s = s + "\n";
        }
        return s;
    }

    /**
     * Display the board in character form to the user
     * @param out the output stream to which the display will be sent
     */
    public void display(PrintStream out){
        out.print("  ");
        for (int i = 0; i<this.getHeight()+1;i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (i == 0) {
                    out.print(j + " ");
                } else {
                    out.print(this.cells[i - 1][j].displayHitStatus() + " ");
                }
            }
            out.println();
            if (i < this.getHeight()) {
                out.print(i + " ");
            }
        }
        out.println();
    }

    /**
     * Returns the cells of the board
     * @return the cells of the board
     * */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * The "cheating" form of the display because the user can see where the unsunk parts of the ships are
     * @param out the output stream to which the display should be sent
     */
    public void fullDisplay(PrintStream out){
        out.print("  ");
        for (int i = 0; i<this.getHeight()+1;i++){
            for (int j = 0; j<this.getWidth();j++){
                if (i == 0) {
                    out.print(j + " ");
                }
                else{
                    out.print(this.cells[i-1][j].displayChar() + " ");
                }
            }
            out.println();
            if (i < this.getHeight()) {
                out.print(i + " ");
            }
        }
        out.println();
    }

    /**
     * Returns true if all the ships on the board have been sunk, else returns false
     * @return true or false
     */
    public boolean allSunk(){
        boolean a = true;
        for (Ship s : this.ships) {
            if (s.isSunk() == false) {
                a = false;
            }
        }
        return a;
    }

    /**
     * Returns an arraylist containing all the ships on the board
     * @return an arrayList containing all the ships on the board
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

}
