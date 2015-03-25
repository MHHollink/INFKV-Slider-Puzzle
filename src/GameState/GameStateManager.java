package GameState;

import java.awt.*;
import java.util.ArrayList;

/**
 * This Class was created by marcel on 26-11-2014
 * Time of creation : 10:55
 */
public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int PUZZLESTATE = 1;
    public static final int HELPSTATE = 2;
    public static final int OPTIONSTATE = 3;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new PuzzleState(this));
        gameStates.add(new HelpState(this));
        gameStates.add(new OptionState(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void draw(Graphics2D g){
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k){
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }
}
