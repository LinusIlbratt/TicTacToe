import java.util.ArrayList;

public class GameBoard {
    private final ArrayList<ArrayList<Character>> gameBoard;
    private final int boardSize;

    // Constructor that initiates an empty dynamic board.
    public GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.gameBoard = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < boardSize; j++) {
                row.add(' ');
            }
            gameBoard.add(row);
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    // If the cell is empty on the specific row and column, we place a player symbol.
    public void placePlayerMove(int row, int col, char gameSymbol) {
        // Places a game symbol on the board if the cell is empty
        gameBoard.get(row).set(col, gameSymbol);
    }

    // Checks if the cell at the specified row and column is empty.
    public boolean isGameBoardEmpty(int row, int col) {
        return gameBoard.get(row).get(col) == ' ';
    }

    // Checks if the game board is full.
    public boolean isGameBoardFull() {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (isGameBoardEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check the game board for a winning line, horizontally, vertically or diagonally.
    public boolean checkWinner() {
        // Checking rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkWinningLines(0, i, 1, 0) || // Columns
                    checkWinningLines(i, 0, 0, 1)) {     // Rows
                return true;
            }
        }
        // Checking the crosses (diagonals)
        return checkWinningLines(0, 0, 1, 1) ||  // First diagonal
                checkWinningLines(0, boardSize - 1, 1, -1); // Second diagonal
    }

    // Checks a specific line for a win based on starting point and step direction.
    private boolean checkWinningLines(int startX, int startY, int stepX, int stepY) {
        char gameSymbol = gameBoard.get(startX).get(startY);
        if (gameSymbol == ' ') return false;

        for (int i = 1; i < boardSize; i++) {
            if (gameBoard.get(startX + i * stepX).get(startY + i * stepY) != gameSymbol) {
                return false;
            }
        }
        return true;
    }

    // Reset each cell on the game board to an empty (space) character (' ').
    public void resetGameBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard.get(i).set(j, ' ');
            }
        }
    }

    // Generates a formatted string representation of the game board for display.
    public String displayGameBoard() {
        StringBuilder printBoard = new StringBuilder();

        // Printing letters for the top in the color orange
        printBoard.append(GameColors.COORDINATE_COLOR + "  ");
        for (int col = 0; col < boardSize; col++) {
            char colLetter = (char) ('A' + col);
            printBoard.append(" ").append(colLetter).append(" ");
        }
        printBoard.append(GameColors.ANSI_RESET + '\n');


        for (int i = 0; i < boardSize; i++) {
            // Print row numbers in orange
            printBoard.append(GameColors.COORDINATE_COLOR).append(String.format("%2d ", i + 1)).append(GameColors.ANSI_RESET);
            for (int j = 0; j < boardSize; j++) {

                // Coloring the symbols on the game board
                char currentGameSymbol = gameBoard.get(i).get(j);
                if (currentGameSymbol == 'X') {
                    printBoard.append(GameColors.PLAYER1_COLOR).append(currentGameSymbol).append(GameColors.ANSI_RESET);
                } else if (currentGameSymbol == 'O') {
                    printBoard.append(GameColors.PLAYER2_COLOR).append(currentGameSymbol).append(GameColors.ANSI_RESET);
                } else {
                    printBoard.append(currentGameSymbol);
                }


                if (j < boardSize - 1) {
                    printBoard.append(" |");
                }
            }
            printBoard.append('\n');  // New line after each row

            // Print horizontal separators except after the last row
            if (i < boardSize - 1) {
                printBoard.append("   ");  // Extra space for the row number
                printBoard.append("--+".repeat(boardSize - 1));
                printBoard.append("--\n");
            }
        }
        return printBoard.toString();
    }

    // Converts the given row and column coordinates to a string format.
    public static String coordinatesToString(int row, int col) {
        char columnChar = (char) ('A' + col);
        return columnChar + String.valueOf(row + 1);
    }

    @Override
    public String toString() {
        return displayGameBoard();
    }

}
