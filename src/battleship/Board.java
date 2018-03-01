package battleship;

import java.io.PrintStream;
import java.io.Serializable;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board
 * can be asked if the game is over.
 * The class is Serializable so that its instance can
 * be saved to a file in binary form using an
 * {@link java.io.ObjectOutputStream} and restored
 * with an {@link java.io.ObjectInputStream}.
 * Because the object holds references to all other
 * objects in the system, no other objects need to
 * be separately saved.
 */
public class Board implements Serializable {
    private int height;
    private int width;
    public Board(int height, int width){
        this.height = height;
        this.width = width;
    }
    /**
     * Fetch the Cell object at the given location.
     * @param row row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    public Cell getCell(int row, int column) throws OutOfBoundsException{
        return new Cell(0,0);
    }

    public int getHeight() {
        return height;
    }

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
    public void addShip(Ship ship){

    }

    @Override
    public String toString() {
        return super.toString();
    }
    public void display(PrintStream p){

    }
    public void fullDisplay(PrintStream p){

    }
    public boolean allSunk(){
        return false;
    }
}
