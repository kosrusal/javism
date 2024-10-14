import java.awt.*;

public class Bullet extends Unit {
    public int damage;

    public Bullet(int x, int y, int speed, double angle, Image image, int damage) {
        super(x, y, speed, angle, image);
        this.damage = damage;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void move() {

    }
}
