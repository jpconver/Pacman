package pacman2;

import java.awt.Graphics;
import java.awt.Image;

public class Ghostgreen extends Laberinto {

	private int cantidadFantasmas;
	// private int pantalla;
	Image fantasmaverde;
	//private int maxghosts = 1;
	Lifes lifes;
	private int ghostx = 8 * blocksize;
	private int ghostdx;  
	private int ghosty = 10 * blocksize;
	private int ghostdy;
	int level;
	private int ghostspeed = 1;
	private int[] dx = new int[4];
	private int[] dy = new int[4];
	private int ghostX, ghostY;
	Pacman pacman;
	boolean colision;
	//private final int validspeeds[] = { 1, 1 };


	public Ghostgreen(int[] screendata, int cantidadFantasmas, int level) {
		super(screendata);
		this.cantidadFantasmas = cantidadFantasmas;
		this.level = level;
		iniciarVariables();
		fantasmaverde = Imagenes.loadImage("sprites/ghostgreen.png");
	}

	void iniciarVariables() {
		int dx = 1;
		int random;
		ghostdy = 0;
		ghostdx = dx;
		dx = -dx;
		random = (int) (Math.random());

			//ghostspeed = validspeeds[random];
	}

	boolean moveGhosts(int pacmanx, int pacmany, int vidas) {

		int i;
		int pos;
		int count;

		for (i = 0; i < cantidadFantasmas; i++) {
			if (ghostx % blocksize == 0 && ghosty % blocksize == 0) {
				pos = ghostx / blocksize + nrofblocks * (int) (ghosty / blocksize);

				count = 0;

				if ((screendata[pos] & 1) == 0 && ghostdx != 1) {
					dx[count] = -1;
					dy[count] = 0;
					count++;
				}

				if ((screendata[pos] & 2) == 0 && ghostdy != 1) {
					dx[count] = 0;
					dy[count] = -1;
					count++;
				}

				if ((screendata[pos] & 4) == 0 && ghostdx != -1) {
					dx[count] = 1;
					dy[count] = 0;
					count++;
				}

				if ((screendata[pos] & 8) == 0 && ghostdy != -1) {
					dx[count] = 0;
					dy[count] = 1;
					count++;
				}

				if (count == 0) {

					if ((screendata[pos] & 15) == 15) {
						ghostdx = 0;
						ghostdy = 0;
					} else {
						ghostdx = -ghostdx;
						ghostdy = -ghostdy;
					}

				} else {

					count = (int) (Math.random() * count);

					if (count > 3) {
						count = 3;
					}

					ghostdx = dx[count];
					ghostdy = dy[count];
				}

			}

			ghostx = ghostx + (ghostdx * ghostspeed);
			ghosty = ghosty + (ghostdy * ghostspeed);
			ghostX = ghostx;
			ghostY = ghosty;

			if (pacmanx > (ghostx - 12) && pacmanx < (ghostx + 12)
				&& pacmany > (ghosty - 12) && pacmany < (ghosty + 12)) {
				colision = true;
			}
		}
		return colision;
	}

	public boolean getColision() {
		return true;
	}
	
	
	public void drawGhost(Graphics g2d) {
		for (int i = 0; i < cantidadFantasmas; i++) {
			g2d.drawImage(fantasmaverde, ghostX, ghostY, null);
		}
	}

}
