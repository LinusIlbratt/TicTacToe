import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private ArrayList<ArrayList<Character>> gameBoard;
    private int boardSize;

    // Constructor that initiates an empty dynamic board
    public GameBoard(int boardSize){
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

    public boolean isEmpty(int row, int col) {
        boolean empty = gameBoard.get(row).get(col) == ' ';
        return empty;
    }

    public void placeMove(int row, int col, char gameSymbol) {
        // Places a game symbol on the board if the cell is empty
        if (isEmpty(row, col)) {
            gameBoard.get(row).set(col, gameSymbol);
        } else {
            System.out.println("Could not place " + gameSymbol);
        }
    }

    public boolean isFull() {
        // Check to see if the board is full
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (isEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinner() {
        // Checking rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (isWinningLine(0, i, 1, 0) || // Columns
            isWinningLine(i, 0, 0, 1)) {     // Rows
                return true;
            }
        }
        // Checking the crosses (diagonals)
        return isWinningLine(0, 0, 1, 1) ||  // First diagonal
                isWinningLine(0, boardSize - 1, 1, -1); // Second diagonal
    }

    private boolean isWinningLine(int startX, int startY, int stepX, int stepY) {
        char gameSymbol = gameBoard.get(startX).get(startY);
        if (gameSymbol == ' ') return false;

        for (int i = 1; i < boardSize; i++) {
            if (gameBoard.get(startX + i * stepX).get(startY + i * stepY) != gameSymbol) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder printBoard = new StringBuilder();

        // Skriv ut kolumnbokstäverna överst
        printBoard.append("  ");
        for (int col = 0; col < boardSize; col++) {
            char colLetter = (char) ('A' + col);
            printBoard.append(" ").append(colLetter).append(" ");
        }
        printBoard.append('\n');


        for (int i = 0; i < boardSize; i++) {
            // Print row numbers
            printBoard.append(String.format("%2d ", i + 1));
            for (int j = 0; j < boardSize; j++) {
                printBoard.append(gameBoard.get(i).get(j));
                if (j < boardSize - 1) {
                    printBoard.append(" |");
                }
            }
            printBoard.append('\n');  // New line after each row

            // Print horizontal separators except after the last row
            if (i < boardSize - 1) {
                printBoard.append("   ");  // Extra space for the row number
                for (int j = 0; j < boardSize - 1; j++) {
                    printBoard.append("--+");
                }
                printBoard.append("--\n");
            }
        }
        return printBoard.toString();
    }


}
