import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player{

    private Scanner sc;

    public HumanPlayer(String playerName, char gameSymbol, Scanner sc) {
        super(playerName, gameSymbol);
        this.sc = sc;
    }


    public int[] makeMove(GameBoard gameBoard) {
        int[] playerMove = new int[2];
        boolean validInput = false;

        System.out.println("Current player is :" + getPlayerName());
        System.out.println(getPlayerName() + " make your move (format: A1, B2, ...): ");

        while (!validInput) {
            try {
                String input = sc.nextLine().toUpperCase();

                char colChar = input.charAt(0);
                if (colChar < 'A' || colChar >= 'A' + gameBoard.getBoardSize()) {
                    throw new IllegalArgumentException("Invalid column");
                }
                int col = colChar - 'A';


                int row = Integer.parseInt(input.substring(1)) - 1;

                if (row >= 0 && row < gameBoard.getBoardSize() && col >= 0 && col < gameBoard.getBoardSize()) {
                    playerMove[0] = row;
                    playerMove[1] = col;
                    validInput = true;

                    System.out.println(getPlayerName() + " has chosen " + input);
                    gameBoard.placeMove(row, col, getGameSymbol());
                } else {
                    System.out.println("Invalid move. Please enter a move within the board's bounds.");
                }
            } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter in the format 'A1', 'B2', etc.");
            }
        }

        return playerMove;
    }

}
