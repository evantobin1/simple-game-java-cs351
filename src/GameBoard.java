
public class GameBoard {
    int width;
    int height;
    Entity entities[][];

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        entities = new Entity[width][height];
    }

    public void addEntity(Entity e, int x, int y) {
        entities[x][y] = e;
    }

    private int[] getLocation(Entity e) {
        int[] location = { -1, -1 };
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (entities[x][y] == e)
                    location = new int[] { x, y };
            }
        }
        return location;
    }

    public void tick() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (entities[x][y] == null)
                    continue;
                if (entities[x][y].health <= 0)
                    entities[x][y] = null;
            }
        }
    }

    public void moveEntity(Entity e, Direction direction) throws Exception {
        int location[] = getLocation(e);
        int x = location[0];
        int y = location[1];

        switch (direction) {
            case SOUTH:
                if (y >= height - 1) {
                    throw new Exception("Cannot go south, hit map border");
                }
                entities[x][y + 1] = e;
                entities[x][y] = null;
                return;
            case EAST:
                if (x >= width - 1) {
                    throw new Exception("Cannot go east, hit map border");
                }
                entities[x + 1][y] = e;
                entities[x][y] = null;
                return;
            case NORTH:
                if (y <= 0) {
                    throw new Exception("Cannot go north, hit map border");
                }
                entities[x][y - 1] = e;
                entities[x][y] = null;
                return;
            case WEST:
                if (x <= 0) {
                    throw new Exception("Cannot go west, hit map border");
                }
                entities[x - 1][y] = e;
                entities[x][y] = null;
                return;
        }
    }

    public Entity getNearbyEntity(Entity player, Direction direction) throws Exception {
        int location[] = getLocation(player);
        int x = location[0];
        int y = location[1];

        switch (direction) {
            case SOUTH:
                if (y >= height - 1) {
                    throw new Exception("Cannot get entity from the south, hit map border");
                }
                return entities[x][y + 1];
            case EAST:
                if (x >= width - 1) {
                    throw new Exception("Cannot get entity from the east, hit map border");
                }
                return entities[x + 1][y];

            case NORTH:
                if (y <= 0) {
                    throw new Exception("Cannot get entity from the north, hit map border");
                }
                return entities[x][y - 1];
            case WEST:
                if (x <= 0) {
                    throw new Exception("Cannot get entity from the west, hit map border");
                }
                return entities[x - 1][y];
            default:
                throw new Exception("Error");

        }

    }

    @Override
    public String toString() {
        String output = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // If nothing is at that spot
                if (entities[x][y] == null) {
                    output += " - ";
                    continue;
                }

                // If the spot is a player
                if (entities[x][y].getClass() == Player.class) {
                    output += " P ";
                    continue;
                }

                // If the spot is an enemy
                if (entities[x][y].getClass() == Enemy.class) {
                    output += " E ";
                    continue;
                }
            }
            output += "\n";
        }
        return output;
    }
}
