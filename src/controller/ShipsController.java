package controller;

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

    public void newGame(int fieldSize, int cellSizeFirstPlayer, int cellSizeSecondPlayer) {
        this.firstPlayerShips = new Ships(fieldSize, cellSizeFirstPlayer, false);
        this.secondPlayerShips = new Ships(fieldSize, cellSizeSecondPlayer, true);
    }

    public void firstPlayerShipsPaint(Graphics g) {
        firstPlayerShips.paint(g);
    }

    public void secondPlayerShipsPaint(Graphics g) {
        secondPlayerShips.paint(g);
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

    public int isShipAliveFirstPlayer(int x, int y) {
        return firstPlayerShips.isShipAlive(x, y);
    }

    public boolean isBelongingShipFirstPlayer(int x1, int y1, int x2, int y2) {
        return firstPlayerShips.isBelongingShip(x1, y1, x2, y2);
    }

    public int isShipAliveSecondPlayer(int x, int y) {
        return secondPlayerShips.isShipAlive(x, y);
    }

    public boolean isBelongingShipSecondPlayer(int x1, int y1, int x2, int y2) {
        return secondPlayerShips.isBelongingShip(x1, y1, x2, y2);
    }
}
