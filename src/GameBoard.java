public class GameBoard {
    private  char[][] gameBoard;
    private int movesMade = 0; // Add a counter to the game

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

    //Method for the placement of the TicTac Symbols and to check to see if a position is free to place the player symbol.
    public boolean placePlayerSymbol(int row, int col, char symbol) {
        if (row < 0 || row > 4 || col < 0 || col > 8 || gameBoard[row][col] != ' ') {
            return false;
        }
        gameBoard[row][col] = symbol;
        movesMade++; // Increase the counter each time a move is made
        return true;
    }

    public boolean isBoardFull() {
        return movesMade >= 9;
    }

    public boolean winningCondition(char symbol) {
        return (gameBoard[0][0] == symbol && gameBoard[0][4] == symbol && gameBoard[0][8] == symbol) ||
                (gameBoard[2][0] == symbol && gameBoard[2][4] == symbol && gameBoard[2][8] == symbol) ||
                (gameBoard[4][0] == symbol && gameBoard[4][4] == symbol && gameBoard[4][8] == symbol) ||

                (gameBoard[0][0] == symbol && gameBoard[0][2] == symbol && gameBoard[0][4] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[2][4] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][8] == symbol && gameBoard[2][8] == symbol && gameBoard[4][8] == symbol) ||

                (gameBoard[0][0] == symbol && gameBoard[2][4] == symbol && gameBoard[4][8] == symbol) ||
                (gameBoard[0][8] == symbol && gameBoard[2][4] == symbol && gameBoard[4][0] == symbol);
    }

    public void resetGameBoard(){
        gameBoard = new char[][]{
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {'-', '-', '+', '-', '-', '-', '+', '-', '-'},
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {'-', '-', '+', '-', '-', '-', '+', '-', '-'},
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '}
        };
        movesMade = 0;

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
