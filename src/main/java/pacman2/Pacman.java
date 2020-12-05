package pacman2;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pacman extends Laberinto{

	
	int pacmanx = 2 * blocksize;
	int pacmany = 11 * blocksize;
	int reqdx = 0;
	int reqdy = 0;
	int pacmandx = 0;
	int pacmandy = 0;
	int viewdx = 0;
	int viewdy = 0;
	int pacmanspeed = 6;
	
	private final int pacmandelay = 3;
	private final int pacmananimcount = 4;
	private int pacanimcount = pacmandelay;
	private int pacanimdir = 1;
	private int pacmaninicial = 0;
	
	private Image pacman, pacman1, pacman1up, pacman1left, pacman1right, pacman1down;
	private Image pacman2up, pacman2down, pacman2left, pacman2right;
	private Image pacman3up, pacman3down, pacman3left, pacman3right;

	
	private void cargarImagen() {
		pacman = new ImageIcon(getClass().getResource("../sprites/pacman.png")).getImage();
		pacman1 = new ImageIcon(getClass().getResource("../sprites/pacman1.png")).getImage();
		pacman1up = new ImageIcon(getClass().getResource("../sprites/up1.png")).getImage();
		pacman2up = new ImageIcon(getClass().getResource("../sprites/up2.png")).getImage();
		pacman3up = new ImageIcon(getClass().getResource("../sprites/up3.png")).getImage();
		pacman1down = new ImageIcon(getClass().getResource("../sprites/down1.png")).getImage();
		pacman2down = new ImageIcon(getClass().getResource("../sprites/down2.png")).getImage();
		pacman3down = new ImageIcon(getClass().getResource("../sprites/down3.png")).getImage();
		pacman1left = new ImageIcon(getClass().getResource("../sprites/left1.png")).getImage();
		pacman2left = new ImageIcon(getClass().getResource("../sprites/left2.png")).getImage();
		pacman3left = new ImageIcon(getClass().getResource("../sprites/left3.png")).getImage();
		pacman1right = new ImageIcon(getClass().getResource("../sprites/right1.png")).getImage();
		pacman2right = new ImageIcon(getClass().getResource("../sprites/right2.png")).getImage();
		pacman3right = new ImageIcon(getClass().getResource("../sprites/right3.png")).getImage();
	}

	void moverPacman() { 
		int pos;
		short ch;
		
		
        if (reqdx == -pacmandx && reqdy == -pacmandy) {
            pacmandx = reqdx;
            pacmandy = reqdy;
            viewdx = pacmandx;
            viewdy = pacmandy;
        }
/*
        if (pacmanx % blocksize == 0 && pacmany % blocksize == 0) {
            pos = pacmanx / blocksize + nrofblocks * (int) (pacmany / blocksize);
            ch = screendata[pos];

            if ((ch & 16) != 0) {
                screendata[pos] = (short) (ch & 15);
                //score++;
            }

            if (reqdx != 0 || reqdy != 0) {
                if (!((reqdx == -1 && reqdy == 0 && (ch & 1) != 0)
                        || (reqdx == 1 && reqdy == 0 && (ch & 4) != 0)
                        || (reqdx == 0 && reqdy == -1 && (ch & 2) != 0)
                        || (reqdx == 0 && reqdy == 1 && (ch & 8) != 0))) {
                    pacmandx = reqdx;
                    pacmandy = reqdy;
                    viewdx = pacmandx;
                    viewdy = pacmandy;
                }
            }

            // Check for standstill
            if ((pacmandx == -1 && pacmandy == 0 && (ch & 1) != 0)
                    || (pacmandx == 1 && pacmandy == 0 && (ch & 4) != 0)
                    || (pacmandx == 0 && pacmandy == -1 && (ch & 2) != 0)
                    || (pacmandx == 0 && pacmandy == 1 && (ch & 8) != 0)) {
                pacmandx = 0;
                pacmandy = 0;
            }
        }
        pacmanx = pacmanx + pacmanspeed * pacmandx;
        pacmany = pacmany + pacmanspeed * pacmandy;
        */
    }
	
	
	void paint(Graphics2D g2d) {
		cargarImagen();
		hacerAnimacion();
		dibujarPacman(g2d);
	}
	
	private void hacerAnimacion() {

		pacanimcount--;

		if (pacanimcount <= 0) {
			pacanimcount = pacmandelay;
			pacmaninicial = pacmaninicial + pacanimdir;

			if (pacmaninicial == (pacmananimcount - 1) || pacmaninicial == 0) {
				pacanimdir = -pacanimdir;
			}
		}
	}
	
	private void dibujarPacman(Graphics2D g2d) {
		if (viewdx == -1) {
			dibujarPacmanIzq(g2d);
		} else if (viewdx == 1) {
			dibujarPacmanDer(g2d);
		} else if (viewdy == 1) {
			dibujarPacmanArriba(g2d);
		} else if (viewdy == -1) {
			dibujarPacmanAbajo(g2d);
		} else {
			g2d.drawImage(pacman, pacmanx, pacmany, null);
		}
	}
	
	private void dibujarPacmanIzq(Graphics2D g2d) {

		switch (pacmaninicial) {
		case 1:
			g2d.drawImage(pacman1left, pacmanx, pacmany, null);
			break;
		case 2:
			g2d.drawImage(pacman2left, pacmanx, pacmany, null);
			break;
		case 3:
			g2d.drawImage(pacman3left, pacmanx, pacmany, null);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx, pacmany, null);
			break;
		}
	}
	
	private void dibujarPacmanDer(Graphics2D g2d) {

		switch (pacmaninicial) {
		case 1:
			g2d.drawImage(pacman1right, pacmanx, pacmany, null);
			break;
		case 2:
			g2d.drawImage(pacman2right, pacmanx, pacmany, null);
			break;
		case 3:
			g2d.drawImage(pacman3right, pacmanx, pacmany, null);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx, pacmany, null);
			break;
		}
	}
	private void dibujarPacmanArriba(Graphics2D g2d) {

		switch (pacmaninicial) {
		case 1:
			g2d.drawImage(pacman1up, pacmanx, pacmany, null);
			break;
		case 2:
			g2d.drawImage(pacman2up, pacmanx, pacmany, null);
			break;
		case 3:
			g2d.drawImage(pacman3up, pacmanx, pacmany, null);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx, pacmany, null);
			break;
		}
	}

	private void dibujarPacmanAbajo(Graphics2D g2d) {

		switch (pacmaninicial) {
		case 1:
			g2d.drawImage(pacman1down, pacmanx, pacmany, null);
			break;
		case 2:
			g2d.drawImage(pacman2down, pacmanx, pacmany, null);
			break;
		case 3:
			g2d.drawImage(pacman3down, pacmanx, pacmany, null);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx, pacmany, null);
			break;
		}
	}

	

	
	
}
