
public class Player extends Entity {
    public Sword sword;

    public Player(int health) {
        this.health = health;
    }

    public void attack(Entity e) {
        sword.swing(e);
    }
}
