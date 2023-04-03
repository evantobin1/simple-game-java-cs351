
public class Sword {
    public int attackDamage;

    public Sword(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void swing(Entity e) {
        e.health -= attackDamage;
    }
}
