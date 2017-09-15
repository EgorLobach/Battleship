package model;

import java.awt.*;

/**
 * Created by anonymous on 15.09.2017.
 */
class Shot {
    private int x, y;

    Shot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void paint(Graphics g, int cellSize){
        g.setColor(Color.gray);
        g.fillRect(x*cellSize + cellSize/2 - 3, y*cellSize + cellSize/2 - 3, 8, 8);
    }
}
