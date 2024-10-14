// src/GameFrame.java
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame(int size) throws HeadlessException {
        System.out.println("Create GameFrame");
        setBounds(100, 100, size, size);
        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
    }
}
