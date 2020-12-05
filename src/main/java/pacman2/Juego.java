package pacman2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Juego extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	Pacman pacman = new Pacman();
	Laberinto laberinto = new Laberinto();
	
	protected void mover() {
		pacman.moverPacman();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		setBackground(Color.black);
		laberinto.dibujarLaberinto(g2d);
		pacman.paint(g2d);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			pacman.reqdx = -3;
			pacman.reqdy = 0;
			pacman.viewdx = -1;
			pacman.viewdy = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			pacman.reqdx = 3;
			pacman.reqdy = 0;
			pacman.viewdx = 1;
			pacman.viewdy = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			pacman.reqdy = -3;
			pacman.reqdx = 0;
			pacman.viewdy = 1;
			pacman.viewdx = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			pacman.reqdy = 3;
			pacman.reqdx = 0;
			pacman.viewdy = -1;
			pacman.viewdx = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
