import java.util.Scanner;

public class TicTacToeGame {
    private GameBoard board;
    private Player player1;
    private Player player2;
    private Scanner sc;

    public TicTacToeGame(){
        board = new GameBoard();
        sc = new Scanner(System.in);
        System.out.println("Ange namn för Player 1: ");
        String player1Name = sc.nextLine();
        player1 = new Player(player1Name, 'X');

        System.out.println("Ange namn för Player 2: ");
        String player2Name = sc.nextLine();
        player2 = new Player(player2Name, 'O');
    }

    public void playTurn(Player player) {
        boolean validPlacement = false;
        while (!validPlacement) {
            System.out.println(player.getPlayerName() + " enter your placement: 1-9");
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

    public void multiPlayer() {
        boolean gameIsRunning = true;

        while (gameIsRunning) {
            playTurn(player1);
            System.out.println(board);

            if (board.isBoardFull()) {
                System.out.println("The game is a tie!");
                gameIsRunning = false;
                continue;
            }

            playTurn(player2);
            System.out.println(board);

            if (board.isBoardFull()) {
                System.out.println("The game is a tie!");
                gameIsRunning = false;
            }
        }
    }

    public boolean checkTie(){
        return board.isBoardFull();
    }

}
