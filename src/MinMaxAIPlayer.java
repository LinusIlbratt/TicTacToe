public class MinMaxAIPlayer extends Player {
    private GameBoard currentGameBoard;


    public MinMaxAIPlayer(String playerName, char gameSymbol, GameBoard gameBoard) {
        super(playerName, gameSymbol);
        this.currentGameBoard = gameBoard;

    }

    public boolean areMovesLeft() {
        return !currentGameBoard.isGameBoardFull();
    }

    public int evaluateBoard(GameBoard board) {

        // Checking Rows for X or O victory.
        for (int row = 0; row < board.getBoardSize(); row++) {
            if (!board.isGameBoardEmpty(row, 0) &&
                    board.getCellValue(row, 0) == board.getCellValue(row, 1) &&
                    board.getCellValue(row, 1) == board.getCellValue(row, 2)) {

                return (board.getCellValue(row, 0) == super.getGameSymbol()) ? +10 : -10;
            }
        }

        // Checking Columns for X or O victory.
        for (int col = 0; col < board.getBoardSize(); col++) {
            if (!board.isGameBoardEmpty(0, col) &&
                    board.getCellValue(0, col) == board.getCellValue(1, col) &&
                    board.getCellValue(1, col) == board.getCellValue(2, col)) {

                return (board.getCellValue(0, col) == super.getGameSymbol()) ? +10 : -10;
            }
        }

        // Checking Diagonals for X or O victory.
        if (!board.isGameBoardEmpty(0, 0) &&
                board.getCellValue(0, 0) == board.getCellValue(1, 1) &&
                board.getCellValue(1, 1) == board.getCellValue(2, 2)) {

            return (board.getCellValue(0, 0) == super.getGameSymbol()) ? +10 : -10;
        }

        if (!board.isGameBoardEmpty(0, 2) &&
                board.getCellValue(0, 2) == board.getCellValue(1, 1) &&
                board.getCellValue(1, 1) == board.getCellValue(2, 0)) {

            return (board.getCellValue(0, 2) == super.getGameSymbol()) ? +10 : -10;
        }

        return 0;
    }

    public int minMaxAlgorithm(GameBoard board, int turnsAhead, boolean isMax) {
        int score = evaluateBoard(board);

        // If Maximum has won the game, return evaluated score.
        if (score == 10)
            return score - turnsAhead;

        // If Minimum has won the game, return evaluated score.
        if (score == -10)
            return score + turnsAhead;

        // No moves left and no winner, it's a tie
        if (!areMovesLeft())
            return 0;

        if (isMax) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < board.getBoardSize(); i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    if (board.isGameBoardEmpty(i, j)) {
                        board.placePlayerMove(i, j, super.getGameSymbol()); // Make the move
                        maxEval = Math.max(maxEval, minMaxAlgorithm(board, turnsAhead + 1, false));
                        board.placePlayerMove(i, j, ' '); // Undo the move
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < board.getBoardSize(); i++) {
                for (int j = 0; j < board.getBoardSize(); j++) {
                    if (board.isGameBoardEmpty(i, j)) {
                        board.placePlayerMove(i, j, 'X'); // Assuming 'X' is the opponent's symbol
                        minEval = Math.min(minEval, minMaxAlgorithm(board, turnsAhead + 1, true));
                        board.placePlayerMove(i, j, ' '); // Undo the move
                    }
                }
            }
            return minEval;
        }
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        int bestScore = Integer.MIN_VALUE;
        int bestMoveRow = -1;
        int bestMoveCol = -1;

        for (int i = 0; i < gameBoard.getBoardSize(); i++) {
            for (int j = 0; j < gameBoard.getBoardSize(); j++) {
                if (gameBoard.isGameBoardEmpty(i, j)) {
                    // Simulate making a move
                    gameBoard.placePlayerMove(i, j, super.getGameSymbol());
                    int moveScore = minMaxAlgorithm(gameBoard, 0, false); // We pass 'false' because the next move after AI's move would be the opponent's (minimizing player)
                    // Undo the move to restore the original state
                    gameBoard.placePlayerMove(i, j, ' ');

                    // Update bestScore and best move if current move's score is better
                    if (moveScore > bestScore) {
                        bestScore = moveScore;
                        bestMoveRow = i;
                        bestMoveCol = j;
                    }
                }
            }
        }
        // Make the best move that's found
        gameBoard.placePlayerMove(bestMoveRow, bestMoveCol, super.getGameSymbol());

    }
}

