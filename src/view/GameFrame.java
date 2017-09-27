package view;

import controller.ShipsController;
import controller.ShotsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static model.Ships.NOT_ALIVE;

/**
 * Created by anonymous on 15.09.2017.
 */
public class GameFrame {
    private static final int FIELD_SIZE = 10;
    private final int COMP_PANEL_SIZE = 400;
    private final int COMP_CELL_SIZE = COMP_PANEL_SIZE / FIELD_SIZE;
    private final int PLAYER_PANEL_SIZE = COMP_PANEL_SIZE / 2;
    private final int PLAYER_CELL_SIZE = PLAYER_PANEL_SIZE / FIELD_SIZE;


    private ShipsController shipsController;
    private ShotsController shotsController;
    private BattleField playerBattleField, compBattleField;
    private boolean gameOver;
    private Random randomGenerator;
    private JFrame headFrame = new JFrame();

    public GameFrame(ShipsController shipsController, ShotsController shotsController) {
        this.shipsController = shipsController;
        this.shotsController = shotsController;
        headFrame.setTitle("Battle Ship");
        headFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        headFrame.setResizable(false);
    }

    public void initGameFrame() {
        compBattleField = new BattleField();
        compBattleField.setPreferredSize(new Dimension(COMP_PANEL_SIZE, COMP_PANEL_SIZE));
        compBattleField.setBackground(Color.WHITE);
        compBattleField.setBorder(BorderFactory.createLineBorder(Color.blue));
        compBattleField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() / COMP_CELL_SIZE;
                int y = e.getY() / COMP_CELL_SIZE;
                if (e.getButton() == MouseEvent.BUTTON1 && !gameOver) {
                    if (!shotsController.firstPlayerHitSamePlace(x, y)) {
                        shotsController.addFirstPlayerShots(x, y);
                        if (shipsController.checkHitSecondPlayer(x, y)) {
                            if (shipsController.isShipAliveSecondPlayer(x, y) == NOT_ALIVE) {
                                for (int dx = -3; dx < 4; dx++)
                                    for (int dy = -3; dy < 4; dy++)
                                        if (shipsController.isBelongingShipSecondPlayer(x, y, x+dx, y+dy))
                                            shotsController.killShipSecondPlayer(x+dx, y+dy);
                            }
                            if (!shipsController.checkSurvivorsSecondPlayer()) {
                                JOptionPane.showMessageDialog(headFrame, "YOU WON");
                                gameOver = true;
                            }
                        } else shootsComp();
                        compBattleField.repaint();
                        playerBattleField.repaint();
                    }
                }
            }
        });
        playerBattleField = new BattleField();
        playerBattleField.setPreferredSize(new Dimension(PLAYER_PANEL_SIZE, PLAYER_PANEL_SIZE));
        playerBattleField.setBackground(Color.WHITE);
        playerBattleField.setBorder(BorderFactory.createLineBorder(Color.blue));

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            newGame();
            compBattleField.repaint();
            playerBattleField.repaint();
        });

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(playerBattleField, BorderLayout.NORTH);
        rightPanel.add(newGameButton, BorderLayout.CENTER);

        headFrame.setLayout(new BoxLayout(headFrame.getContentPane(), BoxLayout.X_AXIS));
        headFrame.add(compBattleField);
        headFrame.add(rightPanel);
        headFrame.pack();
        headFrame.setLocationRelativeTo(null);
        headFrame.setVisible(true);
        newGame();
    }

    private void newGame() {
        shotsController.newGame(PLAYER_CELL_SIZE, COMP_CELL_SIZE);
        shipsController.newGame(FIELD_SIZE, PLAYER_CELL_SIZE, COMP_CELL_SIZE);
        gameOver = false;
        randomGenerator = new Random();
    }

    private void shootsComp() {
        int x, y;
        do {
            x = randomGenerator.nextInt(FIELD_SIZE);
            y = randomGenerator.nextInt(FIELD_SIZE);
        } while (shotsController.secondPlayerHitSamePlace(x, y));
        shotsController.addSecondPlayerShots(x, y);
        if (shipsController.checkHitFirstPlayer(x, y)) {
            if (shipsController.isShipAliveFirstPlayer(x, y) == NOT_ALIVE) {
                for (int dx = -3; dx < 4; dx++)
                    for (int dy = -3; dy < 4; dy++)
                        if (shipsController.isBelongingShipFirstPlayer(x, y, x+dx, y+dy))
                            shotsController.killShipFirstPlayer(x+dx, y+dy);
            }
            if (!shipsController.checkSurvivorsFirstPlayer()) {
                JOptionPane.showMessageDialog(headFrame, "COMP WON");
                gameOver = true;
            } else shootsComp();
        }
    }

    class BattleField extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int cellSize = (int) getSize().getWidth() / FIELD_SIZE;
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 1; i < FIELD_SIZE; i++) {
                g.drawLine(0, i * cellSize, FIELD_SIZE * cellSize, i * cellSize);
                g.drawLine(i * cellSize, 0, i * cellSize, FIELD_SIZE * cellSize);
            }
            if (cellSize == COMP_CELL_SIZE) {
                shotsController.firstPlayerShotsPaint(g);
                shipsController.secondPlayerShipsPaint(g);
            } else {
                shotsController.secondPlayerShotsPaint(g);
                shipsController.firstPlayerShipsPaint(g);
            }
        }
    }
}
