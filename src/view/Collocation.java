package view;

import controller.ShipsController;
import controller.ShotsController;
import listener.ExitWindowListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by anonymous on 30.09.2017.
 */
class Collocation {
    private JDialog collocation;
    private ShipsController shipsController;
    private ShotsController shotsController;
    private boolean allCollocation = false;

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
        readyButton.addActionListener(e -> {
            if(!allCollocation){
                JOptionPane.showMessageDialog(collocation, "Не все корабли расставлены!");
            }
            else collocation.dispose();
        });
        collocation.add(readyButton, BorderLayout.SOUTH);

        CollocationField collocationField = new CollocationField();
        collocationField.setPreferredSize(new Dimension(GameFrame.COMP_PANEL_SIZE, GameFrame.COMP_PANEL_SIZE));
        collocation.add(collocationField, BorderLayout.NORTH);

        collocation.addWindowListener(new ExitWindowListener());
        collocation.pack();
        collocation.setLocationRelativeTo(null);
        collocation.setVisible(true);
    }
}
