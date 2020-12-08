package pacman2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Juego extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private Pacman pacman;
	private Laberinto laberinto;
	private int anchoJuego;
	private int largoJuego;
	private Sonidos sonidos;
	private int cantVidas;
	@SuppressWarnings("unused")
	private int cantidadFantasmas;
	private int[] screendata = new int[400];
	
	

	public Juego(int anchoJuego, int largoJuego, int vidas, int fantasmas) {
		this.laberinto = new Laberinto(screendata);
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;
		this.cantVidas = vidas;
		this.cantidadFantasmas = fantasmas;
		
		iniciarVariables();
		cargarSonidos();
		iniciarJuego();
	}

    private void iniciarVariables() {
    	for (int i = 0; i<laberinto.datosLaberinto.length; i++) {
		screendata[i] = laberinto.datosLaberinto[i];
    	}
	}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }
	
    private void iniciarJuego() {
    	this.pacman = new Pacman(cantVidas, screendata);
    	//sonidos.repetirSonido("music");
    }
    
	protected void actualizarJuego() {
		pacman.moverse();
	}


	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		setBackground(Color.black);
		pacman.paint(g2d);
		laberinto.dibujarLaberinto(g2d);
		pacman.drawScore(g2d);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			pacman.reqdx = -1;
			pacman.reqdy = 0;
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			pacman.reqdx = 1;
			pacman.reqdy = 0;

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			pacman.reqdy = 1;
			pacman.reqdx = 0;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			pacman.reqdy = -1;
			pacman.reqdx = 0;

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
		while(true) {
			actualizarJuego();
			repaint();
			try {
				Thread.sleep(10);
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
