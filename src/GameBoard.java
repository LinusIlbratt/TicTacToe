import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private  char[][] gameBoard;

    private int boardSize;
    private int movesMade = 0; // Add a counter to the game

     //Constructor creates a 3x3 2D array with chars.
     //Each position is filled with 'space' that will represent an empty TicTacToe board.
    public GameBoard() {
        gameBoard = new char[][]{
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {'-', '-', '+', '-', '-', '-', '+', '-', '-'},
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '},
                {'-', '-', '+', '-', '-', '-', '+', '-', '-'},
                {' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' '}
        };
    }

    // Constructor that receives an int value for the board size.
    public GameBoard(int boardSize){
        int rows = 2 * boardSize - 1;
        int cols = 3 * boardSize - 1;
        gameBoard = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i % 2 == 0 && j % 3 == 2) {
                    gameBoard[i][j] = '|';
                } else if (i % 2 == 1) {
                    if (j == cols - 1) {
                        gameBoard[i][j] = '-';
                    } else {
                        gameBoard[i][j] = (j % 3 == 2) ? '+' : '-';
                    }
                } else {
                    gameBoard[i][j] = ' ';
                }
            }
        }
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public int getBoardSize(){
        return boardSize;
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

    public List<Integer> getAvailableMoves() {
        List<Integer> availableMoves = new ArrayList<>();
        char[][] board = getGameBoard(); // Making a copy of the current board and not a new board.
        // Makes it easier to work with, so we don't have to use a get method each time we want to make a reference.
        int boardSize = board.length; // Getting total amounts of rows (not columns)

        for (int row = 0; row < boardSize; row++){
            for (int col = 0; col < boardSize; col++){
                if(board[row][col] == ' ') {
                    int positionsLeft = row * boardSize + col + 1; // Converts our 2D array to 1D positions with a 1-base index. (1, 2, 3 etc)
                    availableMoves.add(positionsLeft);
                }
            }
        }
        return availableMoves;
    }

    public boolean isBoardFull() {
        return movesMade >= 9;
    }

    public boolean winningCondition(char symbol) {
        return  // Horizontal lines
                (gameBoard[0][0] == symbol && gameBoard[0][4] == symbol && gameBoard[0][8] == symbol) ||
                (gameBoard[2][0] == symbol && gameBoard[2][4] == symbol && gameBoard[2][8] == symbol) ||
                (gameBoard[4][0] == symbol && gameBoard[4][4] == symbol && gameBoard[4][8] == symbol) ||
                // Vertical lines
                (gameBoard[0][0] == symbol && gameBoard[2][0] == symbol && gameBoard[4][0] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[2][4] == symbol && gameBoard[4][4] == symbol) ||
                (gameBoard[0][8] == symbol && gameBoard[2][8] == symbol && gameBoard[4][8] == symbol) ||
                // Cross lines
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


     //Override the default toString method to provide a visual representation
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
