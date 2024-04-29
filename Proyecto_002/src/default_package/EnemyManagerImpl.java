package default_package;

import Figuras.EnemyManager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManagerImpl implements EnemyManager {
    private List<Point> enemies = new ArrayList<>();

    @Override
    public List<Point> getEnemies() {
        return enemies;
    }

    @Override
    public void createEnemies() {
        Random rand = new Random();
        int numEnemies = rand.nextInt(3) + 1; 
        for (int i = 0; i < numEnemies; i++) {
            int x = rand.nextInt(Main.WIDTH - 50); 
            int y = rand.nextInt(50); 
            enemies.add(new Point(x, y));
        }
    }

    @Override
    public void removeEnemy(Point enemy) {
        enemies.remove(enemy);
    }

    @Override
    public void clearEnemies() {
        enemies.clear();
    }
}