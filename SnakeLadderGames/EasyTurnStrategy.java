package SnakeLadderGames;

import java.util.List;

public class EasyTurnStrategy implements TurnStrategy {

    @Override
    public TurnDecision evaluateTurn(List<Integer> rolls) {
        int lastRoll = rolls.get(rolls.size() - 1);

        if (lastRoll == 6) {
            return new TurnDecision(true, false);
        }

        return new TurnDecision(false, false);
    }
}