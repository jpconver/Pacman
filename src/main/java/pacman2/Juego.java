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
	private final static int LOSTLIFE_SCREEN = 4;
	private Image pantallaBienvenida = Imagenes.loadImage("images/pantallaIntro.png");
	private Image pantallaGraciasxJugar = Imagenes.loadImage("images/pantallaGracias.png");
	private Image pantallaVidaPerdida = Imagenes.loadImage("images/pantallaIntro.png");
	private Laberinto laberinto;
	private Pacman pacman;
	private Ghostred ghostred;
	private Ghostblue ghostblue;
	private Ghostgreen ghostgreen;
	private Ghostyellow ghostyellow;
	private Ghostpink ghostpink;
	private Score score;
	private Lifes vidas;
	private Sonidos sonidos;
	private boolean colisionred = false;
	private boolean colisionblue = false;
	private boolean colisiongreen = false;
	private boolean colisionyellow = false;
	private boolean colisionpink = false;
	private int anchoJuego;
	private int altoJuego;
	int cantVidas;
	private int level = 1;
	static int puntaje = 0;
	private int cantidadFantasmas;
	private int[] screendata = new int[400];
	private int pacmanx;
	private int pacmany;
	

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
		this.ghostred = new Ghostred(screendata, cantidadFantasmas, pantalla);
		this.ghostblue = new Ghostblue(screendata, cantidadFantasmas);
		this.ghostgreen = new Ghostgreen(screendata, cantidadFantasmas);
		this.ghostyellow = new Ghostyellow(screendata, cantidadFantasmas);
		this.ghostpink = new Ghostpink(screendata, cantidadFantasmas);
		cargarSonidos();
		iniciarVariables();
		sonidos.repetirSonido("music");
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
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(anchoJuego, altoJuego);
	}

	protected void actualizarJuego() {
		
		pacman.moverPacman();
		this.pacmanx = pacman.getPacmanx();
		this.pacmany = pacman.getPacmany();
		colisionred = ghostred.moveGhosts(pacmanx, pacmany);
		colisionblue = ghostblue.moveGhosts(pacmanx, pacmany, cantVidas);
		colisiongreen = ghostgreen.moveGhosts(pacmanx, pacmany, cantVidas);
		colisionyellow = ghostyellow.moveGhosts(pacmanx, pacmany, cantVidas);
		colisionpink = ghostpink.moveGhosts(pacmanx, pacmany, cantVidas);
		chequearLaberinto();
		
	}

	private void chequearLaberinto() {
		
		int i = 0;
		boolean completado = true;

		while (i < screendata.length && completado) {

			if ((screendata[i] & 48) != 0) {
				completado = false;
			}
			i++;
		}
		if(colisionred || colisionblue || colisiongreen || colisionyellow || colisionpink) {
			pantalla = LOSTLIFE_SCREEN;
			colisionred = false;
			colisionblue = false;
			colisiongreen = false;
			colisionyellow = false;
			colisionpink = false;
		}else if (completado && level == 2) {
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
			ghostred.drawGhost(g2d);
			ghostblue.drawGhost(g2d);
			ghostgreen.drawGhost(g2d);
			ghostyellow.drawGhost(g2d);
			ghostpink.drawGhost(g2d);
		}
		if (pantalla == THANKS_SCREEN) {
			dibujarPantalla(g2d, pantallaGraciasxJugar);
		} if (pantalla == LOSTLIFE_SCREEN) {
			dibujarPantalla(g2d, pantallaVidaPerdida);
			mostrarMensaje(g2d);
		}
	}

	private void mostrarMensaje(Graphics2D g2d) {
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(60, altoJuego - 120, anchoJuego - 130, 50);
		g2d.setColor(Color.white);
		g2d.drawRect(60, altoJuego - 120, anchoJuego - 130, 50);
		String mensaje = "Presiona la Barra espaciadora para Iniciar";
		String nextLevel = "Lo haz logrado! Listo para el proximo nivel ?";
		String vidaperdida = "¡Perdiste una vida! Espacio para jugar otra vez";
		Font small = new Font("Comic Sans MS", Font.PLAIN, 18);
		g2d.setFont(small);
		if(colisionred || colisionblue || colisiongreen || colisionyellow || colisionpink) {
			g2d.drawString(vidaperdida, 67, 492);
		} else if (level == 1) {
			g2d.drawString(mensaje, 82, 492);
		} else if (level == 2) {
			g2d.drawString(nextLevel, 70, 492);
		}
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
	
	private void dibujarJuego() {
		this.repaint();
	}
	@Override
	public void run() {
		while (true) {
			if (pantalla == GAME_SCREEN) {
				actualizarJuego();
			}
			dibujarJuego();
			esperar(15);
		}
	}

	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
