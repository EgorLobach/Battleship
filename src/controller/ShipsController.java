package controller;

import model.Cell;
import model.Ship;
import model.Ships;

import java.awt.*;

/**
 * Created by anonymous on 15.09.2017.
 */
public class ShipsController {
    private Ships firstPlayerShips, secondPlayerShips;

    public ShipsController(Ships firstPlayerShips, Ships secondPlayerShips) {
        this.firstPlayerShips = firstPlayerShips;
        this.secondPlayerShips = secondPlayerShips;
    }

    public void newGame(int fieldSize, boolean autoCollocation) {
        if (autoCollocation) {
            this.firstPlayerShips = new Ships(fieldSize, true);
            this.secondPlayerShips = new Ships(fieldSize);
        }
        else {
            this.firstPlayerShips = new Ships(fieldSize, false);
            this.secondPlayerShips = new Ships(fieldSize);
        }
    }

    public void firstPlayerShipsPaint(Graphics g, int cellSize) {
        for (Ship ship : firstPlayerShips.getShips()) {
            for (Cell cell : ship.getCells()) {
                if (cell.isAlive()){
                    g.setColor(Color.BLUE);
                }
                else g.setColor(Color.RED);
                g.fill3DRect(cell.getX() * cellSize + 1, cell.getY() * cellSize + 1,
                        cellSize - 2, cellSize - 2, true);
            }
        }
    }

    public void secondPlayerShipsPaint(Graphics g, int cellSize) {
        for (Ship ship : secondPlayerShips.getShips()) {
            for (Cell cell : ship.getCells()) {
                if (!cell.isAlive()) {
                    g.setColor(Color.RED);
                    g.fill3DRect(cell.getX() * cellSize + 1, cell.getY() * cellSize + 1,
                            cellSize - 2, cellSize - 2, true);
                }
            }
        }
    }

    public boolean checkHitFirstPlayer(int x, int y) {
        return firstPlayerShips.checkHit(x, y);
    }

    public boolean checkHitSecondPlayer(int x, int y) {
        return secondPlayerShips.checkHit(x, y);
    }

    public boolean checkSurvivorsFirstPlayer() {
        return firstPlayerShips.checkSurvivors();
    }

    public boolean checkSurvivorsSecondPlayer() {
        return secondPlayerShips.checkSurvivors();
    }

    public boolean isShipAliveFirstPlayer(int x, int y) {
        return firstPlayerShips.isShipAlive(x, y);
    }

    public boolean isShipAliveSecondPlayer(int x, int y) {
        return secondPlayerShips.isShipAlive(x, y);
    }

    public boolean isBelongingShipFirstPlayer(int x1, int y1, int x2, int y2) {
        return firstPlayerShips.isBelongingShip(x1, y1, x2, y2);
    }

    public boolean isBelongingShipSecondPlayer(int x1, int y1, int x2, int y2) {
        return secondPlayerShips.isBelongingShip(x1, y1, x2, y2);
    }

    public void addShip(int x, int y, int length, int position) {
        firstPlayerShips.addShip(x, y, length, position);
    }

    public boolean isShipHere(int x, int y, int length, int position) {
        return firstPlayerShips.isShipHere(x, y, length, position);
    }
}
