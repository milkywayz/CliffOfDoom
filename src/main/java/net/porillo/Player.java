package net.porillo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Player {

    private int x, y;
    private int moves, totalMoves;
    private boolean alive;

    Player(int x, int y) {
        this(x, y, 0);
    }

    Player(int x, int y, int totalMoves) {
        this(x, y, 0, totalMoves, true);
    }

    void move(int x, int y) {
        if (!alive) {
            return;
        }

        this.x += x;
        this.y += y;

        this.moves++;
        this.totalMoves++;
    }
}
