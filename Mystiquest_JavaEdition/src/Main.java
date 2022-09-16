import java.util.*;
import java.lang.*;
import javax.swing.*;
class Main {
    public static void main(String[] args) {
        //Sets up Window
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        frame.setTitle("Mystiquest : Java Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(gamePanel);
        frame.pack();
        gamePanel.startGameThread();
        frame.setVisible(true);
    }
}