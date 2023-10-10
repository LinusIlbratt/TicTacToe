import java.util.InputMismatchException;
import java.util.Scanner;

public class GameManager {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player startingPlayer;

    private final Scanner sc = new Scanner(System.in);
    private boolean gameIsRunning;


    public GameManager() {
        // We don't initialize anything with the constructor since we do that with the menu
        this.player1 = null;
        this.player2 = null;
        this.currentPlayer = null;
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public void initializeSinglePlayer() {
        this.player1 = HumanPlayer.createHumanPlayer('X', sc);
        this.player2 = new ComputerPlayer("Computer", 'O');
        this.startingPlayer = player1;
        this.currentPlayer = startingPlayer;
    }

    public void initializeMultiplayer(){
        this.player1 = HumanPlayer.createHumanPlayer('X', sc);
        this.player2 = HumanPlayer.createHumanPlayer('O', sc);
        this.startingPlayer = player1;
        this.currentPlayer = startingPlayer;
    }

    public void displayTotalWins() {
        System.out.println("Current score: ");
        System.out.println(player1.getPlayerName() + ": " + player1.getTotalWins() + " wins.");
        System.out.println(player2.getPlayerName() + ": " + player2.getTotalWins() + " wins.");
    }

    public void gameLoop() {
        boolean gameIsRunning = true;
        while (gameIsRunning) {
            // Display the board
            System.out.println(gameBoard);

            // Let the current player make a move by calling the makeMove function
            System.out.println("Current player is: " + currentPlayer.getPlayerName());
            currentPlayer.makeMove(gameBoard);

            // Check if a player has won or if the board is full, then it's a tie
            if (gameBoard.checkWinner()) {
                System.out.println(currentPlayer.getPlayerName() + " has won!");
                currentPlayer.incrementWins();
                gameIsRunning = false;
            } else if (gameBoard.isGameBoardFull()) {
                System.out.println("It's a tie!");
                gameIsRunning = false;
            } else {
                // Switching players by calling the switchPlayer method
                switchPlayer();
            }
        }
    }

    public void startGame() {
        String playAgain;
        do {
            gameLoop();
            displayTotalWins();
            System.out.println("Do you want to play again? (y/n)");

            playAgain = sc.nextLine().trim();
            if (playAgain.equalsIgnoreCase("y")) {
                if (startingPlayer == player1) {
                    startingPlayer = player2;
                } else {
                    startingPlayer = player1;
                }
                currentPlayer = startingPlayer; // Set the currentPlayer to the new starting player
                gameBoard.resetGameBoard();
            }
        } while (playAgain.equalsIgnoreCase("y"));

    }

    public void chooseGameBoardSize(){
        boolean invalidUserChoice;

        do {
            invalidUserChoice = false;
            System.out.println("Select your board size.");
            System.out.println("3. 3x3");
            System.out.println("4. 4x4");
            System.out.println("5. 5x5");
            System.out.print("> ");

            try {
                int userBoardChoice = sc.nextInt();
                sc.nextLine(); // Clear the buffer

                if (userBoardChoice >= 3 && userBoardChoice <= 5) {
                    gameBoard = new GameBoard(userBoardChoice);
                } else {
                    invalidUserChoice = true;
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); // Clear the buffer
                invalidUserChoice = true;
            }

            if (invalidUserChoice) {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (invalidUserChoice);
    }

    public void displayRules(){
        System.out.println("""
                Rules:
                               
                The game is played on a grid that's 3x3 (or large if chosen).
                Player 1 is X and Player 2 is 0.
                Players take turns putting their symbols on the grid.
                The first player to get 3 (or the board size) of their symbols in a row (up, down, across, or diagonally) wins.
                If all the cells on the grid are filled and no player has their symbols in a row, then the game is a tie.
                """);
    }

    public void gameMenu() {
        System.out.println("Welcome to TicTacToe!\n");

        displayRules();

        System.out.println("Press Enter to continue...");
        sc.nextLine(); // This will wait for the user to press any key

        while (true) {
            System.out.println("Choose your game mode");
            System.out.println("1. Single Player");
            System.out.println("2. Multiplayer");

            String input = sc.nextLine().trim();

            try {
                int userChoice = Integer.parseInt(input);

                if (userChoice == 1) {
                    initializeSinglePlayer();
                    break;
                } else if (userChoice == 2) {
                    initializeMultiplayer();
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number.");
            }
        }

        chooseGameBoardSize();
    }


}

