package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 15.09.2017.
 */
public class Shots {
    private List<Shot> shots;

    public Shots() {
        shots = new ArrayList<>();
    }

    public void add(int x, int y) {
        shots.add(new Shot(x, y));
    }

    public boolean hitSamePlace(int x, int y) {
        for (Shot shot : shots)
            if (shot.getX() == x && shot.getY() == y)
                return true;
        return false;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void kill(int x, int y) {
        for (int dx = -1; dx < 2; dx++)
            for (int dy = -1; dy < 2; dy++)
                if (!(dx == 0 && dy == 0) || !hitSamePlace(x, y))
                    shots.add(new Shot(x + dx, y + dy));
    }
}
