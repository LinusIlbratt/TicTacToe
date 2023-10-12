public abstract class Player {
    private String playerName;
    private char gameSymbol;
    private int totalWins;

    // Constructs a new player with specified name, game symbol and initialize total wins to zero.
    public Player(String playerName, char gameSymbol) {
        this.playerName = playerName;
        this.gameSymbol = gameSymbol;
        this.totalWins = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public char getGameSymbol() {
        return gameSymbol;
    }

    public int getTotalWins() {
        return totalWins;
    }

    // Increments the total number of wins for the pla
    public void incrementWins() {
        this.totalWins++;
    }

    public abstract void makeMove(GameBoard gameBoard); // Abstract method. All subclasses must have this method.

}
