package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 13.09.2017.
 */
public class Ship {
    private List<Cell> cells = new ArrayList<>();

    Ship(int x, int y, int length, int position) {
        for (int i = 0; i < length; i++) {
            cells.add(new Cell(x + i * ((position == 1) ? 0 : 1), y + i * ((position == 1) ? 1 : 0)));
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

    boolean isBelonging(int x, int y) {
        for (Cell cell : cells)
            if (cell.getX() == x && cell.getY() == y)
                return true;
        return false;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
