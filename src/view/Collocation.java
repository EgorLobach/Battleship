package view;

import controller.ShipsController;
import controller.ShotsController;
import listener.ExitWindowListener;
import model.Pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static view.GameFrame.COMP_CELL_SIZE;

/**
 * Created by anonymous on 30.09.2017.
 */
class Collocation {
    private static final int VERTICALLY = 1;
    private static final int HORIZONTALLY = 0;
    private JDialog collocation;
    private ShipsController shipsController;
    private ShotsController shotsController;
    private boolean allCollocation = false;
    private int position = VERTICALLY;
    private int length = 0;
    private int number = 0;

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
            if (!allCollocation) {
                JOptionPane.showMessageDialog(collocation, "Не все корабли расставлены!");
            } else collocation.dispose();
        });
        collocation.add(readyButton, BorderLayout.SOUTH);

        BattleField collocationField = new BattleField(shipsController, shotsController, false);
        collocationField.setPreferredSize(new Dimension(GameFrame.COMP_PANEL_SIZE, GameFrame.COMP_PANEL_SIZE));

        Pattern pattern = new Pattern();
        collocationField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() / COMP_CELL_SIZE;
                int y = e.getY() / COMP_CELL_SIZE;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (number == pattern.size())
                        allCollocation = true;
                    else {
                        length = pattern.get(number);
                        shipsController.addShip(x, y, length, position);
                        collocationField.repaint();
                        number++;
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3)
                    position = ((position == VERTICALLY) ? HORIZONTALLY : VERTICALLY);
            }
        });


        collocation.add(collocationField, BorderLayout.NORTH);
        collocation.addWindowListener(new ExitWindowListener());
        collocation.pack();
        collocation.setLocationRelativeTo(null);
        collocation.setVisible(true);
    }
}
