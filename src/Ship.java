// src/Ship.java
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
 
public class Ship extends Unit implements HealthPoint {
    public Weapon weapon;
    public int hp = 1;


    public Ship(int x, int y, int speed, double angle, Image image, Weapon weapon, int hp) {
        super(x, y, speed, angle, image);
        this.weapon = weapon;
        this.hp = hp;
    }

    public void fire() {
        try {
            Image image = ImageIO.read(new File("javism/src/bullet.png"));
            // Create a new bullet at the ship's position and angle
            Bullet bullet = new Bullet(
                    this.x + image.getWidth(null) / 2, // Bullet starts at ship's center x
                    this.y + image.getHeight(null) / 2, // Bullet starts at ship's center y
                    15, // Bullet speed
                    this.angle, 
                    image, // You can provide a bullet image here if you have one
                    10 // Bullet damage
            ); 
            weapon.bullets.add(bullet); // Add the bullet to the weapon's bullet list
        } catch (Exception e) {
            System.out.println("Облом в пули");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void move() {
        int dx = (int) (Math.cos(angle) * speed);
        int dy = (int) (Math.sin(angle) * speed);

        if (direction.up) {
            x -= dx;
            y -= dy;
        }
        if (direction.down) {
            x += dx;
            y += dy;
        }
        if (direction.left) {
            angle -= 0.1;
        }
        if (direction.right) {
            angle += 0.1;
        }

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.rotate(angle, x + image.getWidth(null) / 2,
                y + image.getHeight(null) / 2);
        g.drawImage(image, x, y, null);
        graphics2D.rotate(-angle, x + image.getWidth(null) / 2,
                y + image.getHeight(null) / 2);
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void setHP(int healthPoint) {
        hp = healthPoint;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
