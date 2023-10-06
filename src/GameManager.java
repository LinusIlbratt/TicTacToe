import java.util.Scanner;

public class GameManager {
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Player player1;
    private Player player2;
    private Scanner sc = new Scanner(System.in);


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
            this.player1 = new HumanPlayer(playerName, 'X');
            this.player2 = new ComputerPlayer("Computer", 'O');
        } else {
            System.out.println("Enter name for Player 1:");
            String player1Name = sc.next();
            System.out.println("Enter name for Player 2:");
            String player2Name = sc.next();
            this.player1 = new HumanPlayer(player1Name, 'X');
            this.player2 = new HumanPlayer(player2Name, 'O');
        }
        this.currentPlayer = this.player1;
    }

    public void displayTotalWins(){
        System.out.println("This is the current score for the game: ");
        System.out.println(player1.getPlayerName() + ": " + player1.getTotalWins() + " wins.");
        System.out.println(player2.getPlayerName() + ": " + player2.getTotalWins() + " wins.");
    }

    public void startGame() {
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
        // Show result and number of wins
        displayTotalWins();
    }

    public void displayMenu() {
        System.out.println("Welcome to TicTacToe!");
        System.out.println("Choose your game mode");
        System.out.println("1. Singel Player");
        System.out.println("2. Multiplayer");

        int userChoice = sc.nextInt();
        switch (userChoice) {
            case 1:
                initializePlayers(true); // True indicates singel player
                break;
            case 2:
                initializePlayers(false); // False indicates multiplayer
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                displayMenu();
                break;
        }

        System.out.println("Select your board size:");
        System.out.println("1. 3x3");
        System.out.println("2. 4x4");
        System.out.println("3. 5x5");

        int boardSizeChoice = sc.nextInt();
        switch (boardSizeChoice) {
            case 1:
                gameBoard = new GameBoard(3); // Initiates the board size to 3x3.
                break;
            case 2:
                gameBoard = new GameBoard(4); // Initiates the board size to 4x4.
                break;
            case 3:
                gameBoard = new GameBoard(5); // Initiates the board size to 5x5.
                break;
            default:
                System.out.println("Invalid board size choice. Try again");
                displayMenu();
                break;
        }
    }

}

