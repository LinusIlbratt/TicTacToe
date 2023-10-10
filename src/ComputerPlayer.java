import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random random = new Random();

    public ComputerPlayer(String playerName, char gameSymbol) {
        super(playerName, gameSymbol);
    }


    public void makeMove(GameBoard gameBoard) {
        int boardSize = gameBoard.getBoardSize();
        int row, col;

        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
        } while (!gameBoard.isGameBoardEmpty(row, col));

        gameBoard.placePlayerMove(row, col, getGameSymbol());
        System.out.println(getPlayerName() + " has chosen " + coordinatesToString(row, col));
    }

}
