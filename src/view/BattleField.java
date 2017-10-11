package view;

import controller.ShipsController;
import controller.ShotsController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anonymous on 30.09.2017.
 */
class BattleField extends JPanel {
    private final ShipsController shipsController;
    private final ShotsController shotsController;
    private final boolean autoCollocation;

    BattleField(ShipsController shipsController, ShotsController shotsController, boolean autoCollocation) {
        this.shipsController = shipsController;
        this.shotsController = shotsController;
        this.autoCollocation = autoCollocation;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int cellSize = (int) getSize().getWidth() / GameFrame.FIELD_SIZE;
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 1; i < GameFrame.FIELD_SIZE; i++) {
            g.drawLine(0, i * cellSize, GameFrame.FIELD_SIZE * cellSize, i * cellSize);
            g.drawLine(i * cellSize, 0, i * cellSize, GameFrame.FIELD_SIZE * cellSize);
        }
        if (!autoCollocation) {
            shipsController.firstPlayerShipsPaint(g, GameFrame.COMP_CELL_SIZE);
        }
        if (cellSize == GameFrame.COMP_CELL_SIZE) {
            shotsController.firstPlayerShotsPaint(g, GameFrame.COMP_CELL_SIZE);
            shipsController.secondPlayerShipsPaint(g, GameFrame.COMP_CELL_SIZE);
        } else {
            shotsController.secondPlayerShotsPaint(g, GameFrame.PLAYER_CELL_SIZE);
            shipsController.firstPlayerShipsPaint(g, GameFrame.PLAYER_CELL_SIZE);
        }
    }
}