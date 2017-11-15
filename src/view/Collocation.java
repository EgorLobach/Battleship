package view;

import controller.ShipsController;
import controller.ShotsController;
import listener.ExitWindowListener;
import model.Pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static view.GameFrame.*;

/**
 * Created by anonymous on 30.09.2017.
 */
class Collocation {
    private JDialog collocation;
    private ShipsController shipsController;
    private ShotsController shotsController;
    private boolean allCollocation = false;
    private int position = VERTICALLY;
    private int number = 0;

    Collocation(ShipsController shipsController, ShotsController shotsController) {
        this.shipsController = shipsController;
        this.shotsController = shotsController;
        collocation = new JDialog();
        collocation.setTitle("Раставьте корабли");
        collocation.setResizable(false);
        collocation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        collocation.setModal(true);
        collocation.setLayout(new BorderLayout());
    }

    void init() {
        Pattern pattern = new Pattern();
        BattleField collocationField = new BattleField(shipsController, shotsController, false);
        collocationField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() / COMP_CELL_SIZE;
                int y = e.getY() / COMP_CELL_SIZE;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if((!allCollocation && !shipsController.isShipHere(x, y, pattern.get(number), position))&&
                            !((position == VERTICALLY && y + pattern.get(number) > FIELD_SIZE) ||
                                (position != VERTICALLY && x + pattern.get(number) > FIELD_SIZE))) {
                        shipsController.addShip(x, y, pattern.get(number), position);
                        collocationField.repaint();
                        number++;
                        if (number == pattern.size())
                            allCollocation = true;
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3)
                    position = ((position == VERTICALLY) ? HORIZONTALLY : VERTICALLY);
            }
        });
        collocationField.setPreferredSize(new Dimension(GameFrame.COMP_PANEL_SIZE, GameFrame.COMP_PANEL_SIZE));
        JButton readyButton = new JButton("Готово");
        readyButton.addActionListener(e -> {
            if (!allCollocation) {
                JOptionPane.showMessageDialog(collocation, "Не все корабли расставлены!");
            } else collocation.dispose();
        });
        collocation.add(readyButton, BorderLayout.SOUTH);
        collocation.add(collocationField, BorderLayout.NORTH);
        collocation.addWindowListener(new ExitWindowListener());
        collocation.pack();
        collocation.setLocationRelativeTo(null);
        collocation.setVisible(true);
    }
}
