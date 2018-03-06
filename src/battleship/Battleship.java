package battleship;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Battleship implements Serializable{
    public static final String ALL_SHIPS_SUNK = "All ships sunk!";
    public static final String BAD_ARG_COUNT = "Wrong number of arguments for command";
    public static final String MISSING_SETUP_FILE = "No setup file specified";
    public static final int MAX_DIM = 10;
    public static final String DIM_TOO_BIG = "The coordinate dimensions are too big";
    public static final String BAD_CONFIG_FILE = "File didn't have proper configuration";
    public static final String PROMPT = "> ";
    public static final String WHITESPACE = " ";
    private PrintStream out;
    private Board board;

    /**
     *
     * @param a
     * @return
     * @throws OverlapException
     * @throws OutOfBoundsException
     * @throws BattleshipException
     */
    private boolean setup(String[] a) throws OverlapException, OutOfBoundsException, BattleshipException {
        if (checkArgCount(a,2)) {
            if (Integer.parseInt(a[0]) <= MAX_DIM || Integer.parseInt(a[1]) <= MAX_DIM){
                Board b = new Board(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
                this.board = b;
            }
            else {
                throw new BattleshipException(DIM_TOO_BIG);
            }

        }
        else if (checkArgCount(a,4)){
            if (a[2].equals("HORIZONTAL")){
                Ship ship = new Ship(this.board,Integer.parseInt(a[0]),Integer.parseInt(a[1]), Ship.Orientation.HORIZONTAL,Integer.parseInt(a[3]));
                this.board.addShip(ship);
            }
            else{
                Ship ship = new Ship(this.board,Integer.parseInt(a[0]),Integer.parseInt(a[1]), Ship.Orientation.VERTICAL,Integer.parseInt(a[3]));
                this.board.addShip(ship);
            }
        }
        else{
            throw new BattleshipException(BAD_CONFIG_FILE);
        }
        return true;
    }
    private void promptPlayer(){
        System.out.print(PROMPT);
        out.print(PROMPT);
    }
    private void play(){

        BufferedReader q = new BufferedReader(new InputStreamReader(System.in));
        boolean something = true;
        while (!this.board.allSunk() && something) {
            this.promptPlayer();
            String hit_coordinates = null;
            try {
                hit_coordinates = q.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print(hit_coordinates);
            String[] h_coordinates = hit_coordinates.split(WHITESPACE);
            if (h_coordinates[0].equals("h")) {
                try {
                    if (this.hit(h_coordinates) == false){
                        out.println(BAD_ARG_COUNT);
                    }
                    else {
                        Cell c = this.board.getCells()[Integer.parseInt(h_coordinates[1])][Integer.parseInt(h_coordinates[2])];
                        if (c.getStatus() == '☐' || c.getStatus() == '*') {
                            if (c.getS().isSunk()) {
                                out.println();
                                out.println(Ship.SUNK_MESSAGE);
                            }
                        }
                        out.println();
                        this.board.display(out);
                    }
                } catch (OutOfBoundsException e) {
                    e.printStackTrace();
                } catch (BattleshipException e) {
                    e.printStackTrace();
                }
            } else if (h_coordinates[0].equals("s")) {
                this.save(h_coordinates);
                this.out.println();
            } else if (h_coordinates[0].equals("q")) {
                something = this.quit(h_coordinates);
                this.out.println();
            } else if (h_coordinates[0].equals("!")) {
                this.out.println();
                if (this.cheat(h_coordinates)) {
                    this.board.fullDisplay(out);
                }
            } else {

            }

        }
        if (something) {

            out.println(ALL_SHIPS_SUNK);

        }
    }
    private boolean hit(String[] b) throws OutOfBoundsException, BattleshipException {
        if (checkArgCount(b,3)){
            Cell c = this.board.getCell(Integer.parseInt(b[1]),Integer.parseInt(b[2]));
            if (c.getStatus() == 'S'){
                try {
                    this.board.getCells()[Integer.parseInt(b[1])][Integer.parseInt(b[2])].hit();
                    int index = 0;
                    for(int i = 0; i<this.board.getShips().size();i++){
                        Ship s = this.board.getShips().get(i);
                        if(s.getOrt() == Ship.Orientation.HORIZONTAL){
                            for (int j = 0; j<s.getLength();j++){
                                if (Integer.parseInt(b[1] ) == s.getuRow() && Integer.parseInt(b[2] ) == (s.getlCol()+j)){
                                    index = i;
                                }
                            }
                        }
                        else{
                            for (int j = 0; j<s.getLength();j++){
                                if (Integer.parseInt(b[1] ) == (s.getuRow()+j) && Integer.parseInt(b[2] ) == s.getlCol()){
                                    index = i;
                                }
                            }
                        }
                    }
                    Ship ship = this.board.getShips().get(index);
                    if (ship.isSunk()){
                        if(ship.getOrt() == Ship.Orientation.HORIZONTAL){
                            for (int o = 0; o<ship.getLength();o++){
                                this.board.getCells()[ship.getuRow()][ship.getlCol()+o].setStatus('*');
                            }
                        }
                        else{
                            for (int o = 0; o<ship.getLength();o++){
                                this.board.getCells()[ship.getuRow()+o][ship.getlCol()].setStatus('*');
                            }
                        }
                    }
                } catch (CellPlayedException e) {
                    e.printStackTrace();
                }

                return true;
            }
            else if(c.getStatus() == '_'){
                try {
                    this.board.getCells()[Integer.parseInt(b[1])][Integer.parseInt(b[2])].hit();
                } catch (CellPlayedException e) {
                    e.printStackTrace();
                }
                return true;
            }
            else {
                return false;
            }
        }
        else{
            System.out.println("Wrong number of arguments for command");
            return false;
        }
    }
    private boolean cheat(String[] c){
        if (checkArgCount(c,1)){
            return true;
        }
        return false;
    }
    private boolean checkArgCount(String[] c, int a){
        return c.length == a;
    }
    private boolean save(String[] s){
        try {
            ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(s[1]));
            outstream.writeObject(this.board);
            return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean quit(String[] q){
        return false;
    }
    public static void main(String[] args) throws OverlapException, OutOfBoundsException {
        if (args.length == 1){
            try {

                if (args[0].endsWith("bin")){
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
                    Battleship b = new Battleship();
                    b.board = (Board) in.readObject();
                    PrintStream out = new PrintStream("C:\\Users\\William\\Desktop\\Git\\lab06-WCJ7833\\game.txt");
                    b.out = out;
                    out.print("Checking if " + args[0] + " is a saved game file... ");
                    out.print("yes");
                    out.println();
                    out.println();
                    b.board.display(out);
                    b.play();
                }
                else {
                    Battleship battleship = new Battleship();
                    PrintStream out = new PrintStream("C:\\Users\\William\\Desktop\\Git\\lab06-WCJ7833\\game.txt");
                    battleship.out = out;
                    battleship.out.print("Checking if " + args[0] + " is a saved game file... ");
                    battleship.out.print("no; will read as a text setup file.");
                    battleship.out.println();
                    battleship.out.println();
                    BufferedReader b = new BufferedReader(new FileReader(args[0]));
                    String dimensions = b.readLine();
                    while (dimensions != null) {
                        String[] l = dimensions.split(WHITESPACE);
                        try {
                            battleship.setup(l);
                        } catch (BattleshipException e) {
                            e.printStackTrace();
                        }
                        dimensions = b.readLine();

                    }
                    battleship.board.display(battleship.out);
                    battleship.play();
                }

            }
            catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println(MISSING_SETUP_FILE);
        }
    }
}
