package SnakeLadderGames;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter board dimension N (board will be N x N): ");
            int boardDimension = scanner.nextInt();

            System.out.print("Enter number of players: ");
            int playerCount = scanner.nextInt();

            System.out.print("Enter difficulty level (easy/hard): ");
            String difficulty = scanner.next().trim().toLowerCase();
            GameVariant variant;
            if ("easy".equals(difficulty)) {
                variant = GameVariant.EASY;
            } else if ("hard".equals(difficulty)) {
                variant = GameVariant.DIFFICULT;
            } else {
                throw new IllegalArgumentException("Difficulty must be either easy or hard.");
            }

            GameConfig config = new GameConfig(
                    boardDimension,
                    boardDimension,
                    boardDimension,
                    playerCount,
                    variant
            );

            SnakeAndLadderGameFactory factory = new SnakeAndLadderGameFactory();
            SnakeAndLadderGame game = factory.createGame(config);

            game.startGame();

            while (!game.isFinished()) {
                TurnResult result = game.makeTurn();
                System.out.println(result);
            }

            System.out.println("Winner is: " + game.getWinner().getName());
        }
    }
}
