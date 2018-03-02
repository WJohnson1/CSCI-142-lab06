package battleship;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Battleship {
    public static final String ALL_SHIPS_SUNK = "";
    public static final String BAD_ARG_COUNT = "";
    public static final String MISSING_SETUP_FILE = "";
    public static final int MAX_DIM = 9;
    public static final String DIM_TOO_BIG = "";
    public static final String BAD_CONFIG_FILE = "";
    public static final String PROMPT = "";
    public static final String WHITESPACE = "";
    private Board board;
    private boolean setup(String[] a) throws OverlapException, OutOfBoundsException {
        if (a.length == 2) {
            Board b = new Board(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
            this.board = b;
        }
        else {
            if (a[2].equals("HORIZONTAL")){
                Ship ship = new Ship(this.board,Integer.parseInt(a[0]),Integer.parseInt(a[1]), Ship.Orientation.HORIZONTAL,Integer.parseInt(a[3]));
                this.board.addShip(ship);
            }
            else{
                Ship ship = new Ship(this.board,Integer.parseInt(a[0]),Integer.parseInt(a[1]), Ship.Orientation.VERTICAL,Integer.parseInt(a[3]));
                this.board.addShip(ship);
            }
        }
        return true;
    }
    private void promptPlayer(){

    }
    private void play(){

    }
    private boolean hit(String[] b){
        return false;
    }
    private boolean cheat(String[] c){
        return false;
    }
    private boolean checkArgCount(String[] c, int a){
        return false;
    }
    private boolean save(String[] s){
        return false;
    }
    private boolean quit(String[] q){
        return false;
    }
    public static void main(String[] args) throws OverlapException, OutOfBoundsException {
        if (args.length == 1){
            try {
                BufferedReader b = new BufferedReader(new FileReader(args[0]));
                int count = 0;
                String dimensions = b.readLine();
                Battleship battleship = new Battleship();
                while (dimensions != null) {
                    if (count == 0) {
                        String[] l = dimensions.split(" ");
                        if (battleship.setup(l)) {
                            count = 1;
                        }
                    }
                    else {
                        String[] l = dimensions.split(" ");
                        battleship.setup(l);

                    }
                    dimensions = b.readLine();
                }
                System.out.println(battleship.board.toString());
                PrintStream out = new PrintStream("lab06-WCJ7833\\game.txt");
                battleship.board.display(out);
            }
            catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No setup file specified");
        }
    }
}
