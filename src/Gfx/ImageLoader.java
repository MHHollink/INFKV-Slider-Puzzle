package Gfx;


import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This Class was created by marcel on 26-11-2014
 * Time of creation : 11:53
 */
public class ImageLoader {

    private BufferedImage image;
    private BufferedImage[] imageChunks;
    private int[] shuffled, notShuffled;
    private BufferedImage lastChunk;
    private int emptySlot;

    private int numRows;
    private int numCols;

    private int chunkWidth;
    private int chunkHeight;

    private String imagePath;

    public ImageLoader(String imagePath, int puzzleSize){
        this.imagePath = imagePath;
        numCols = numRows = puzzleSize;

        init();

        image = scale(image, GamePanel.WIDTH-(20+numRows), GamePanel.WIDTH-(20+numCols), false);

        makeChunks();

        lastChunk = imageChunks[imageChunks.length-1];
        imageChunks[imageChunks.length-1] = null;
        emptySlot = imageChunks.length-1;
    }

    private void init(){
        try {
            image = ImageIO.read(
                    getClass().getResourceAsStream(imagePath)
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void makeChunks(){
        int chunks = numRows * numCols;

        int width = image.getWidth() / numCols;
        int height = image.getHeight() / numRows;
        int count = 0;
        imageChunks = new BufferedImage[chunks];
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numCols; y++) {
                //Initialize the image array with image chunks
                imageChunks[count] = new BufferedImage(width, height, image.getType());

                // draws the image chunk
                Graphics2D gr = imageChunks[count++].createGraphics();
                gr.drawImage(image, 0, 0, width, height, width * y, height * x, width * y + width, height * x + height, null);
                gr.dispose();
            }
        }

        shuffled = new int[imageChunks.length];
        notShuffled = new int[imageChunks.length];
        for (int i = 0; i < imageChunks.length; i++) {
            shuffled[i] = i;
            notShuffled[i] = i;
        }

        chunkHeight = imageChunks[0].getHeight();
        chunkWidth = imageChunks[0].getWidth();
    }

    private BufferedImage scale(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public BufferedImage[] getChunks() {
        return imageChunks;
    }


    public BufferedImage getChunk(int c){
        return imageChunks[c];
    }

    public int getChunkWidth() {
        return chunkWidth;
    }

    public int getChunkHeight() {
        return chunkHeight;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public BufferedImage getLastChunk() {
        return lastChunk;
    }

    public int getEmptySlot() {
        return emptySlot;
    }

    public void setImageChunk(int chunk) {
        int temp = shuffled[chunk];
        shuffled[chunk] = shuffled[emptySlot];
        shuffled[emptySlot] = temp;

        imageChunks[emptySlot] = imageChunks[chunk];
        imageChunks[chunk] = null;
        emptySlot = chunk;


    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getShuffled() {
        return shuffled;
    }

    public int[] getNotShuffled() {
        return notShuffled;
    }

    public void replaceLastChunk() {
        imageChunks[imageChunks.length-1] = lastChunk;
    }
}
