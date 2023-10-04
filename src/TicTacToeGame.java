import java.util.Scanner;

public class TicTacToeGame {
    private GameBoard gameBoard;
    protected Player player1;
    protected Player player2;
    protected final Scanner sc;
    private int movesMade = 0; // Add a counter to the game

    // Constructor that creates and initiates a game of TicTacToe with a game-board and two players.
    // Players are asked to enter their names and initiates them with the symbols X and O.
    public TicTacToeGame() {
        sc = new Scanner(System.in);
        gameBoard = new GameBoard();
    }

    public void setPlayersForMultiplayer(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }



    // Method that's responsible for handling the correct placements of player symbols
    public void playTurn(Player player) {
        boolean validPlacement = false;
        while (!validPlacement) {
            System.out.println(player.getPlayerName() + " enter your placement: 1-9");
            System.out.print("> ");
            String playerPosition = sc.nextLine();


            switch (playerPosition) {
                case "1" -> validPlacement = gameBoard.placePlayerSymbol(0, 0, player.getTicTacSymbol());
                case "2" -> validPlacement = gameBoard.placePlayerSymbol(0, 4, player.getTicTacSymbol());
                case "3" -> validPlacement = gameBoard.placePlayerSymbol(0, 8, player.getTicTacSymbol());
                case "4" -> validPlacement = gameBoard.placePlayerSymbol(2, 0, player.getTicTacSymbol());
                case "5" -> validPlacement = gameBoard.placePlayerSymbol(2, 4, player.getTicTacSymbol());
                case "6" -> validPlacement = gameBoard.placePlayerSymbol(2, 8, player.getTicTacSymbol());
                case "7" -> validPlacement = gameBoard.placePlayerSymbol(4, 0, player.getTicTacSymbol());
                case "8" -> validPlacement = gameBoard.placePlayerSymbol(4, 4, player.getTicTacSymbol());
                case "9" -> validPlacement = gameBoard.placePlayerSymbol(4, 8, player.getTicTacSymbol());
                default -> System.out.println("Invalid input, try again");
            }
        }
    }

    public void startGame(){
        String playAgain;
        do {
            gameLoop();
            System.out.println(player1.getPlayerName() + " have " + player1.getTotalWins() + " total wins.");
            System.out.println(player2.getPlayerName() + " have " + player2.getTotalWins() + " total wins.");

            System.out.println("Do you want to play again? (y/n)");
            playAgain = sc.nextLine().trim();
            if (playAgain.equalsIgnoreCase("y")){
                gameBoard.resetGameBoard();
            }
        } while(playAgain.equalsIgnoreCase("y"));

    }

    public void gameLoop() {
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            playTurn(player1);
            System.out.println(this);

            if (gameBoard.winningCondition(player1.getTicTacSymbol())){
                System.out.println(player1.getPlayerName() + " is the winner!");
                player1.incrementWins();
                return; // Exit the method (Ending the game)
            }

            if (gameBoard.isBoardFull()) {
                System.out.println("The game is a tie!");
                gameIsRunning = false;
                continue;
            }

            playTurn(player2);
            System.out.println(this);

            if (gameBoard.winningCondition(player2.getTicTacSymbol())){
                System.out.println(player2.getPlayerName() + " is the winner!");
                player2.incrementWins();
                return; // Exit the method (Ending the game)
            }

            if (gameBoard.isBoardFull()) {
                System.out.println("The game is a tie!");
                gameIsRunning = false;
            }
        }
    }

    // Return true if board is full otherwise it returns false
    public boolean checkTie() {
        return gameBoard.isBoardFull();
    }
//    Metod innan intellij ville göra om den då man kunde förenkla uttrycket av ett logiskt uttryck.
//    if (board.isBoardFull()) {
//        return true;
//    } else {
//        return false;
//    }

    @Override
    public String toString() {
        return gameBoard.toString();
    }

}
