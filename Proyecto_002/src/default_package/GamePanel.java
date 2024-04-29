package default_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import Figuras.EnemyManager;

public class GamePanel extends JPanel {
	private EnemyManager enemyManager;
	private Image buffer;

	private int posicionJugadorX = 275;
	private int posicionJugadorY = 450;
	private int puntaje = 0;
	private int vidas = 3;
	private String usuario="David";
	private boolean disparando = false;
	private int posicionBalaX = posicionJugadorX + 20;
	private int posicionBalaY = posicionJugadorY - 10;
	private boolean juegoTerminado = false;

	public GamePanel(EnemyManager enemyManager) {
		this.enemyManager = enemyManager;

		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setBackground(Color.BLACK);

		setFocusable(true);
		requestFocus();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int tecla = e.getKeyCode();
				if (tecla == KeyEvent.VK_LEFT) {
					if (posicionJugadorX > 0)
						posicionJugadorX -= 5; // Mover hacia la izquierda
				} else if (tecla == KeyEvent.VK_RIGHT) {
					if (posicionJugadorX < 530)
						posicionJugadorX += 5; // Mover hacia la derecha
				} else if (tecla == KeyEvent.VK_SPACE && !disparando) {
					disparando = true;
					posicionBalaX = posicionJugadorX + 20;
					posicionBalaY = posicionJugadorY - 10;
				}
			}
		});

		Timer timer = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!juegoTerminado) {
					actualizar();
					repaint();
				}
			}
		});
		timer.start();
	}

	public void paint(Graphics g) {
		if (buffer == null) {
			buffer = createImage(getWidth(), getHeight());
		}

		Graphics bufferGraphics = buffer.getGraphics();
		super.paint(bufferGraphics);
		bufferGraphics.setColor(Color.BLACK);
		bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

		// Dibujar jugador
		int[] xPoints = { posicionJugadorX, posicionJugadorX + 25, posicionJugadorX + 50 };
		int[] yPoints = { posicionJugadorY, posicionJugadorY - 20, posicionJugadorY };
		bufferGraphics.setColor(Color.BLUE);
		bufferGraphics.fillPolygon(xPoints, yPoints, 3);

		// Dibujar enemigos
		bufferGraphics.setColor(Color.RED);
		for (Point enemigo : enemyManager.getEnemies()) {
			bufferGraphics.fillRect(enemigo.x, enemigo.y, 50, 50);
		}

		// Dibujar bala
		if (disparando) {
			bufferGraphics.setColor(Color.WHITE);
			bufferGraphics.fillRect(posicionBalaX, posicionBalaY, 5, 10);
		}

		// Dibujar puntaje y vidas
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.drawString("Usuario: " +usuario , 10, 20);
		bufferGraphics.drawString("Puntaje: " + puntaje, 10, 60);
		bufferGraphics.drawString("Vidas: " + vidas, 10, 40);
		bufferGraphics.drawLine(0, 330,600,330);

		// Dibujar mensaje de fin de juego
		if (juegoTerminado) {
			bufferGraphics.drawString("Fin del juego", getWidth() / 2 - 50, getHeight() / 2);
		}

		// Dibujar el buffer en el panel
		g.drawImage(buffer, 0, 0, null);
	}

	private void actualizar() {
		List<Point> enemigos = enemyManager.getEnemies();

		if (enemigos.isEmpty()) {
			enemyManager.createEnemies(); // Crear enemigos 
		} else {
			for (Point enemigo : enemigos) {

				if (enemigo.y >= getHeight() * 0.6) {
					juegoTerminado = true;
					break;
				}
				enemigo.y +=  1;// Mover enemigos hacia abajo
				if (disparando && colision(posicionBalaX, posicionBalaY, enemigo.x, enemigo.y)) {
					enemigos.remove(enemigo);
					puntaje += 10;
					disparando = false;
					break; 
				}
			}
		}

		if (disparando) {
			posicionBalaY -= 5;
			if (posicionBalaY <= 0) {
				disparando = false;
			}
		}

		if (juegoTerminado) {
			reiniciarJuego();
		}
	}

	private boolean colision(int x1, int y1, int x2, int y2) {
		// colision
		return x1 < x2 + 50 && x1 + 5 > x2 && y1 < y2 + 50 && y1 + 10 > y2;
	}

	private void reiniciarJuego() {
		List<Point> enemigos = enemyManager.getEnemies();
		vidas--;
		if (vidas > 0) {
			puntaje = 0;
			juegoTerminado = false;
			enemigos.clear();
		} 
	}
}
