import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner sc;

    public HumanPlayer(String playerName, char gameSymbol, Scanner sc) {
        super(playerName, gameSymbol);
        this.sc = sc;
    }

    public static HumanPlayer createHumanPlayer(char gameSymbol, Scanner sc) {
        String playerName;
        do {
            if (gameSymbol == 'X') {
                System.out.println("Enter the name for Player 1:");
            } else {
                System.out.println("Enter the name for Player 2:");
            }
            System.out.print("> ");
            playerName = sc.nextLine().trim();
        } while (!isValidPlayerName(playerName));

        return new HumanPlayer(playerName, gameSymbol, sc);
    }

    private static boolean isValidPlayerName(String playerName) {

        if (playerName == null || playerName.isEmpty()) {
            System.out.println("Name can't be empty.");
            return false;
        }

        if (!playerName.matches("[\\p{L} '-]+")) {
            System.out.println("Name can only contain letters, spaces, hyphens and apostrophes.");
            return false;
        }

        if (playerName.matches(".*\\d.*")) {
            System.out.println("Name can't contain numbers.");
            return false;
        }
        return true;
    }

    public void makeMove(GameBoard gameBoard) {
        boolean validInput = false; // boolean to check a valid input from the user

        while (!validInput) {
            System.out.println(getPlayerName() + " make your move (format: A1, B2, ....):");
            System.out.print("> ");
            String userInput = sc.nextLine().toUpperCase(); // converts input so user can write a1 or A1

            try {
                int col = userInput.charAt(0) - 'A'; // Takes the first letter from user and converts it to column index, A->0, B->1...
                int row = Integer.parseInt(userInput.substring(1)) - 1; // Converts the remaining String to an int and subtract with 1 to get row index.

                if (row >= 0 && row < gameBoard.getBoardSize() && col >= 0 && col < gameBoard.getBoardSize()) { // Check to se if row and column is not out of bound.
                    if (gameBoard.isGameBoardEmpty(row, col)) {
                        System.out.println(getPlayerName() + " has chosen " + GameBoard.coordinatesToString(row, col));
                        gameBoard.placePlayerMove(row, col, getGameSymbol());
                        validInput = true;
                    } else {
                        System.out.println("Position " + GameBoard.coordinatesToString(row, col) + " is already taken. Please try again.");
                    }
                } else {
                    System.out.println("Invalid move. Please enter a move within the board's bounds");
                }
            } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter the correct format 'A1', 'B2', etc.");
            }
        }

    }

}
