package SnakeLadderGames;

public class Ladder extends Jump {

    public Ladder(int start, int end) {
        super(start, end);
        if (end <= start) {
            throw new IllegalArgumentException("Ladder end must be greater than start.");
        }
    }
}