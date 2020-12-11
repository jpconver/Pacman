package pacman2;

import java.awt.Graphics;
import java.awt.Image;

public class Ghostred extends Laberinto {

	private int cantidadFantasmas;
	// private int pantalla;
	Image fantasmarojo;
	private int maxghosts = 1;
	private int[] ghostx = new int[maxghosts];
	private int[] ghostdx = new int[maxghosts];
	private int[] ghosty = new int[maxghosts];
	private int[] ghostdy = new int[maxghosts];
	private int[] ghostspeed = new int[maxghosts];
	private int[] dx = new int[4];
	private int[] dy = new int[4];
	private int ghostX, ghostY;
	private int r= 0;
	Pacman pacman;
	boolean colision;
	Image fantasmaRojo;
	private final int validspeeds[] = { 1, 1 };
	private int currentspeed = 0;
	private int pantalla;

	public Ghostred(int[] screendata, int cantidadFantasmas, int pantalla) {
		super(screendata);
		this.cantidadFantasmas = cantidadFantasmas;
		this.pantalla = pantalla;
		iniciarVariables();
		fantasmarojo = Imagenes.loadImage("sprites/ghostred.png");
	}

	void iniciarVariables() {
		int i;
		int dx = 1;
		int random;

		for (i = 0; i < cantidadFantasmas; i++) {
			ghostx[i] = 7 * blocksize;
			ghosty[i] = 10 * blocksize;
			ghostdy[i] = 0;
			ghostdx[i] = dx;
			dx = -dx;
			random = (int) (Math.random() * (currentspeed));

			ghostspeed[i] = validspeeds[random];
		}
	}

	boolean moveGhosts(int pacmanx, int pacmany) {

		int i;
		int pos;
		int count;

		for (i = 0; i < cantidadFantasmas; i++) {
			if (ghostx[i] % blocksize == 0 && ghosty[i] % blocksize == 0) {
				pos = ghostx[i] / blocksize + nrofblocks * (int) (ghosty[i] / blocksize);

				count = 0;

				if ((screendata[pos] & 1) == 0 && ghostdx[i] != 1) {
					dx[count] = -1;
					dy[count] = 0;
					count++;
				}

				if ((screendata[pos] & 2) == 0 && ghostdy[i] != 1) {
					dx[count] = 0;
					dy[count] = -1;
					count++;
				}

				if ((screendata[pos] & 4) == 0 && ghostdx[i] != -1) {
					dx[count] = 1;
					dy[count] = 0;
					count++;
				}

				if ((screendata[pos] & 8) == 0 && ghostdy[i] != -1) {
					dx[count] = 0;
					dy[count] = 1;
					count++;
				}

				if (count == 0) {

					if ((screendata[pos] & 15) == 15) {
						ghostdx[i] = 0;
						ghostdy[i] = 0;
					} else {
						ghostdx[i] = -ghostdx[i];
						ghostdy[i] = -ghostdy[i];
					}

				} else {

					count = (int) (Math.random() * count);

					if (count > 3) {
						count = 3;
					}

					ghostdx[i] = dx[count];
					ghostdy[i] = dy[count];
				}

			}

			ghostx[i] = ghostx[i] + (ghostdx[i] * ghostspeed[i]);
			ghosty[i] = ghosty[i] + (ghostdy[i] * ghostspeed[i]);
			
			ghostX = ghostx[i];
			ghostY = ghosty[i];
			
			if (pacmanx > (ghostx[i] - 12) && pacmanx < (ghostx[i] + 12)
					&& pacmany > (ghosty[i] - 12) && pacmany < (ghosty[i] + 12)) {
					//System.out.println("Pacman Tocado por ROJO" + r);
					colision = true;
			}
		}
		return colision;
	}
	
	public void drawGhost(Graphics g2d) {
		for (int i = 0; i < cantidadFantasmas; i++) {
			g2d.drawImage(fantasmarojo, ghostX, ghostY, null);
		}
	}

}
