import java.awt.*;

public class Bullet extends Unit {
    public int damage;

    public Bullet(int x, int y, int speed, double angle, Image image, int damage) {
        super(x, y, speed, angle, image);
        this.damage = damage;
    }

    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, null);
        } else {
            // If no image, draw a simple shape (e.g., a line)
            g.setColor(Color.RED);
            g.drawLine(x, y, x + 5, y); // Adjust length as needed
        }
    }

    @Override
    public void move() {
        x += (int) (Math.cos(angle) * speed);
        y += (int) (Math.sin(angle) * speed);
    }
}
