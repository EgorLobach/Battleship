package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 11.10.2017.
 */
public class Pattern {
    private List<Integer> pattern = new ArrayList<>();

    public Pattern() {
        int[] allPattern = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int aPattern : allPattern)
            pattern.add(aPattern);
    }

    public int get(int i) {
        return pattern.get(i);
    }

    List<Integer> getPattern() {
        return pattern;
    }

    public int size() {
        return pattern.size();
    }
}
