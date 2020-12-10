package pacman2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Lifes{
	private int vidas;
	private int ancho;
	private int alto;
	

	static Image pacman2left = Imagenes.loadImage("sprites/left2.png");

	public Lifes(int cantVidas, int ancho, int alto) {
		this.vidas = cantVidas;
		this.ancho = ancho;
		this.alto = alto;
	}

	void drawLifes(Graphics2D g) {

		g.setColor(Color.yellow);
		g.drawString("Vidas restantes: ", ancho - 500, alto - 40);
		for (int i = 0; i < vidas ; i++) {
            g.drawImage(pacman2left, ancho - 500 + i * 30, alto - 35, null);
        }
	}	
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	void perderVida() {
		vidas--;
	}
}

