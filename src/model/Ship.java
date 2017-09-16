package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by anonymous on 13.09.2017.
 */
public class Ship {
    private ArrayList<Cell> cells = new ArrayList<>();
    private int position;
    public static final int VERTICALLY = 1;

    Ship(int x, int y, int length, int position) {
        this.position = position;
        for (int i = 0; i < length; i++) {
            cells.add(new Cell(x + i * ((this.position == VERTICALLY) ? 0 : 1), y + i * ((this.position == VERTICALLY) ? 1 : 0)));
        }
    }

    boolean isOutOfField(int top) {
        for (Cell cell : cells)
            if (cell.getX() < 0 || cell.getX() > top || cell.getY() < 0 || cell.getY() > top)
                return true;
        return false;
    }

    boolean isOverlayOrTouchShip(Ship ctrlShip) {
        for (Cell cell : cells)
            if (ctrlShip.isOverlayOrTouchCell(cell))
                return true;
        return false;
    }

    private boolean isOverlayOrTouchCell(Cell ctrlCell) {
        for (Cell cell : cells)
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++)
                    if (ctrlCell.getX() == cell.getX() + dx && ctrlCell.getY() == cell.getY() + dy)
                        return true;
        return false;
    }

    boolean checkHit(int x, int y) {
        for (Cell cell : cells)
            if (cell.checkHit(x, y))
                return true;
        return false;
    }

    boolean isAlive() {
        for (Cell cell : cells)
            if (cell.isAlive())
                return true;
        return false;
    }

    void paint(Graphics g, int cellSize, boolean hide) {
        for (Cell cell : cells)
            cell.paint(g, cellSize, hide);
    }

    public int getPosition() {
        return position;
    }
    public boolean isBelonging(int x , int y){
        for (Cell cell : cells)
            if (cell.getX() == x && cell.getY() == y)
                return true;
        return false;
    }
}
