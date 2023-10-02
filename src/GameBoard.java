public class GameBoard {
    private char[][] gameBoard; //

    // Constructor creates a 3x3 2D array with chars.
    // Each position is filled with 'space' that will represent an empty TicTacToe board.
    public GameBoard() {
        gameBoard = new char[][]{
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {'-', '-', '+', '-', '-', '-', '+', '-', '-'},
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {'-', '-', '+', '-', '-', '-', '+', '-', '-'},
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '}
        };
    }


    public void placePlayerSymbol(int row, int col, char symbol) {
        gameBoard[row][col] = symbol;
    }


    // Override the default toString method to provide a visual representation
    // of my TicTacToe game board using the StringBuilder class.
    @Override
    public String toString() {
        StringBuilder printBoard = new StringBuilder();
        for (char[] row : gameBoard) {
            for (char c : row) {
                printBoard.append(c);
            }
            printBoard.append('\n');  // New line after each row
        }
        return printBoard.toString();
    }
}
