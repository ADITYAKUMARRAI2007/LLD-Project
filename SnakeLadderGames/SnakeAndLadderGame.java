package SnakeLadderGames;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeAndLadderGame {
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private final TurnStrategy turnStrategy;
    private GameStatus status;
    private Player winner;

    public SnakeAndLadderGame(Board board, List<Player> players, Dice dice, TurnStrategy turnStrategy) {
        this.board = board;
        this.players = new LinkedList<>(players);
        this.dice = dice;
        this.turnStrategy = turnStrategy;
        this.status = GameStatus.NOT_STARTED;
    }

    public void startGame() {
        if (players.size() < 2) {
            throw new IllegalStateException("At least 2 players are required to start the game.");
        }
        status = GameStatus.IN_PROGRESS;
    }

    public TurnResult makeTurn() {
        if (status == GameStatus.NOT_STARTED) {
            throw new IllegalStateException("Game has not started.");
        }
        if (status == GameStatus.FINISHED) {
            throw new IllegalStateException("Game is already finished.");
        }

        Player currentPlayer = players.poll();
        int initialPosition = currentPlayer.getPosition();
        List<Integer> rolls = new ArrayList<>();

        while (true) {
            int roll = dice.roll();
            rolls.add(roll);

            TurnDecision decision = turnStrategy.evaluateTurn(rolls);

            if (decision.shouldCancelTurn()) {
                players.offer(currentPlayer);
                return new TurnResult(
                        currentPlayer.getName(),
                        rolls,
                        initialPosition,
                        initialPosition,
                        true,
                        false
                );
            }

            if (!decision.shouldContinueTurn()) {
                break;
            }
        }

        int totalMove = rolls.stream().mapToInt(Integer::intValue).sum();
        int tentativePosition = initialPosition + totalMove;
        int finalPosition = initialPosition;

        if (tentativePosition <= board.getSize()) {
            finalPosition = board.resolvePosition(tentativePosition);
            currentPlayer.setPosition(finalPosition);
        }

        boolean won = finalPosition == board.getSize();

        if (won) {
            winner = currentPlayer;
            if (players.size() < 2) {
                status = GameStatus.FINISHED;
            }
        } else {
            players.offer(currentPlayer);
            if (players.size() < 2) {
                status = GameStatus.FINISHED;
            }
        }

        return new TurnResult(
                currentPlayer.getName(),
                rolls,
                initialPosition,
                finalPosition,
                false,
                won
        );
    }

    public boolean isFinished() {
        return status == GameStatus.FINISHED;
    }

    public Player getWinner() {
        return winner;
    }
}