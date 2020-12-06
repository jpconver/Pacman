package pacman2;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Pacman extends Laberinto{

	
	int pacmanx = 7 * blocksize;
	int pacmany = 10 * blocksize;
	int reqdx = 0;
	int reqdy = 0;
	int pacmandx = 0;
	int pacmandy = 0;
	int viewdx = 0;
	int viewdy = 0;
	int pacmanspeed = 3;
	int i = 0;
	
	private final int pacmandelay = 4;
	private final int PacmanCantSprites = 4;
	private int pacanimcount = pacmandelay;
	private int pacanimdir = 1;
	private int pacmaninicial = 0;
	
	private Image pacman, pacman1, pacman1up, pacman1left, pacman1right, pacman1down;
	private Image pacman2up, pacman2down, pacman2left, pacman2right;
	private Image pacman3up, pacman3down, pacman3left, pacman3right;

	public Pacman() {
		cargarImagenes();
	}

	
	private void cargarImagenes() {
		pacman = loadImage("sprites/pacman.png");
		pacman1 = loadImage("sprites/pacman1.png");
		pacman1up = loadImage("sprites/up1.png");
		pacman2up = loadImage("sprites/up2.png");
		pacman3up = loadImage("sprites/up3.png");
		pacman1down = loadImage("sprites/down1.png");
		pacman2down = loadImage("sprites/down2.png");
		pacman3down = loadImage("sprites/down3.png");
		pacman1left = loadImage("sprites/left1.png");
		pacman2left = loadImage("sprites/left2.png");
		pacman3left = loadImage("sprites/left3.png");
		pacman1right = loadImage("sprites/right1.png");
		pacman2right = loadImage("sprites/right2.png");
		pacman3right = loadImage("sprites/right3.png");
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
		
		if (pacmanx % blocksize == 0 && pacmany % blocksize == 0) {
            pos = pacmanx / blocksize + nrofblocks * (int) (pacmany / blocksize);
            ch = getScreendata()[pos];
            
            if ((ch & 16) != 0) {
            	System.out.println("Dot comido " + i);
            	
                datosLaberinto[pos] = (short) (datosLaberinto[pos] - 16);
                i++;
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
		
		/*
		int pos;
		short ch;
		
		
        if (reqdx == -pacmandx && reqdy == -pacmandy) {
            pacmandx = reqdx;
            pacmandy = reqdy;
            viewdx = pacmandx;
            viewdy = pacmandy;
        }

        if (pacmanx % blocksize == 0 && pacmany % blocksize == 0) {
            pos = pacmanx / blocksize + nrofblocks * (int) (pacmany / blocksize);
            ch = screendata[pos];
        

            if ((ch & 16) != 0) {
                screendata[pos] = (short) (ch & 15);
                score++;
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
		hacerAnimacion();
		dibujarPacman(g2d);
	}
	
	private void hacerAnimacion() {

		pacanimcount--;

		if (pacanimcount <= 0) {
			pacanimcount = pacmandelay;
			pacmaninicial = pacmaninicial + pacanimdir;

			if (pacmaninicial == (PacmanCantSprites - 1) || pacmaninicial == 0) {
				pacanimdir = -pacanimdir;
			}
		}
	}
	
	private void dibujarPacman(Graphics2D g2d) {
		if (viewdx == -1) {
			dibujarPacmanIzq(g2d);
		} else if (viewdx == 1) {
			dibujarPacmanDer(g2d);
		} else if (viewdy == -1) {
			dibujarPacmanArriba(g2d);
		} else if (viewdy == 1) {
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
	
	private Image loadImage(String resourceName) {
		try {
			URI uri = ClassLoader.getSystemResource(resourceName).toURI();
			String mainPath = Paths.get(uri).toString();
			return ImageIO.read(new File(mainPath));
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	
}
