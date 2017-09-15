package controller;

import model.Shots;

import java.awt.*;

/**
 * Created by anonymous on 15.09.2017.
 */
public class ShotsController {
    private Shots firstPlayerShots, secondPlayerShots;

    public ShotsController(Shots firstPlayerShots, Shots secondPlayerShots) {
        this.firstPlayerShots = firstPlayerShots;
        this.secondPlayerShots = secondPlayerShots;
    }

    public void newGame(int cellSizeFirstPlayer, int cellSizeSecondPlayer) {
        this.firstPlayerShots = new Shots(cellSizeSecondPlayer);
        this.secondPlayerShots = new Shots(cellSizeFirstPlayer);
    }

    public void firstPlayerShotsPaint(Graphics g) {
        firstPlayerShots.paint(g);
    }

    public void secondPlayerShotsPaint(Graphics g) {
        secondPlayerShots.paint(g);
    }

    public boolean firstPlayerHitSamePlace(int x, int y) {
        return firstPlayerShots.hitSamePlace(x, y);
    }

    public boolean secondPlayerHitSamePlace(int x, int y) { return secondPlayerShots.hitSamePlace(x, y);}

    public void addFirstPlayerShots(int x, int y) {
        firstPlayerShots.add(x, y);
    }

    public void addSecondPlayerShots(int x, int y) {
        secondPlayerShots.add(x, y);
    }
}
