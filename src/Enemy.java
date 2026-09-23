public class Enemy extends Entity {

    // Enemy object constructor
    public Enemy(String name, String type, int maxHealth, double gold) {
        super(name, maxHealth, gold, 0, type, 'w');
    }
}
