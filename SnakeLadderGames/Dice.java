package SnakeLadderGames;

import java.util.Random;

public class Dice {
    private final int minFace;
    private final int maxFace;
    private final Random random;

    public Dice(int minFace, int maxFace) {
        this.minFace = minFace;
        this.maxFace = maxFace;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(maxFace - minFace + 1) + minFace;
    }
}