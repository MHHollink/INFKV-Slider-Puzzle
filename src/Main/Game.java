package Main;


import javax.swing.*;
import java.awt.*;

/**
 * This Class was created by marcel on 26-11-2014
 * Time of creation : 10:51
 */
public class Game {

    private static JFrame window;
    public static final String TITLE = "Foto Slide Puzzle";

    public static void main(String[] args) {

        window = new JFrame(TITLE);

        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();

        window.setLocation(getCenter());

        window.setVisible(true);

    }

    private static Point getCenter(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);

        return new Point(middle.x - (window.getWidth() / 2),
                middle.y - (window.getHeight() / 2));
    }

}
