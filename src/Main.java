import controller.ShipsController;
import controller.ShotsController;
import model.Ships;
import model.Shots;
import view.GameFrame;

import javax.swing.*;

/**
 * Created by anonymous on 13.09.2017.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShipsController shipsController = new ShipsController (null, null);
                ShotsController shotsController = new ShotsController (null, null);
                GameFrame gameFrame = new GameFrame(shipsController, shotsController);
                gameFrame.initGameFrame();
            }
        });
    }
}
