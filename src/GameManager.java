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

        if (isSinglePlayer) {
            System.out.println("Enter your name:");
            String playerName = sc.next();
            this.player1 = new HumanPlayer(playerName, 'X', sc);
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
        System.out.println("This is the current score for the game: ");
        System.out.println(player1.getPlayerName() + ": " + player1.getTotalWins() + " wins.");
        System.out.println(player2.getPlayerName() + ": " + player2.getTotalWins() + " wins.");
    }

    public void gameLoop() {
        boolean gameIsRunning = true;
        while (gameIsRunning) {
            // Display the board
            System.out.println(gameBoard);

            // Let the current player make a move
            currentPlayer.makeMove(gameBoard);

            // Check if a player has won or if the board is full, then it's a tie
            if (gameBoard.hasWinner()) {
                System.out.println(currentPlayer.getPlayerName() + " has won!");
                currentPlayer.incrementWins();
                gameIsRunning = false;
            } else if (gameBoard.isFull()) {
                System.out.println("It's a tie!");
                gameIsRunning = false;
            } else {
                switchPlayer();
            }
        }
    }


    public void startGame() {
        String playAgain;
        do {
            gameLoop();

            System.out.println(player1.getPlayerName() + " have " + player1.getTotalWins() + " total wins.");
            System.out.println(player2.getPlayerName() + " have " + player2.getTotalWins() + " total wins.");

            System.out.println("Do you want to play again? (y/n)");

            playAgain = sc.nextLine().trim();
            if (playAgain.equalsIgnoreCase("y")) {
                gameBoard.resetGameBoard();
            }
        } while (playAgain.equalsIgnoreCase("y"));

    }

    public void displayMenu() {
        System.out.println("Welcome to TicTacToe!");
        System.out.println("Choose your game mode");
        System.out.println("1. Singel Player");
        System.out.println("2. Multiplayer");

        int userChoice = sc.nextInt();
        sc.nextLine(); // Empty the buffer
        switch (userChoice) {
            case 1 -> initializePlayers(true); // True indicates singel player
            case 2 -> initializePlayers(false); // False indicates multiplayer
            default -> {
                System.out.println("Invalid choice. Try again.");
                displayMenu();
            }
        }

        System.out.println("Select your board size:");
        System.out.println("1. 3x3 (Classic 3 in a row");
        System.out.println("2. 4x4 (4 in a row");
        System.out.println("3. 5x5 (5 in a row");

        int boardSizeChoice = sc.nextInt();
        sc.nextLine(); // Empty the buffer
        switch (boardSizeChoice) {
            case 1 -> gameBoard = new GameBoard(3); // Initiates the board size to 3x3.
            case 2 -> gameBoard = new GameBoard(4); // Initiates the board size to 4x4.
            case 3 -> gameBoard = new GameBoard(5); // Initiates the board size to 5x5.
            default -> {
                System.out.println("Invalid board size choice. Try again");
                displayMenu();
            }
        }
    }

}

