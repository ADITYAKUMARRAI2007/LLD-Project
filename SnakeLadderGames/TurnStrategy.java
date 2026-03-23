package SnakeLadderGames;
import java.util.List;

public interface TurnStrategy {
    TurnDecision evaluateTurn(List<Integer> rolls);
}