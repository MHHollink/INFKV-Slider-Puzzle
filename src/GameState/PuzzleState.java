package GameState;

import Gfx.ImageLoader;
import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

/**
 * This Class was created by marcel on 26-11-2014
 * Time of creation : 11:12
 */
@SuppressWarnings("unused")
public class PuzzleState extends GameState {

    static ImageLoader il;

    private boolean moved = false;
    public static boolean started = false;
    static int moves = 0;

    public static boolean ingame;
    boolean timeStarted = false;
    long start;
    long finishedTime;

    String puzzle;
    int difficuly;

    public static boolean finished;
    private boolean stopped = false;

    public PuzzleState(GameStateManager gsm) {
        this.gsm = gsm;

        init();
    }

    @Override
    public void init() {
        puzzle = OptionState.puzzlePath[OptionState.currentPuzzle];
        this.difficuly = OptionState.difficulty;
        il = new ImageLoader(puzzle, difficuly);

        try {
            shuffle();

            started = true;
            stopped = false;
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        // todo: write working wincheck function.
        if(Arrays.equals(il.getShuffled(), il.getNotShuffled())) {
            finished = true;

            if(!stopped){
                finishedTime = (System.currentTimeMillis()-start)/1000;
                stopped=true;
                il.replaceLastChunk();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(MenuState.font);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(10,10, GamePanel.WIDTH-20, GamePanel.WIDTH-20);
        g.setColor(new Color(175, 74, 0));
        g.drawRect(10,10, GamePanel.WIDTH-20, GamePanel.WIDTH-20);

        int x = 0; int y = 0;
        for (int i = 0; i < il.getChunks().length; i++) {
            if(il.getChunk(i) != null){
                g.drawImage(il.getChunk(i), 11+(x*il.getChunkWidth()+x), 11+(y*il.getChunkHeight()+y), null);
            }else{
                g.setColor(new Color(175, 74,0));
                g.fillRect(11+(x*il.getChunkWidth()+x), 11+(y*il.getChunkHeight()+y), il.getChunkWidth(), il.getChunkHeight());
            }

            x++;
            if( x >= il.getNumCols()){
                x=0;
                y++;
            }
        }

        if(moves < 10){
            g.drawString("Moves : '00"+moves+"'", 50, 665);
        }else if(moves < 100){
            g.drawString("Moves : '0"+moves+"'", 50, 665);
        }else{
            g.drawString("Moves : '"+moves+"'", 50, 665);
        }

        if(timeStarted && !finished){
            if (((System.currentTimeMillis() - start) / 1000) < 10){
                g.drawString("Time : '00"+((System.currentTimeMillis()-start)/1000)+"'", 430, 665);
            }else if(((System.currentTimeMillis() - start) / 1000) < 100){
                g.drawString("Time : '0"+((System.currentTimeMillis()-start)/1000)+"'", 430, 665);
            }else{
                g.drawString("Time : '"+((System.currentTimeMillis()-start)/1000)+"'", 430, 665);
            }
        } else if (finished) {
            if(finishedTime < 100){
                g.drawString("Time : '0"+(int)finishedTime+"'", 430, 665);
            }else {
                g.drawString("Time : '"+(int)finishedTime+"'", 430, 665);
            }

        }else{
                g.drawString("Time : '000'", 430, 665);

        }

    }

    public void shuffle() throws AWTException {

        for (int i = 0; i < 3000*il.getNumCols(); i++) {
            checkMove(new Random().nextInt(4));
        }

    }

    private void checkMove(int key) {
        switch (key){
            case 0:
                // up
                for (int i = 0; i < il.getChunks().length; i++) {
                    if(i == (il.getEmptySlot()-il.getNumRows())){
                        il.setImageChunk(i);
                        if(started){
                            moves++;
                            if(!timeStarted && ingame)
                            {
                                start = System.currentTimeMillis();
                                timeStarted = true;
                            }
                        }
                        return;
                    }
                }

                break;
            case 1:
                // down
                for (int i = 0; i < il.getChunks().length; i++) {
                    if(i == (il.getEmptySlot()+il.getNumRows())){
                        il.setImageChunk(i);
                        if(started){
                            moves++;
                            if(!timeStarted && ingame)
                            {
                                start = System.currentTimeMillis();
                                timeStarted = true;
                            }
                        }
                        return;
                    }
                }
                break;
            case 2:
                // left
                for (int i = 0; i < il.getChunks().length; i++) {
                    if(i == (il.getEmptySlot()-1)){
                        if(!(((i+1) % il.getNumCols()) == 0)){
                            il.setImageChunk(i);
                            if(started){
                                moves++;
                                if(!timeStarted && ingame)
                                {
                                    start = System.currentTimeMillis();
                                    timeStarted = true;
                                }
                            }
                            return;
                        }
                    }
                }
                break;
            case 3:
                // right
                for (int i = 0; i < il.getChunks().length; i++) {
                    if(i == (il.getEmptySlot()+1)){
                        if(!(((i) % il.getNumCols()) == 0)){
                            il.setImageChunk(i);
                            if(started){
                                moves++;
                                if(!timeStarted && ingame)
                                {
                                    start = System.currentTimeMillis();
                                    timeStarted = true;
                                }
                            }
                            return;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void keyPressed(int k) {
        if(!stopped) {
            if ((k == KeyEvent.VK_UP) && (!moved)) {
                moved = true;
                checkMove(0);
            }
            if ((k == KeyEvent.VK_DOWN) && (!moved)) {
                moved = true;
                checkMove(1);
            }
            if ((k == KeyEvent.VK_LEFT) && (!moved)) {
                moved = true;
                checkMove(2);
            }
            if ((k == KeyEvent.VK_RIGHT) && (!moved)) {
                moved = true;
                checkMove(3);
            }
        }
        if (k == KeyEvent.VK_ESCAPE) {
            timeStarted = false;
            ingame = false;
            moves = 0;
            gsm.setState(GameStateManager.MENUSTATE);

        }
    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_UP){
            moved=false;
        }
        if(k == KeyEvent.VK_DOWN){
            moved=false;
        }
        if(k == KeyEvent.VK_LEFT){
            moved=false;
        }
        if(k == KeyEvent.VK_RIGHT){
            moved=false;
        }
    }



}
