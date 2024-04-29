package default_package;

import javax.swing.JFrame;

import Figuras.EnemyManager;
public class Main {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    public static void main(String[] args) {
        
    	EnemyManager enemyManager = new EnemyManagerImpl();
        JFrame frame = new JFrame("Juego Galaga");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel(enemyManager);
        frame.add(gamePanel);

        frame.setVisible(true);
    }
}
