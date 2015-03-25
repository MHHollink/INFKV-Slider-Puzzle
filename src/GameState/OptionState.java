package GameState;

import Gfx.ImageLoader;
import Main.Game;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * This Class was created by marcel on 26-11-2014
 * <p/>
 * Time of creation : 11:28
 */
public class OptionState extends GameState {

    int currentChoice = 0;
    String[] options = {
            "Puzzle",
            "Difficulty"
    };

    public static int currentPuzzle = 9;
    public static String[] puzzlePath = {
            "/puzzles/cat.png",
            "/puzzles/cat2.png",
            "/puzzles/ape.png",
            "/puzzles/bird.png",
            "/puzzles/deer.png",
            "/puzzles/horse.png",
            "/puzzles/snake.png",
            "/puzzles/elephant.png",
            "/puzzles/frog.png",
            "/puzzles/giraffe.png"
    };

    public static ImageLoader il;

    public static final int EASY = 3;
    public static final int MEDIUM = 5;
    public static final int HARD = 10;
    public static final int PRO = 12;
    public static final int MASTER = 15;
    public static final int ASIAN = 31;
    public static int difficulty = MEDIUM;

    public OptionState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        //draw Background
        g.clearRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw title
        g.setColor(MenuState.titleColor);
        g.setFont(MenuState.titleFont);

        g.drawString(Game.TITLE, getCenterForString(Game.TITLE, g), 150);
        for (int i = 0; i < 4; i++) {
            g.drawLine(
                    getCenterForString(Game.TITLE, g),
                    160+i,GamePanel.WIDTH-getCenterForString(Game.TITLE, g),
                    160+i
            );
        }

        g.setFont(MenuState.font);
        for (int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(new Color(255, 180, 100));
            }
            else {
                g.setColor(new Color(175, 74,0));
            }
            g.drawString(options[i], getCenterForString(options[i], g), 220 + i*270);

            il = new ImageLoader(puzzlePath[currentPuzzle], difficulty);
            g.drawImage(il.getImage(), GamePanel.WIDTH/2 - 100, 250, 200, 200, null );

            if(difficulty == EASY) {g.drawString("Easy (3 x 3)", getCenterForString("Easy (3 x 3)", g), 515);}
            else if(difficulty == MEDIUM) {g.drawString("Medium (5 x 5)", getCenterForString("Medium (5 x 5)", g), 515);}
            else if(difficulty == HARD) {g.drawString("Hard (10 x 10)", getCenterForString("Hard (10 x 10)", g), 515);}
            else if(difficulty == PRO) {g.drawString("Pro (12 x 12)", getCenterForString("Pro (12 x 12)", g), 515);}
            else if(difficulty == MASTER) {g.drawString("Master (15 x 15)", getCenterForString("Master (15 x 15)", g), 515);}
            else if(difficulty == ASIAN) {g.drawString("Asian (31 x 31)", getCenterForString("Asian (20 x 20)", g), 515);}

        }

        // draw back
        g.setColor(new Color(175, 74,0));
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("ESC to back", 10, GamePanel.HEIGHT-10);
    }

    private int getCenterForString(String s, Graphics g){
        int stringLen = (int)
                g.getFontMetrics().getStringBounds(s, g).getWidth();
        return GamePanel.WIDTH/2 - stringLen/2;
    }

    @Override
    public void keyPressed(int k) {

        if(k == KeyEvent.VK_UP){
            // go up an options
            currentChoice--;
            if (currentChoice == -1){
                currentChoice = options.length-1;
            }

        }
        if (k == KeyEvent.VK_DOWN){
            // go down an option
            currentChoice++;
            if (currentChoice == options.length){
                currentChoice = 0;
            }
        }

        if(k == KeyEvent.VK_ENTER){
            if(currentChoice == 0){
                currentPuzzle++;
                if(currentPuzzle >= puzzlePath.length){
                    currentPuzzle = 0;
                }
            }
            if(currentChoice == 1){
                if(difficulty == EASY) {difficulty = MEDIUM;}
                else if(difficulty == MEDIUM) { difficulty = HARD; }
                else if(difficulty == HARD) { difficulty = PRO; }
                else if(difficulty == PRO) { difficulty = MASTER; }
                else if(difficulty == MASTER) { difficulty = ASIAN; }
                else if(difficulty == ASIAN) { difficulty = EASY; }

            }
        }

        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
