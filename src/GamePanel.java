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
import java.util.Iterator;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc;
    public Ship enemy;
    public Timer timer;
    private Image backgroundImage;

    public GamePanel() {
        System.out.println("Create GamePanel");
        addKeyListener(this);
        timer = new Timer(50, this);
        

        try {
            Image image = ImageIO.read(new File("javism/src/cpp.png"));
            Weapon weapon = new Weapon();
            orc = new Ship(50, 50, 10, 0, image, weapon, 1);
        } catch (IOException e) {
            System.out.println("Облом в кораблике");
            throw new RuntimeException(e);
        }

        try {
            Image image = ImageIO.read(new File("javism/src/py.png"));
            Weapon weapon = new Weapon();
            enemy = new Ship(50, 50, 10, 0, image, weapon, 1);
        } catch (IOException e) {
            System.out.println("Облом во враге");
            throw new RuntimeException(e);
        }

        try {
            Image backgrountImage = ImageIO.read(new File("javism/src/frame.png"));
            this.backgroundImage = backgrountImage;
        } catch (IOException e) {
            System.out.println("Облом в фоне");
            throw new RuntimeException(e);
        }

        setFocusable(true);
        requestFocusInWindow();
        timer.start();
    }

    public void drawBullets(Ship ship, Graphics g) {
        for (Bullet bullet : ship.weapon.bullets) {
            bullet.draw(g);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        if (orc != null) {
            orc.draw(g);
            drawBullets(orc, g);
        }
        if (enemy != null) {
            enemy.draw(g);
            drawBullets(enemy, g);
        }

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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            orc.fire(); 
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            enemy.direction.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            enemy.direction.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            enemy.direction.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            enemy.direction.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            enemy.fire();
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
        if (e.getKeyCode() == KeyEvent.VK_W) {
            enemy.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            enemy.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            enemy.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            enemy.direction.left = false;
        }
    }

    private void updateAndRemoveBullets(Ship ship) {
        if (ship.weapon != null) { // Check if the ship has a weapon
            for (Iterator<Bullet> iterator = ship.weapon.bullets.iterator(); iterator.hasNext(); ) {
                Bullet bullet = iterator.next();
                bullet.move();

                if (bullet.x < 0 || bullet.x > getWidth() || bullet.y < 0 || bullet.y > getHeight()) {
                    iterator.remove(); // Remove the bullet from the list
                } else {
                    // Check for collision with the other ship
                    Ship otherShip = (ship == orc) ? enemy : orc;
                    if (otherShip == null) {
                        return;
                    }
                    if (isCollision(bullet.getBoundingRectangle(), otherShip.getBoundingRectangle())) {
                        otherShip.setHP(otherShip.getHP() - bullet.damage);
                        iterator.remove();
                        System.out.println("Ship HP: " + otherShip.getHP());

                        if (!otherShip.isAlive()) {
                            otherShip.setHP(0);
                            if (otherShip == orc) {
                                orc = null;
                            } else {
                                enemy = null;
                            }
                            System.out.println("Ship defeated!");
                            // Handle ship defeat (e.g., end the game, respawn)
                        }
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (orc != null) {
            orc.move();
            updateAndRemoveBullets(orc);
        }
        if (enemy != null) {
            enemy.move();
            updateAndRemoveBullets(enemy);
        }
    
        
        repaint(); 
    }

    private boolean isCollision(Rectangle object1, Rectangle object2) {
        return object1.intersects(object2);
    }
}
