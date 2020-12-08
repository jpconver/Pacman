package pacman2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Pacman extends Laberinto {

	
	int pacmanx = 7 * blocksize;	//Posicion Pacman
	int pacmany = 10 * blocksize;	//Posicion Pacman
	int reqdx = 0;	//Direccion de movimiento pacman
	int reqdy = 0;	//Direccion de movimiento pacman
	int pacmandx = 0; //Direccion de movimiento pacman
	int pacmandy = 0; //Direccion de movimiento pacman
	int viewdx = 0;	//Direcion a la que mira el pacman
	int viewdy = 0; //Dirrecion a la que mira el pacman
	int pacmanspeed = 2;
	
	Sonidos sonidos;
	int vidas;
	private int score = 0;
	private final int pacmandelay = 4;
	private final int PacmanCantSprites = 4;
	private int pacanimcount = pacmandelay;
	private int pacanimdir = 1;
	private int pacmaninicial = 0;
	private final Font smallfont = new Font("Helvetica", Font.BOLD, 14);
	
	
	public Pacman(int cantVidas, int[] screendata2) {
		super(screendata2);
		cargarImagenes();
		this.vidas = cantVidas;
		cargarSonidos();
	}
	private void cargarSonidos() {
        try {
            sonidos = new Sonidos();
            //sonidos.agregarSonido("eat-dot", "sounds/eating-dot.wav");
            //sonidos.agregarSonido("eat-cherry", "sounds/eating-cherry.wav");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }


	private void cargarImagenes() {
			pacman = Imagenes.loadImage("sprites/pacman.png");
			pacman1 = Imagenes.loadImage("sprites/pacman1.png");
			pacman1up = Imagenes.loadImage("sprites/up1.png");
			pacman2up = Imagenes.loadImage("sprites/up2.png");
			pacman3up = Imagenes.loadImage("sprites/up3.png");
			pacman1down = Imagenes.loadImage("sprites/down1.png");
			pacman2down = Imagenes.loadImage("sprites/down2.png");
			pacman3down = Imagenes.loadImage("sprites/down3.png");
			pacman1left = Imagenes.loadImage("sprites/left1.png");
			pacman2left = Imagenes.loadImage("sprites/left2.png");
			pacman3left = Imagenes.loadImage("sprites/left3.png");
			pacman1right = Imagenes.loadImage("sprites/right1.png");
			pacman2right = Imagenes.loadImage("sprites/right2.png");
			pacman3right = Imagenes.loadImage("sprites/right3.png");
			//cereza = loadImage("sprites/cereza.png");
	}

	public void moverse() {
		int pos;
		int ch;
		
		
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
            	screendata[pos] = (short) (screendata[pos] - 16);
                score++;
                sonidos.tocarSonido("eat-dot");
            }
            
            if ((ch & 32) != 0) {
            	screendata[pos] = (short) (screendata[pos] - 32);
                score += 20;
                sonidos.tocarSonido("eat-cherry");
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
	
	void drawScore(Graphics2D g) {
		int i = 0;
		g.setFont(smallfont);
        g.setColor(Color.yellow);
		g.drawString("Vidas restantes: ", 15 + i * 30, scrsize + 13);
		g.setColor(Color.green);
    	for (i = 0; i < vidas; i++) {
            g.drawImage(pacman2left, i*30 + 15, scrsize + 15,null);
    	}
        g.drawString("Puntuación: " + score, scrsize / 2 + 96, scrsize + 16);
    }
	
	

	
}
