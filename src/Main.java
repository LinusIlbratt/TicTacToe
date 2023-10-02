import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ange namn för Player 1: ");
        String player1Name = sc.nextLine();
        Player player1 = new Player(player1Name, 'X');

        System.out.println("Ange namn för Player 2: ");
        String player2Name = sc.nextLine();
        Player player2 = new Player(player2Name, 'O');





        System.out.println(player1.getPlayerName() + " enter your placement: 1-9");
        String player1Position = sc.nextLine();

        // Enhanced switch statement (break method is included).
        switch (player1Position) {
            case "1" -> board.placePlayerSymbol(0, 0, player1.getTicTacSymbol());
            case "2" -> board.placePlayerSymbol(0, 4, player1.getTicTacSymbol());
            case "3" -> board.placePlayerSymbol(0, 8, player1.getTicTacSymbol());
            case "4" -> board.placePlayerSymbol(2, 0, player1.getTicTacSymbol());
            case "5" -> board.placePlayerSymbol(2, 4, player1.getTicTacSymbol());
            case "6" -> board.placePlayerSymbol(2, 8, player1.getTicTacSymbol());
            case "7" -> board.placePlayerSymbol(4, 0, player1.getTicTacSymbol());
            case "8" -> board.placePlayerSymbol(4, 4, player1.getTicTacSymbol());
            case "9" -> board.placePlayerSymbol(4, 8, player1.getTicTacSymbol());
            default -> System.out.println("Invalid input, try again");
        }

        System.out.println(board);

        System.out.println(player2.getPlayerName() + " enter your placement: 1-9");
        String player2Position = sc.nextLine();

        switch (player2Position) {
            case "1" -> board.placePlayerSymbol(0, 0, player2.getTicTacSymbol());
            case "2" -> board.placePlayerSymbol(0, 4, player2.getTicTacSymbol());
            case "3" -> board.placePlayerSymbol(0, 8, player2.getTicTacSymbol());
            case "4" -> board.placePlayerSymbol(2, 0, player2.getTicTacSymbol());
            case "5" -> board.placePlayerSymbol(2, 4, player2.getTicTacSymbol());
            case "6" -> board.placePlayerSymbol(2, 8, player2.getTicTacSymbol());
            case "7" -> board.placePlayerSymbol(4, 0, player2.getTicTacSymbol());
            case "8" -> board.placePlayerSymbol(4, 4, player2.getTicTacSymbol());
            case "9" -> board.placePlayerSymbol(4, 8, player2.getTicTacSymbol());
            default -> System.out.println("Invalid input, try again");
        }

        System.out.println(board);


    }
}