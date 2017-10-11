package view;

import controller.ShipsController;
import controller.ShotsController;
import listener.ExitWindowListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anonymous on 29.09.2017.
 */
class BeginningGame {
    private JDialog beginningGame;
    private ShipsController shipsController;
    private ShotsController shotsController;
    private JLabel welcome;

    BeginningGame(ShipsController shipsController, ShotsController shotsController) {
        welcome = new JLabel("*Выберите вариант расстановки:*");
        welcome.setFont(new Font("", Font.ITALIC, 20));
        this.shipsController = shipsController;
        this.shotsController = shotsController;
        beginningGame = new JDialog();
        beginningGame.setTitle("Добро пожаловать в Морской Бой!");
        beginningGame.setResizable(false);
        beginningGame.setModal(true);
        beginningGame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    void init() {
        JButton autoCollocation = new JButton("Расставить автоматически");
        autoCollocation.setFont(new Font("", Font.PLAIN, 20));
        JButton himselfCollocation = new JButton("Расставить самостоятельно");
        himselfCollocation.setFont(new Font("", Font.PLAIN, 20));
        autoCollocation.addActionListener(e -> {
            shotsController.newGame();
            shipsController.newGame(GameFrame.FIELD_SIZE, true);
            beginningGame.dispose();
        });
        himselfCollocation.addActionListener(e -> {
            shotsController.newGame();
            shipsController.newGame(GameFrame.FIELD_SIZE, false);
            Collocation collocation = new Collocation(shipsController, shotsController);
            beginningGame.dispose();
            collocation.init();
        });
        beginningGame.addWindowListener(new ExitWindowListener());
        beginningGame.setLayout(new BorderLayout());
        beginningGame.add(welcome, BorderLayout.NORTH);
        beginningGame.add(autoCollocation, BorderLayout.CENTER);
        beginningGame.add(himselfCollocation, BorderLayout.SOUTH);
        beginningGame.pack();
        beginningGame.setLocationRelativeTo(null);
        beginningGame.setVisible(true);
    }
}
