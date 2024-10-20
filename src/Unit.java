import java.awt.*;
import java.awt.geom.*;

public abstract class Unit implements Moving, Drawing {
    public int x = 0;
    public int y = 0;
    public int speed = 0;
    public double angle = 0;
    public Image image;
    public Direction direction;

    public Unit(int x, int y, int speed, double angle, Image image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.image = image;
        direction = new Direction();
    }

    public Rectangle getBoundingRectangle() {
        if (image == null) {
            return new Rectangle(x, y, 30, 30); // Default size if no image
        }
    
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        
        int nx = x + (int) Math.round(0.1*imageWidth);
        int ny = y + (int) Math.round(0.1*imageHeight);
        int nw = imageWidth - (int) Math.round(0.2*imageWidth);
        int nh = imageHeight - (int) Math.round(0.2*imageHeight);
        
        return new Rectangle(nx, ny, nw, nh);
    }
}
