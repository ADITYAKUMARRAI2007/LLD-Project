package SnakeLadderGames;

import java.util.List;

public class DifficultTurnStrategy implements TurnStrategy {

    @Override
    public TurnDecision evaluateTurn(List<Integer> rolls) {
        int size = rolls.size();

        if (size >= 3 &&
                rolls.get(size - 1) == 6 &&
                rolls.get(size - 2) == 6 &&
                rolls.get(size - 3) == 6) {
            return new TurnDecision(false, true);
        }

        int lastRoll = rolls.get(size - 1);
        if (lastRoll == 6) {
            return new TurnDecision(true, false);
        }

        return new TurnDecision(false, false);
    }
}