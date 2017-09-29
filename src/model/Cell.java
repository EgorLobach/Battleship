package model;

import java.awt.*;

/**
 * Created by anonymous on 13.09.2017.
 */
public class Cell {
    private int x, y;
    private boolean alive;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    boolean checkHit(int x, int y) {
        if (this.x == x && this.y == y) {
            alive = false;
            return true;
        }
        return false;
    }

    public boolean isAlive() {
        return alive;
    }
}
