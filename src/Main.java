import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard board3x3 = new GameBoard(3);
        System.out.println(board3x3);

        GameBoard board4x4 = new GameBoard(4);
        System.out.println(board4x4);

        GameBoard board5x5 = new GameBoard(5);
        System.out.println(board5x5);
    }
}