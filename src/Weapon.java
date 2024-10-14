import java.util.LinkedList;

public class Weapon implements Attack {
    public LinkedList<Bullet> bullets = new LinkedList<>();

    @Override
    public void toAttack(HealthPoint enemy) {
        
    }
}

