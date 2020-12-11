package pacman2;

import java.awt.Graphics2D;

public class Pacman extends Laberinto {
	int pantalla;
	int pacmanx = 0 * blocksize; // Posicion Pacman
	int pacmany = 0 * blocksize; // Posicion Pacman
	int reqdx = 0; // Direccion de movimiento pacman
	int reqdy = 0; // Direccion de movimiento pacman
	int pacmandx = 0; // Direccion de movimiento pacman
	int pacmandy = 0; // Direccion de movimiento pacman
	int viewdx = 0; // Direcion a la que mira el pacman
	int viewdy = 0; // Dirrecion a la que mira el pacman
	int pacmanspeed = 2;
	int xpacman;
	int ypacman;
	Ghostred ghost;

	public int getPacmanx() {
		return pacmanx;
	}


	public void setPacmanx(int pacmanx) {
		this.pacmanx = pacmanx;
	}


	public int getPacmany() {
		return pacmany;
	}


	public void setPacmany(int pacmany) {
		this.pacmany = pacmany;
	}

	Sonidos sonidos;
	private final int pacmandelay = 4;
	private final int PacmanCantSprites = 4;
	private int pacanimcount = pacmandelay;
	private int pacanimdir = 1;
	private int pacmaninicial = 0;


	public Pacman(int[] screendata) {
		super(screendata);
		cargarImagenes();
		cargarSonidos();
		
	}


	private void cargarSonidos() {
		try {
			sonidos = new Sonidos();
			sonidos.agregarSonido("eat-dot", "sounds/eating-dot.wav");
			sonidos.agregarSonido("eat-cherry", "sounds/eating-cherry.wav");
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
		
	}

	public void moverPacman() {
		
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
				Juego.puntaje++;
				 //sonidos.tocarSonido("eat-dot");
			}

			if ((ch & 32) != 0) {
				screendata[pos] = (short) (screendata[pos] - 32);
				Juego.puntaje += 20;
				 //sonidos.tocarSonido("eat-cherry");
			}
 			if (reqdx != 0 || reqdy != 0) {
				if (!((reqdx == -1 && reqdy == 0 && (ch & 1) != 0) || (reqdx == 1 && reqdy == 0 && (ch & 4) != 0)
						|| (reqdx == 0 && reqdy == -1 && (ch & 2) != 0)
						|| (reqdx == 0 && reqdy == 1 && (ch & 8) != 0))) {
					pacmandx = reqdx;
					pacmandy = reqdy;
					viewdx = pacmandx;
					viewdy = pacmandy;
				}
			}
			if ((pacmandx == -1 && pacmandy == 0 && (ch & 1) != 0) || (pacmandx == 1 && pacmandy == 0 && (ch & 4) != 0)
					|| (pacmandx == 0 && pacmandy == -1 && (ch & 2) != 0)
					|| (pacmandx == 0 && pacmandy == 1 && (ch & 8) != 0)) {
				pacmandx = 0;
				pacmandy = 0;
			}
		}
		pacmanx = pacmanx + pacmanspeed * pacmandx;
		pacmany = pacmany + pacmanspeed * pacmandy;
		
		setPacmanx(pacmanx);
		setPacmany(pacmany);
		
		//System.out.println("X:"+pacmanx + " Y:" + pacmany);
		//ghost.moveGhosts(getPacmanx(), getPacmany());
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
}
