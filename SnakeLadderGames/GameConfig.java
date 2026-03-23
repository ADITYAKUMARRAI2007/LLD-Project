package SnakeLadderGames;

public class GameConfig {
    private final int boardDimension;
    private final int snakeCount;
    private final int ladderCount;
    private final int playerCount;
    private final GameVariant variant;

    public GameConfig(int boardDimension, int snakeCount, int ladderCount, int playerCount, GameVariant variant) {
        this.boardDimension = boardDimension;
        this.snakeCount = snakeCount;
        this.ladderCount = ladderCount;
        this.playerCount = playerCount;
        this.variant = variant;
    }

    public int getBoardDimension() {
        return boardDimension;
    }

    public int getSnakeCount() {
        return snakeCount;
    }

    public int getLadderCount() {
        return ladderCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public GameVariant getVariant() {
        return variant;
    }
}