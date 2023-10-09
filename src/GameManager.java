import java.util.InputMismatchException;
import java.util.Scanner;

public class GameManager {
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

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

    public void initializePlayers(boolean isSinglePlayer) {

        // Initializes players based on the game mode (single or multiplayer)
        if (isSinglePlayer) {
            System.out.println("Enter your name:");
            String player1Name = sc.next();
            this.player1 = new HumanPlayer(player1Name, 'X', sc);
            this.player2 = new ComputerPlayer("Computer", 'O');
        } else {
            System.out.println("Enter name for Player 1:");
            String player1Name = sc.next();

            System.out.println("Enter name for Player 2:");
            String player2Name = sc.next();

            this.player1 = new HumanPlayer(player1Name, 'X', sc);
            this.player2 = new HumanPlayer(player2Name, 'O', sc);
        }
        this.currentPlayer = this.player1;
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

    public void displayMenu() {
        System.out.println("Welcome to TicTacToe!");

        int userChoice = 0;

        while (userChoice < 1 || userChoice > 2) {
            System.out.println("Choose your game mode");
            System.out.println("1. Single Player");
            System.out.println("2. Multiplayer");

            if (sc.hasNextInt()) {
                userChoice = sc.nextInt();
            } else {
                System.out.println("Invalid choice. Enter a number.");
                sc.next(); // Consume the invalid input
            }

            sc.nextLine(); // Empty the buffer

            switch (userChoice) {
                case 1 -> initializePlayers(true);
                case 2 -> initializePlayers(false);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        chooseGameBoardSize();
    }

}

