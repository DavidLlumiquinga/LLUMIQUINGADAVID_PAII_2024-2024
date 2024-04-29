package Figuras;

import java.awt.Point;
import java.util.List;

public interface EnemyManager {
    public List<Point> getEnemies();
    public void createEnemies();
    public void removeEnemy(Point enemy);
    public void clearEnemies();
}
