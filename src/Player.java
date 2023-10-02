public class Player {
    private String playerName;
    private char ticTacSymbol;

    public Player(String playerName, char ticTacSymbol) {
        this.playerName = playerName;
        this.ticTacSymbol = ticTacSymbol;

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public char getTicTacSymbol() {
        return ticTacSymbol;
    }

    public void setTicTacSymbol(char ticTacSymbol) {
        this.ticTacSymbol = ticTacSymbol;
    }
}
