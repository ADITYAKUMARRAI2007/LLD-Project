package SnakeLadderGames;

public class Snake extends Jump {

    public Snake(int start, int end) {
        super(start, end);
        if (start <= end) {
            throw new IllegalArgumentException("Snake start must be greater than end.");
        }
    }
}