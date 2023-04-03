
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);

        // Initialize gameboard
        GameBoard gb = new GameBoard(10, 10);

        // Setup player
        Player player = new Player(20);
        Sword sword = new Sword(10);
        player.sword = sword;
        gb.addEntity(player, 2, 1);

        // Setup enemies
        gb.addEntity(new Enemy(20), 4, 1);
        gb.addEntity(new Enemy(20), 6, 4);

        String input = "";
        // Gameloop
        while (true) {
            gb.tick();
            System.out.println("\n" + gb.toString());
            System.out.println("- move {NORTH, EAST, SOUTH, WEST}\n- attack {NORTH, EAST, SOUTH, WEST}\n- exit");

            input = scnr.next();
            Direction direction;

            switch (input) {
                case "exit":
                    scnr.close();
                    return;

                case "move":
                    try {
                        input = scnr.next();
                        direction = Direction.valueOf(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println(input + " is not a direction!");
                        continue;
                    }
                    try {
                        gb.moveEntity(player, direction);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "attack":
                    try {
                        input = scnr.next();
                        direction = Direction.valueOf(input);
                    } catch (IllegalArgumentException e) {
                        System.out.println(input + " is not a direction!");
                        continue;
                    }
                    try {
                        Entity e = gb.getNearbyEntity(player, direction);
                        if (e != null) {
                            player.attack(e);
                            System.out
                                    .println("Player has done " + player.sword.attackDamage + " damage to enemy, "
                                            + e.health + " health remaining!");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
            }
        }
    }
}
