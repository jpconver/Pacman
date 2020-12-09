package pacman2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score {

	private final static Font FONT = new Font("Helvetica", Font.BOLD, 14);

	private int alto;
	private int ancho;
	
	public Score(int anchoJuego, int altoJuego) {
		this.alto = altoJuego;
		this.ancho = anchoJuego;
	}

	public void drawScore(Graphics2D g) {
		g.setFont(FONT);
		g.setColor(Color.green);
		g.drawString("Puntuación: " + Juego.puntaje, ancho - 150, alto - 40);
	}
}
