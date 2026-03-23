package SnakeLadderGames;

import java.util.List;

public class TurnResult {
    private final String playerName;
    private final List<Integer> rolls;
    private final int initialPosition;
    private final int finalPosition;
    private final boolean turnLost;
    private final boolean won;

    public TurnResult(String playerName, List<Integer> rolls, int initialPosition, int finalPosition, boolean turnLost, boolean won) {
        this.playerName = playerName;
        this.rolls = rolls;
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
        this.turnLost = turnLost;
        this.won = won;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public boolean isTurnLost() {
        return turnLost;
    }

    public boolean isWon() {
        return won;
    }

    @Override
    public String toString() {
        return "TurnResult{" +
                "playerName='" + playerName + '\'' +
                ", rolls=" + rolls +
                ", initialPosition=" + initialPosition +
                ", finalPosition=" + finalPosition +
                ", turnLost=" + turnLost +
                ", won=" + won +
                '}';
    }
}