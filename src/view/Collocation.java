package view;

import controller.ShipsController;
import controller.ShotsController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anonymous on 30.09.2017.
 */
class Collocation {
    private JDialog collocation;
    private ShipsController shipsController;
    private ShotsController shotsController;

    Collocation(ShipsController shipsController, ShotsController shotsController) {
        this.shipsController = shipsController;
        this.shotsController = shotsController;
        collocation = new JDialog();
        collocation.setTitle("Раставьте корабли");
        collocation.setResizable(false);
        collocation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        collocation.setModal(true);
    }

    void init() {
        collocation.setLayout(new BorderLayout());
        JButton readyButton = new JButton("Готово");

        collocation.add(readyButton, BorderLayout.NORTH);

        collocation.pack();
        collocation.setLocationRelativeTo(null);
        collocation.setVisible(true);
    }
}
