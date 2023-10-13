import java.util.InputMismatchException;
import java.util.Scanner;

public class GameManager {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player startingPlayer;
    private final Scanner sc = new Scanner(System.in);
    private static final String YES = "y";

    public GameManager() {
        // We don't initialize anything with the constructor. Setting the players to null just to make it more clear.
        this.player1 = null;
        this.player2 = null;
        this.currentPlayer = null;
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
            System.out.println("3. Human vs AI");
            System.out.println("4. Computer Battle");
            System.out.println("5. Exit");
            System.out.print("> ");

            String input = sc.nextLine().trim();

            try {
                int userChoice = Integer.parseInt(input);

                switch (userChoice) {
                    case 1 -> {
                        initializeSinglePlayer();
                        chooseGameBoardSize();
                        startGame();
                    }
                    case 2 -> {
                        initializeMultiplayer();
                        chooseGameBoardSize();
                        startGame();
                    }
                    case 3 -> {
                        gameBoard = new GameBoard(3);
                        initializeHumanVsAI(gameBoard);
                        startGame();
                    }
                    case 4 -> {
                        gameBoard = new GameBoard(3);
                        initializeComputerBattle(gameBoard);
                        startGame();
                    }
                    case 5 -> {
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number.");
            }
        }


    }

    public void startGame() {
        boolean playAgain;
        do {
            gameLoop();
            displayTotalWins();
            playAgain = wantsToPlayAgain();
            if (playAgain) {
                resetGameForNewRound();
            }
        } while (playAgain);

    }

    public void gameLoop() {
        while (true) {
            displayCurrentState();
            currentPlayer.makeMove(gameBoard);
            if (gameHasEnded()) break;
            switchPlayer();
        }
    }

    private boolean gameHasEnded() {
        if (gameBoard.checkWinner()) {
            announceWinner();
            currentPlayer.incrementWins();
            return true;
        }
        if (gameBoard.isGameBoardFull()) {
            announceTie();
            return true;
        }
        return false;
    }

    private void announceWinner() {
        System.out.println(currentPlayer.getPlayerName() + " has won!\n");
        System.out.println(gameBoard);
    }

    public void announceTie(){
        System.out.println("It's a tie!");
    }

    private void displayCurrentState() {
        System.out.println(gameBoard);
        System.out.println("Current player is: " + currentPlayer.getPlayerName());
    }

    // Determines the size of the game board
    public void chooseGameBoardSize() {
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

    public void initializePlayers(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        // Handle identical names if both players are humans
        if (player1 instanceof HumanPlayer && player2 instanceof  HumanPlayer && player1.getPlayerName().equals(player2.getPlayerName())) {
            System.out.println("Identical names, Player 2 is now " + player2.getPlayerName() + "2.");
        }

        this.startingPlayer = this.player1;
        this.currentPlayer = this.startingPlayer;

    }

    // Create objects for singel player
    public void initializeSinglePlayer() {
        initializePlayers(HumanPlayer.createHumanPlayer('X', sc), new ComputerPlayer("Computer", 'O'));
    }

    // Create objects for multiplayer
    public void initializeMultiplayer() {
        initializePlayers(HumanPlayer.createHumanPlayer('X', sc), HumanPlayer.createHumanPlayer('O', sc));
    }

    // Create objects for AI game
    public void initializeHumanVsAI(GameBoard gameBoard) {
        initializePlayers(HumanPlayer.createHumanPlayer('X', sc), new MinMaxAIPlayer("T.U.C.", 'O', gameBoard));
    }

    public void initializeComputerBattle(GameBoard gameBoard) {
        initializePlayers(new ComputerPlayer("Computer", 'X'), new MinMaxAIPlayer("T.U.C.", 'O', gameBoard));
    }
    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public void displayTotalWins() {
        System.out.println("Current score: ");
        System.out.println(player1.getPlayerName() + ": " + player1.getTotalWins() + " wins.");
        System.out.println(player2.getPlayerName() + ": " + player2.getTotalWins() + " wins.");
    }

    public void displayRules() {
        System.out.println("""
                Rules:
                               
                The game is played on a grid that's 3x3 (or larger if chosen).
                Player 1 have symbol X and Player 2 have symbol O.
                Players take turns putting their symbols on the grid.
                The first player to get 3 (or the board size) of their symbols in a row (up, down, across, or diagonally) wins.
                If all the cells on the grid are filled and no player has their symbols in a row, then the game is a tie.
                """);
    }

    public boolean wantsToPlayAgain() {
        System.out.println("Do you want to play again? (y/n)");
        System.out.print("> ");
        String playAgain = sc.nextLine().trim();
        return playAgain.equalsIgnoreCase(YES);
    }

    public void resetGameForNewRound(){
        if (startingPlayer == player1) {
            startingPlayer = player2;
        } else {
            startingPlayer = player1;
        }
        currentPlayer = startingPlayer;
        gameBoard.resetGameBoard();
    }

}

