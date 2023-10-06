import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random random = new Random();

    public ComputerPlayer(String playerName, char gameSymbol) {
        super(playerName, gameSymbol);
    }


    public int[] makeMove(GameBoard gameBoard) {
        int boardSize = gameBoard.getBoardSize();
        int row, col;

        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
        } while (!gameBoard.isEmpty(row, col));

        gameBoard.placeMove(row, col, getGameSymbol());
        System.out.println(getPlayerName() + " has chosen " + coordinatesToString(row, col));
        return new int[]{row, col};
    }

}
