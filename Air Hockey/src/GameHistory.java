public class GameHistory {

    private String gamerTagPlayerOne;
    private String gamerTagPlayerTwo;
    protected int playerOneScore;
    protected int playerTwoScore;
    protected static String isGameFinished = "No";
    protected static String gameMode;

    public String getGamerTagPlayerOne() {
        return gamerTagPlayerOne;
    }

    public void setGamerTagPlayerOne(String gamerTagPlayerOne) {
        this.gamerTagPlayerOne = gamerTagPlayerOne;
    }

    public String getGamerTagPlayerTwo() {
        return gamerTagPlayerTwo;
    }

    public void setGamerTagPlayerTwo(String gamerTagPlayerTwo) {
        this.gamerTagPlayerTwo = gamerTagPlayerTwo;
    }
}
