package GameState;

import Main.Game;
import Main.GamePanel;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This Class was created by marcel on 26-11-2014
 * Time of creation : 11:29
 */
public class HelpState extends GameState {

    public HelpState(GameStateManager gsm){
        this.gsm = gsm;
    }

    @Override
    public void init() {    }

    @Override
    public void update() {  }

    @Override
    public void draw(Graphics2D g) {
        //draw Background
        g.clearRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw title
        g.setColor(MenuState.titleColor);
        g.setFont(MenuState.titleFont);
        int stringLen = (int)
                g.getFontMetrics().getStringBounds(Game.TITLE, g).getWidth();
        int start = GamePanel.WIDTH/2 - stringLen/2;
        g.drawString(Game.TITLE, start, 150);
        for (int i = 0; i < 4; i++) {
            g.drawLine(start,160+i,GamePanel.WIDTH-start,160+i);
        }

        String[] lines = {
                "Het doel van deze puzzel is om de blokken zo te",
                "schuiven dat ze de onderliggende afbeelding goed",
                "weergeven. Onder Opties kun je een andere",
                "Afbeelding selecteren en de moeilijkheidsgraad van",
                "de puzzel aanpassen.",
                "",
                "Probeer de puzzel zo snel mogenlijk op te lossen,",
                "en doe dit binnen zo minmogenlijk zetten.",
                "",
                "Succes!"
        };

        // draw game information
        g.setColor(new Color(175, 74,0));
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], 35, 210+(i*25));
        }

        // draw back
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("ESC to back", 10, GamePanel.HEIGHT-10);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    @Override
    public void keyReleased(int k) {    }
}
