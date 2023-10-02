import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Scanner sc = new Scanner(System.in);

        System.out.println(board);

        System.out.println("Enter your placement: 1-9");
        String playerPosition = sc.nextLine();

        // Enhanced switch statement (break method is included).
        switch (playerPosition) {
            case "1" -> board.placePlayerSymbol(0, 0, 'X');
            case "2" -> board.placePlayerSymbol(0, 4, 'X');
            case "3" -> board.placePlayerSymbol(0, 8, 'X');
            case "4" -> board.placePlayerSymbol(2, 0, 'X');
            case "5" -> board.placePlayerSymbol(2, 4, 'X');
            case "6" -> board.placePlayerSymbol(2, 8, 'X');
            case "7" -> board.placePlayerSymbol(4, 0, 'X');
            case "8" -> board.placePlayerSymbol(4, 4, 'X');
            case "9" -> board.placePlayerSymbol(4, 8, 'X');
            default -> System.out.println("Invalid input, try again");
        }

        System.out.println(board);


    }
}