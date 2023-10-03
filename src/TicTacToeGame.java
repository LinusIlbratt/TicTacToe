import java.util.Scanner;

public class TicTacToeGame {
    private final GameBoard board;
    private final Player player1;
    private final Player player2;
    private final Scanner sc;

    public TicTacToeGame() {
        board = new GameBoard();
        sc = new Scanner(System.in);
        System.out.println("Ange namn för Player 1: ");
        System.out.print("> ");
        String player1Name = sc.nextLine();
        player1 = new Player(player1Name, 'X');

        System.out.println("Ange namn för Player 2: ");
        System.out.print("> ");
        String player2Name = sc.nextLine();
        player2 = new Player(player2Name, 'O');
    }

    public void playTurn(Player player) {
        boolean validPlacement = false;
        while (!validPlacement) {
            System.out.println(player.getPlayerName() + " enter your placement: 1-9");
            System.out.print("> ");
            String playerPosition = sc.nextLine();


            switch (playerPosition) {
                case "1" -> validPlacement = board.placePlayerSymbol(0, 0, player.getTicTacSymbol());
                case "2" -> validPlacement = board.placePlayerSymbol(0, 4, player.getTicTacSymbol());
                case "3" -> validPlacement = board.placePlayerSymbol(0, 8, player.getTicTacSymbol());
                case "4" -> validPlacement = board.placePlayerSymbol(2, 0, player.getTicTacSymbol());
                case "5" -> validPlacement = board.placePlayerSymbol(2, 4, player.getTicTacSymbol());
                case "6" -> validPlacement = board.placePlayerSymbol(2, 8, player.getTicTacSymbol());
                case "7" -> validPlacement = board.placePlayerSymbol(4, 0, player.getTicTacSymbol());
                case "8" -> validPlacement = board.placePlayerSymbol(4, 4, player.getTicTacSymbol());
                case "9" -> validPlacement = board.placePlayerSymbol(4, 8, player.getTicTacSymbol());
                default -> System.out.println("Invalid input, try again");
            }
        }
    }

    public void startGame(){
        String playAgain;
        do {
            multiPlayer();
            System.out.println(player1.getPlayerName() + " have " + player1.getTotalWins() + " total wins.");
            System.out.println(player2.getPlayerName() + " have " + player2.getTotalWins() + " total wins.");
            System.out.println("Do you want to play again? (y/n)");
            playAgain = sc.nextLine().trim();
            if (playAgain.equalsIgnoreCase("y")){
            board.resetGameBoard();
            }
        } while(playAgain.equalsIgnoreCase("y"));
    }
    public void multiPlayer() {
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            playTurn(player1);
            System.out.println(board);

            if (board.winningCondition(player1.getTicTacSymbol())){
                System.out.println(player1.getPlayerName() + " is the winner!");
                player1.incrementWins();
                return; // Exit the method (Ending the game)
            }

            if (board.isBoardFull()) {
                System.out.println("The game is a tie!");
                gameIsRunning = false;
                continue;
            }

            playTurn(player2);
            System.out.println(board);

            if (board.winningCondition(player2.getTicTacSymbol())){
                System.out.println(player2.getPlayerName() + " is the winner!");
                player2.incrementWins();
                return; // Exit the method (Ending the game)
            }

            if (board.isBoardFull()) {
                System.out.println("The game is a tie!");
                gameIsRunning = false;
            }
        }
    }

    // Return true if board is full otherwise it returns false
    public boolean checkTie() {
        return board.isBoardFull();
    }
//    Metod innan intellij ville göra om den då man kunde förenkla uttrycket av ett logiskt uttryck.
//    if (board.isBoardFull()) {
//        return true;
//    } else {
//        return false;
//    }

}
