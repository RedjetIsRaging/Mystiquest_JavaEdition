import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    public final int originalTileSize = 32;
    public final float scale = 1.5f;
    public final int tileSize = (int) (originalTileSize * scale);

    public final int screenCol = 20;
    public final int screenRow = 16;
    public final int screenWidth = screenRow * tileSize;
    public final int screenLength = screenCol * tileSize;


    Thread gameThread;
    InputHandler input = new InputHandler();

    //PLAYER INFORMATION
    public int playerX = 0;
    public int playerY = 0;
    public int playerSpeed = 3;

    //SYSTEM
    private int framesPerSecond = 60;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenLength, screenWidth));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/framesPerSecond;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            currentTime = System.nanoTime();
            if(currentTime - lastTime >= drawInterval) {
                //Update the game while it is running
                update();
                //Repaint the screen with the updated information
                repaint();
                lastTime = currentTime;
            }
        }
    }

    public void update(){
        //Updates the player info
        if(input.up){
            playerY -= playerSpeed;
        }
        else if(input.left){
            playerX -= playerSpeed;
        }
        else if(input.down){
            playerY += playerSpeed;
        }
        else if(input.right){
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLUE);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
        g2.dispose();
    }
}
