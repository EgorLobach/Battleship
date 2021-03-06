package view;

import controller.ShipsController;
import controller.ShotsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by anonymous on 15.09.2017.
 */
public class GameFrame {
    static final int FIELD_SIZE = 10;
    static final int COMP_PANEL_SIZE = 400;
    static final int COMP_CELL_SIZE = COMP_PANEL_SIZE / FIELD_SIZE;
    static final int PLAYER_PANEL_SIZE = COMP_PANEL_SIZE / 2;
    static final int PLAYER_CELL_SIZE = PLAYER_PANEL_SIZE / FIELD_SIZE;
    public static final int VERTICALLY = 1;
    static final int HORIZONTALLY = 0;

    private ShipsController shipsController;
    private ShotsController shotsController;
    private boolean gameOver;
    private Random randomGenerator;
    private JFrame headFrame = new JFrame();

    public GameFrame(ShipsController shipsController, ShotsController shotsController) {
        this.shipsController = shipsController;
        this.shotsController = shotsController;
        headFrame.setTitle("Морской бой");
        headFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        headFrame.setResizable(false);
    }

    public void initGameFrame() {
        BattleField playerBattleField = new BattleField(shipsController, shotsController, true);
        playerBattleField.setPreferredSize(new Dimension(PLAYER_PANEL_SIZE, PLAYER_PANEL_SIZE));
        playerBattleField.setBackground(Color.WHITE);

        BattleField compBattleField = new BattleField(shipsController, shotsController, true);
        compBattleField.setPreferredSize(new Dimension(COMP_PANEL_SIZE, COMP_PANEL_SIZE));
        compBattleField.setBackground(Color.WHITE);
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
                            if (!shipsController.isShipAliveSecondPlayer(x, y)) {
                                for (int dx = -3; dx < 4; dx++)
                                    for (int dy = -3; dy < 4; dy++)
                                        if (shipsController.isBelongingShipSecondPlayer(x, y, x + dx, y + dy))
                                            shotsController.killShipSecondPlayer(x + dx, y + dy);
                            }
                            if (!shipsController.checkSurvivorsSecondPlayer()) {
                                JOptionPane.showMessageDialog(headFrame, "Вы выйграли!!!");
                                gameOver = true;
                            }
                        } else shootsComp();
                        compBattleField.repaint();
                        playerBattleField.repaint();
                    }
                }
            }
        });

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.setFont(new Font("", Font.BOLD, 20));
        newGameButton.addActionListener(e -> {
            newGame();
            compBattleField.repaint();
            playerBattleField.repaint();
        });

        JButton instructionButton = new JButton("Правила игры");
        instructionButton.setFont(new Font("", Font.BOLD, 20));
        instructionButton.addActionListener(e ->
                JOptionPane.showMessageDialog(headFrame, "«Морской бой» — игра для двух участников,\n" +
                "в которой игроки по очереди называют координаты на неизвестной им карте соперника.\n" +
                "Если у соперника по этим координатам имеется корабль (координаты заняты),\n" +
                "то корабль или его часть «топится», а попавший получает право сделать ещё один ход.\n" +
                "Цель игрока — первым поразить все корабли противника."));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(playerBattleField, BorderLayout.NORTH);
        rightPanel.add(instructionButton, BorderLayout.CENTER);
        rightPanel.add(newGameButton, BorderLayout.SOUTH);

        headFrame.setLayout(new BoxLayout(headFrame.getContentPane(), BoxLayout.X_AXIS));
        headFrame.add(compBattleField);
        headFrame.add(rightPanel);
        headFrame.pack();
        headFrame.setLocationRelativeTo(null);
        newGame();
        headFrame.setVisible(true);
    }

    private void newGame() {
        BeginningGame beginningGame = new BeginningGame(shipsController, shotsController);
        beginningGame.init();
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
            if (!shipsController.isShipAliveFirstPlayer(x, y)) {
                for (int dx = -3; dx < 4; dx++)
                    for (int dy = -3; dy < 4; dy++)
                        if (shipsController.isBelongingShipFirstPlayer(x, y, x + dx, y + dy))
                            shotsController.killShipFirstPlayer(x + dx, y + dy);
            }
            if (!shipsController.checkSurvivorsFirstPlayer()) {
                JOptionPane.showMessageDialog(headFrame, "Компьютер выйграл!!!");
                gameOver = true;
            } else shootsComp();
        }
    }
}
