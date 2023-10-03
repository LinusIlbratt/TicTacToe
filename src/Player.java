public class Player {
    private String playerName;
    private char ticTacSymbol;

    private int totalWins = 0;

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

    public void incrementWins(){
        totalWins++;
    }

    public int getTotalWins(){
        return totalWins;
    }

    public String getColorCode() {
        if (ticTacSymbol == 'X') {
            return TextColor.ANSI_RED;
        } else if (ticTacSymbol == 'O') {
            return TextColor.ANSI_BLUE;
        } else {
            return TextColor.ANSI_RESET;  // Default color
        }
    }
}
