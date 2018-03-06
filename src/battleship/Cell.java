package battleship;

import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 * @author William Johnson
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
    private Ship s;
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.status = PRISTINE_WATER;
        this.s = null;
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
            throw new OverlapException(ship.getuRow(),ship.getlCol());
        }
        else{
            this.s = ship;
            this.status='S';
        }

    }

    /**
     * Changes the status of the cell is changed based on the logic of battleship
     * @throws CellPlayedException the cell has already been hit
     */
    public void hit() throws CellPlayedException{
        if (this.status == HIT_WATER || this.status == HIT_SHIP_SECTION){
            throw new CellPlayedException(this.row,this.column);
        }
        else if (this.status == HIDDEN_SHIP_SECTION){

            this.getS().hit();
            if (this.getS().isSunk()){
                this.setStatus('*');
            }
            this.status = HIT_SHIP_SECTION;

        }
        else{
            this.status = HIT_WATER;
        }
    }

    /**
     * Returns the hit status of the cell
     * @return the status that the player can see during the game
     */
    public char displayHitStatus(){
        if (this.status == HIDDEN_SHIP_SECTION || this.status == PRISTINE_WATER) {
            return PRISTINE_WATER;
        }
        else if( this.status == HIT_WATER){
            return HIT_WATER;
        }
        else if(this.status == '*'){
            return '*';
        }
        else {
            return HIT_SHIP_SECTION;
        }
    }

    /**
     * Returns the status of the cell
     * @return the status of the cel
     */
    public char displayChar(){
        return this.status;
    }

    /**
     * Sets the value of the cell's status to another value
     * @param status the status that the cell will be set to
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * Returns the status of the cell
     * @return the status of the cell
     */
    public char getStatus() {
        return status;
    }

    /**
     * Returns the ship that is on the cell
     * @return the ship that is on the cell
     */
    public Ship getS() {
        return s;
    }
}
