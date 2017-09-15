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

    public void newGame(int fieldSize, int cellSizeFirstPlayer, int cellSizeSecondPlayer,
                        boolean hideFirstPlayer, boolean hideSecondPlayer) {
        this.firstPlayerShips = new Ships(fieldSize, cellSizeFirstPlayer, hideFirstPlayer);
        this.secondPlayerShips = new Ships(fieldSize, cellSizeSecondPlayer, hideSecondPlayer);
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

    public int isShipAliveSecondPlayer(int x, int y) {
        return secondPlayerShips.isShipAlive(x, y);
    }
}
