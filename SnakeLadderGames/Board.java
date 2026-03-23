package SnakeLadderGames;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Jump> jumps;

    public Board(int size) {
        this.size = size;
        this.jumps = new HashMap<>();
    }

    public void addJump(Jump jump) {
        jumps.put(jump.getStart(), jump);
    }

    public int resolvePosition(int pos) {
        if (jumps.containsKey(pos)) {
            return jumps.get(pos).getEnd();
        }
        return pos;
    }

    public int getSize() {
        return size;
    }

    
}