package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by anonymous on 15.09.2017.
 */
public class Shots {
    private final int CELL_SIZE;
    private ArrayList<Shot> shots;

    public Shots(int cellSize){
        CELL_SIZE = cellSize;
        shots = new ArrayList<>();
    }
    public void add(int x, int y){
        shots.add(new Shot(x, y));
    }
    public boolean hitSamePlace(int x, int y){
        for (Shot shot : shots)
            if (shot.getX() == x && shot.getY() == y)
                return true;
        return false;
    }
    public void paint (Graphics g){
        for (Shot shot : shots)
            shot.paint(g, CELL_SIZE);
    }
}
