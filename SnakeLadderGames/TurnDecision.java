package SnakeLadderGames;

public class TurnDecision {
    private final boolean continueTurn;
    private final boolean cancelTurn;

    public TurnDecision(boolean continueTurn, boolean cancelTurn) {
        this.continueTurn = continueTurn;
        this.cancelTurn = cancelTurn;
    }

    public boolean shouldContinueTurn() {
        return continueTurn;
    }

    public boolean shouldCancelTurn() {
        return cancelTurn;
    }
}