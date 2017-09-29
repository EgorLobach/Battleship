package controller;

import model.Shot;
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

    public void newGame() {
        this.firstPlayerShots = new Shots();
        this.secondPlayerShots = new Shots();
    }

    public void firstPlayerShotsPaint(Graphics g, int cellSize)
    {
        for (Shot shot : firstPlayerShots.getShots())
        {
            g.setColor(Color.gray);
            g.fillRect(shot.getX()*cellSize + cellSize/2 - 3, shot.getY()*cellSize + cellSize/2 - 3, 8, 8);
        }
    }

    public void secondPlayerShotsPaint(Graphics g, int cellSize) {

        for (Shot shot : secondPlayerShots.getShots())
        {
            g.setColor(Color.gray);
            g.fillRect(shot.getX()*cellSize + cellSize/2 - 3, shot.getY()*cellSize + cellSize/2 - 3, 8, 8);
        }
    }

    public boolean firstPlayerHitSamePlace(int x, int y) {
        return firstPlayerShots.hitSamePlace(x, y);
    }

    public boolean secondPlayerHitSamePlace(int x, int y) {
        return secondPlayerShots.hitSamePlace(x, y);
    }

    public void addFirstPlayerShots(int x, int y) {
        firstPlayerShots.add(x, y);
    }

    public void addSecondPlayerShots(int x, int y) {
        secondPlayerShots.add(x, y);
    }

    public void killShipSecondPlayer(int x, int y) {
        firstPlayerShots.kill(x, y);
    }

    public void killShipFirstPlayer(int x, int y) {
        secondPlayerShots.kill(x, y);
    }

}
