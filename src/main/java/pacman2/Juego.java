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

	public Juego(int anchoJuego, int largoJuego) {
		this.pacman = new Pacman();
		this.laberinto = new Laberinto();
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;
	}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }
	
	protected void mover() {
		pacman.moverPacman();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		setBackground(Color.black);
		laberinto.dibujarLaberinto(g2d);
		pacman.paint(g2d);
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
			mover();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
