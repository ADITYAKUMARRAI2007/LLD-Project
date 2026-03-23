package SnakeLadderGames;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class JumpPlacementService {
    private final Random random = new Random();

    public List<Snake> generateSnakes(int boardDimension, int count, Set<Integer> occupiedStarts) {
        int totalCells = boardDimension * boardDimension;
        List<Snake> snakes = new ArrayList<>();

        while (snakes.size() < count) {
            int start = randomCell(2, totalCells - 1);
            int end = randomCell(1, totalCells - 1);

            if (!isValidSnake(boardDimension, start, end, occupiedStarts)) {
                continue;
            }

            snakes.add(new Snake(start, end));
            occupiedStarts.add(start);
        }

        return snakes;
    }

    public List<Ladder> generateLadders(int boardDimension, int count, Set<Integer> occupiedStarts) {
        int totalCells = boardDimension * boardDimension;
        List<Ladder> ladders = new ArrayList<>();

        while (ladders.size() < count) {
            int start = randomCell(2, totalCells - 1);
            int end = randomCell(2, totalCells);

            if (!isValidLadder(boardDimension, start, end, occupiedStarts)) {
                continue;
            }

            ladders.add(new Ladder(start, end));
            occupiedStarts.add(start);
        }

        return ladders;
    }

    private int randomCell(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private boolean isValidSnake(int boardDimension, int start, int end, Set<Integer> occupiedStarts) {
        if (occupiedStarts.contains(start)) return false;
        if (start <= end) return false;
        return !sameRow(boardDimension, start, end);
    }

    private boolean isValidLadder(int boardDimension, int start, int end, Set<Integer> occupiedStarts) {
        if (occupiedStarts.contains(start)) return false;
        if (end <= start) return false;
        return !sameRow(boardDimension, start, end);
    }

    private boolean sameRow(int boardDimension, int a, int b) {
        int rowA = (a - 1) / boardDimension;
        int rowB = (b - 1) / boardDimension;
        return rowA == rowB;
    }

    public static Set<Integer> newOccupiedSet() {
        return new HashSet<>();
    }
}