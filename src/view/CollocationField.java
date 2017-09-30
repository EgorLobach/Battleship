package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anonymous on 30.09.2017.
 */
public class CollocationField extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int cellSize = (int) getSize().getWidth() / GameFrame.FIELD_SIZE;
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= GameFrame.FIELD_SIZE; i++) {
            g.drawLine(0, i * cellSize, GameFrame.FIELD_SIZE * cellSize, i * cellSize);
            g.drawLine(i * cellSize, 0, i * cellSize, GameFrame.FIELD_SIZE * cellSize);
        }
    }
}
