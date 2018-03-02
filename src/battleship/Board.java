package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

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
    private Cell[][] cells;
    private ArrayList<Ship> ships;
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
            throw new OutOfBoundsException();
        }
        else{
            return cells[row][column];
        }
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
        this.ships.add(ship);
    }

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
    public void display(PrintStream out){
        for (int i = 0; i<this.getHeight();i++){
            for (int j = 0; j<this.getWidth();j++) {
                if (i == 0) {
                    out.print(j + " ");
                } else {
                    out.print(this.cells[i][j].displayHitStatus() + "  ");
                }
            }
            out.println();
            out.print(i + " ");
        }
    }
    public void fullDisplay(PrintStream out){
        for (int i = 0; i<this.getHeight();i++){
            for (int j = 0; j<this.getWidth();j++){
                if (i == 0) {
                    out.print(j + " ");
                }
                else{
                    out.print(this.cells[i][j].displayChar() + "  ");
                }
            }
            out.println();
            out.print(i + " ");
        }
    }
    public boolean allSunk(){
        boolean a = true;
        for (Ship s: this.ships){
            if (s.isSunk() == false){
                a = false;
            }
        }
        return a;
    }
    public static void main(String[] args){
        Board b = new Board(8,4);
        System.out.println(b.toString());

    }
}
