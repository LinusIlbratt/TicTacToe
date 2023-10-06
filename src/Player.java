public abstract class Player {
    private String playerName;
    private final char gameSymbol;
    private int totalWins;

    public Player(String playerName, char gameSymbol) {
        this.playerName = playerName;
        this.gameSymbol = gameSymbol;
        this.totalWins = 0;
    }

    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public char getGameSymbol(){
        return gameSymbol;
    }

    public int getTotalWins(){
        return totalWins;
    }

    public void incrementWins() {
        this.totalWins++;
    }

    public abstract int[] makeMove(GameBoard gameBoard); // Abstract method. All subclasses must have this method

    protected String coordinatesToString(int row, int col) {
        char columnChar = (char) ('A' + col);
        return columnChar + String.valueOf(row + 1);
    }

}
