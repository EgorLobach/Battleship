package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by anonymous on 15.09.2017.
 */
public class Ships {
    private List<Ship> ships = new ArrayList<>();

    public Ships(int fieldSize) {
        Random random = new Random();
        int[] PATTERN = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int aPATTERN : PATTERN) {
            Ship ship;
            do {
                int x = random.nextInt(fieldSize);
                int y = random.nextInt(fieldSize);
                int position = random.nextInt(2);
                ship = new Ship(x, y, aPATTERN, position);
            } while (ship.isOutOfField(fieldSize - 1) || isOverlayOrTouch(ship));
            ships.add(ship);
        }
    }

    private boolean isOverlayOrTouch(Ship ctrlShip) {
        for (Ship ship : ships)
            if (ship.isOverlayOrTouchShip(ctrlShip))
                return true;
        return false;
    }

    public boolean checkHit(int x, int y) {
        for (Ship ship : ships)
            if (ship.checkHit(x, y))
                return true;
        return false;
    }

    public boolean checkSurvivors() {
        for (Ship ship : ships)
            if (ship.isAlive())
                return true;
        return false;
    }

    public boolean isShipAlive(int x, int y) {
        for (Ship ship : ships)
            if (ship.isBelonging(x, y))
                return ship.isAlive();
        return true;
    }

    public boolean isBelongingShip(int x1, int y1, int x2, int y2) {
        for (Ship ship : ships)
            if (ship.isBelonging(x1, y1)&&ship.isBelonging(x2, y2))
                return true;
        return false;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
