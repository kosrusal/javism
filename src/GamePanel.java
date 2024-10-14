// src/GamePanel.java
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc;
    public Timer timer;
    private Image backgroundImage;

    public GamePanel() {
        System.out.println("Create GamePanel");
        addKeyListener(this);
        timer = new Timer(50, this);
        timer.start();

        try {
            Image image = ImageIO.read(new File("untitled2/src/orc.png"));
            Weapon weapon = new Weapon();
            orc = new Ship(50, 50, 10, 0, image, weapon, 1);
        } catch (IOException e) {
            System.out.println("Облом в кораблике");
            throw new RuntimeException(e);
        }

        try {
            Image backgrountImage = ImageIO.read(new File("untitled2/src/frame.png"));
            this.backgroundImage = backgrountImage;
        } catch (IOException e) {
            System.out.println("Облом в фоне");
            throw new RuntimeException(e);
        }

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        orc.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc.direction.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc.direction.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc.direction.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc.direction.left = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc.direction.left = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orc.move();
        repaint();
    }
}
