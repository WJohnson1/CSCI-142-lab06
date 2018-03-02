package battleship;

import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 */
public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    private int row;
    private int column;
    private char status;
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.status = PRISTINE_WATER;
    }
    /**
     * Place a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     * @param ship the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
    public void putShip(Ship ship) throws OverlapException {
        if (this.status == HIDDEN_SHIP_SECTION || this.status == HIT_WATER || this.status == HIT_SHIP_SECTION){
            throw new OverlapException();
        }
        else{
            this.status='S';
        }

    }
    public void hit() throws CellPlayedException{
        if (this.status == HIT_WATER || this.status == HIT_SHIP_SECTION){
            throw new CellPlayedException(this.row,this.column,"You hit");
        }
        else if (this.status == HIDDEN_SHIP_SECTION){
            this.status = HIT_SHIP_SECTION;
        }
        else{
            this.status = HIT_WATER;
        }
    }
    public char displayHitStatus(){
        if (this.status == HIDDEN_SHIP_SECTION || this.status == PRISTINE_WATER) {
            return PRISTINE_WATER;
        }
        else if( this.status == HIT_WATER){
            return HIT_WATER;
        }
        else {
            return HIT_SHIP_SECTION;
        }
    }
    public char displayChar(){
        return this.status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
