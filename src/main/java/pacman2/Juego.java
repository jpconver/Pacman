package pacman2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Juego extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;
	private int pantalla;
	private final static int WELCOME_SCREEN = 1;
	private final static int GAME_SCREEN = 2;
	private final static int THANKS_SCREEN = 3;
	private Image pantallaBienvenida = Imagenes.loadImage("images/pantallaIntro.png");
	private Image pantallaGraciasxJugar = Imagenes.loadImage("images/pantallaGracias.png");
	private Laberinto laberinto;
	private Pacman pacman;
	private Ghost ghost;
	private Score score;
	private Lifes vidas;
	private Sonidos sonidos;
	private int anchoJuego;
	private int altoJuego;
	int cantVidas;
	private int level = 1;
	static int puntaje = 0;
	private int cantidadFantasmas;
	private int[] screendata = new int[400];
	

	public Juego(int anchoJuego, int altoJuego, int vidas, int fantasmas) {
		this.pantalla = WELCOME_SCREEN;
		this.laberinto = new Laberinto(screendata);
		this.anchoJuego = anchoJuego;
		this.altoJuego = altoJuego;
		this.cantVidas = vidas;
		this.cantidadFantasmas = fantasmas;
		this.pacman = new Pacman(screendata);
		this.vidas = new Lifes(cantVidas, anchoJuego, altoJuego);
		this.score = new Score(anchoJuego, altoJuego);
		this.ghost = new Ghost(screendata, cantidadFantasmas, pantalla);
		cargarSonidos();
		iniciarVariables();
		//sonidos.repetirSonido("music");
	}

	private void iniciarVariables() {
		if (level == 1) {
			for (int i = 0; i < laberinto.level_1.length; i++) {
				screendata[i] = laberinto.level_1[i];
			}
		} else if (level == 2) {
			for (int i = 0; i < laberinto.level_2.length; i++) {
				screendata[i] = laberinto.level_2[i];
			}
		}
		ghost.iniciarVariables();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(anchoJuego, altoJuego);
	}

	protected void actualizarJuego(Graphics2D g2d) {
		chequearLaberinto();
		pacman.moverPacman();
		ghost.moveGhosts(g2d);
	}

	private void chequearLaberinto() {
		int i = 0;
		boolean completado = true;

		while (i < laberinto.nrofblocks * laberinto.nrofblocks && completado) {

			if ((screendata[i] & 48) != 0) {
				completado = false;
			}
			i++;
		}
		if (completado && level == 2) {
			pantalla = THANKS_SCREEN;
		} else if (completado) {
			pantalla = WELCOME_SCREEN;
			level++;
			iniciarVariables();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setBackground(Color.black);
		if (pantalla == WELCOME_SCREEN) {
			dibujarPantalla(g2d, pantallaBienvenida);
			mostrarMensaje(g2d);
		}
		if (pantalla == GAME_SCREEN) {
			super.paintComponent(g2d);
			pacman.paint(g2d);
			laberinto.dibujarLaberinto(g2d);
			score.drawScore(g2d);
			vidas.drawLifes(g2d);
			ghost.drawGhost(g2d);
		}
		if (pantalla == THANKS_SCREEN) {
			dibujarPantalla(g2d, pantallaGraciasxJugar);
		}
	}

	private void mostrarMensaje(Graphics2D g2d) {
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(60, altoJuego - 120, anchoJuego - 140, 50);
		g2d.setColor(Color.white);
		g2d.drawRect(60, altoJuego - 120, anchoJuego - 140, 50);
		String mensaje = "Presiona la Barra espaciadora para Iniciar";
		String nextLevel = "Lo haz logrado! Listo para el proximo nivel ?";
		Font small = new Font("Comic Sans MS", Font.PLAIN, 18);
		g2d.setFont(small);
		if (level == 1)
			g2d.drawString(mensaje, 82, 492);
		if (level == 2)
			g2d.drawString(nextLevel, 70, 492);
	}

	private void dibujarPantalla(Graphics g, Image screen) {
		try {
			g.drawImage(screen, 0, 0, anchoJuego, altoJuego, null);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && pantalla == GAME_SCREEN) {
			pacman.reqdx = -1;
			pacman.reqdy = 0;

		}
		if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && pantalla == GAME_SCREEN){
			pacman.reqdx = 1;
			pacman.reqdy = 0;

		}
		if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && pantalla == GAME_SCREEN){
			pacman.reqdy = 1;
			pacman.reqdx = 0;

		}
		if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && pantalla == GAME_SCREEN){
			pacman.reqdy = -1;
			pacman.reqdx = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			pantalla = GAME_SCREEN;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public Laberinto getLaberinto() {
		return laberinto;
	}

	@Override
	public void run() {
		while (true) {
			if (pantalla == WELCOME_SCREEN) {
			}else {
				actualizarJuego(null);
				repaint();
			}
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void cargarSonidos() {
		try {
			sonidos = new Sonidos();
			sonidos.agregarSonido("music", "sounds/pacman-theme.wav");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
}
