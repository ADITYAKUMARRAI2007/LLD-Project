package SnakeLadderGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SnakeAndLadderGameFactory {

    public SnakeAndLadderGame createGame(GameConfig config) {
        validateConfig(config);

        int boardDimension = config.getBoardDimension();
        int totalCells = boardDimension * boardDimension;

        Board board = new Board(totalCells);
        Dice dice = new Dice(1, 6);

        TurnStrategy turnStrategy;
        if (config.getVariant() == GameVariant.EASY) {
            turnStrategy = new EasyTurnStrategy();
        } else {
            turnStrategy = new DifficultTurnStrategy();
        }

        JumpPlacementService placementService = new JumpPlacementService();
        List<Snake> snakes;
        List<Ladder> ladders;

        do {
            Set<Integer> occupiedStarts = JumpPlacementService.newOccupiedSet();

            snakes = placementService.generateSnakes(
                boardDimension,
                config.getSnakeCount(),
                occupiedStarts
            );

            ladders = placementService.generateLadders(
                boardDimension,
                config.getLadderCount(),
                occupiedStarts
            );
        } while (createsCycle(snakes, ladders));

        for (Snake snake : snakes) {
            board.addJump(snake);
        }

        for (Ladder ladder : ladders) {
            board.addJump(ladder);
        }

        List<Player> players = createPlayers(config.getPlayerCount());

        return new SnakeAndLadderGame(board, players, dice, turnStrategy);
    }

    private List<Player> createPlayers(int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            players.add(new Player(i, "Player-" + i));
        }
        return players;
    }

    private boolean createsCycle(List<Snake> snakes, List<Ladder> ladders) {
        Map<Integer, Integer> edges = new HashMap<>();

        for (Snake snake : snakes) {
            edges.put(snake.getStart(), snake.getEnd());
        }
        for (Ladder ladder : ladders) {
            edges.put(ladder.getStart(), ladder.getEnd());
        }

        for (Integer node : edges.keySet()) {
            if (hasCycleFrom(node, edges)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleFrom(Integer start, Map<Integer, Integer> edges) {
        Set<Integer> seen = new HashSet<>();
        Integer current = start;

        while (current != null && edges.containsKey(current)) {
            if (!seen.add(current)) {
                return true;
            }
            current = edges.get(current);
        }
        return false;
    }

    private void validateConfig(GameConfig config) {
        if (config.getBoardDimension() < 2) {
            throw new IllegalArgumentException("Board dimension must be at least 2.");
        }
        if (config.getSnakeCount() < 0 || config.getLadderCount() < 0) {
            throw new IllegalArgumentException("Snake/Ladder count cannot be negative.");
        }
        if (config.getPlayerCount() < 2) {
            throw new IllegalArgumentException("At least two players are required.");
        }
    }
}