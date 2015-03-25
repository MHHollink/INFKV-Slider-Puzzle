package GameState;

import Main.Game;
import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This Class was created by marcel on 26-11-2014
 * Time of creation : 10:56
 */
public class MenuState extends GameState{

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Help",
            "Options",
            "Quit"
    };

    public static Color titleColor;
    public static Font titleFont;

    public static Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        titleColor = new Color(255, 114,0);
        titleFont = new Font("Century Gothic", Font.PLAIN, 64);

        font = new Font("Arial", Font.PLAIN, 27);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {  }

    @Override
    public void draw(Graphics2D g) {
        //draw Background
        g.clearRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        int stringLen = (int)
                g.getFontMetrics().getStringBounds(Game.TITLE, g).getWidth();
        int start = GamePanel.WIDTH/2 - stringLen/2;
        g.drawString(Game.TITLE, start, 150);
        for (int i = 0; i < 4; i++) {
            g.drawLine(start,160+i,GamePanel.WIDTH-start,160+i);
        }

        // draw credits
        g.setFont(new Font("Arial", Font.PLAIN, 21));
        int creditStringLenght = (int)
                g.getFontMetrics().getStringBounds("by Marcel Hollink", g).getWidth();
        g.drawString("by Marcel Hollink", start+stringLen-creditStringLenght,190);

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(new Color(255, 180, 100));
            }
            else {
                g.setColor(new Color(175, 74,0));
            }
            stringLen = (int)
                    g.getFontMetrics().getStringBounds(options[i], g).getWidth();
            start = GamePanel.WIDTH / 2 - stringLen / 2;
            g.drawString(options[i], start, 330 + i*38);
        }
    }

    private void select(){
        if(currentChoice == 0){
            // Start
            gsm.setState(GameStateManager.PUZZLESTATE);
            PuzzleState.moves = 0;
            PuzzleState.finished = false;
            PuzzleState.ingame = true;
        }
        if(currentChoice == 1){
            // Help
            gsm.setState(GameStateManager.HELPSTATE);
        }
        if(currentChoice == 2){
            // Options
            gsm.setState(GameStateManager.OPTIONSTATE);
        }
        if(currentChoice == 3){
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            select();
        }

        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1){
                currentChoice = options.length-1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length){
                currentChoice = 0;
            }
        }
        if(k == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int k) {    }
}
