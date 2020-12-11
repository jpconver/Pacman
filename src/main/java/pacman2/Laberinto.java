package pacman2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Laberinto {
	public int[] screendata;
	public Image fantasmarojo, fantasmaazul, fantasmaverde, fantasmaamarillo, fantasmasrosa;
	public Image pacman, pacman1, pacman1up, pacman1left, pacman1right, pacman1down;
	public Image pacman2up, pacman2down, pacman2left, pacman2right;
	public Image pacman3up, pacman3down, pacman3left, pacman3right;
	private Image cherry = Imagenes.loadImage("sprites/cherry.png");
	
	public Laberinto(int[] screendata) {
		this.screendata = screendata;
	}
	protected final int blocksize = 26;
	final int nrofblocks = 20;
	protected final int scrsize = nrofblocks * blocksize;
	private Color colorLaberinto = new Color(10, 127, 148);
	private final Color dotcolor = new Color(155, 155, 155);
	List<Integer> frutas = Arrays.asList(16, 16, 16, 16, 16, 32);
	Random aleatorio = new Random();
	int a = frutas.get(aleatorio.nextInt(frutas.size()));
	int b = frutas.get(aleatorio.nextInt(frutas.size()));
	int c = frutas.get(aleatorio.nextInt(frutas.size()));
	int d = frutas.get(aleatorio.nextInt(frutas.size()));
	int e = frutas.get(aleatorio.nextInt(frutas.size()));
	int f = frutas.get(aleatorio.nextInt(frutas.size()));
	int g = frutas.get(aleatorio.nextInt(frutas.size()));
	
	

	protected int level_1[] = { 
			 3, 26, 26, 18, 26, 26, 22,  0, 19, 26, 26, 26, 26, 18, 26, 26, 26, 26, 22,  6,
		    21,  0,  0, 21,  0,  0, 21,  0, 21,  0,  0,  0,  0, 21,  0,  0,  0,  0, 21,  4,
		    17, 18, 18, 24, 18, 26, 24, 26, 16, 26, 26, 26, 18, 24, 18, 26, 26, 26, 20,  4,
		    17, 24, 20,  0, 21,  0,  0,  0, 21,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  4,
		    21,  0, 21,  0, 21,  0, 19, 26, 28,  0, 19, 26, 28,  0, 25, 26, 22,  0, 21,  4,
		    29,  0, 21,  0, 21,  0, 21,  0,  0,  0, 21,  0,  0,  0,  0,  0, 21,  0, 17, 30,
		    11,  0, 21,  0, 21,  0, 17, 26, 22,  0, 25, 26, 22,  0, 19, 26, 28,  0, 21,  4,
		    23,  0, 17, 26, 24, 26, 20,  0, 21,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  4,
		    21,  0, 21,  0,  0,  0, 21,  0, 17, 18, 18, 18, 16, 26, 16, 26, 26, 26, 20,  4,
		    17, 26, 28,  0, 23,  0, 17, 26, 24, 24, 24, 24, 20,  0, 21,  0,  0,  0, 21,  4,
		    21,  0,  0,  0, 21,  0, 21, 11, 10,  8, 10, 14, 21,  0, 21,  0, 23,  0, 21,  4,
		    17, 26, 26, 26, 16, 26, 16, 18, 18, 18, 18, 18, 16, 26, 16, 26, 24, 26, 16, 30,
		    21,  0,  0,  0, 21,  0, 25, 24, 24, 24, 24, 24, 28,  0, 21,  0,  0,  0, 21,  4,
		    21,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0,  0,  0,  0, 21,  0,  0,  0, 21,  4,
		    17, 26, 26, 26, 16, 26, 18, 26, 26, 26, 26, 26, 18, 26, 16, 26, 26, 26, 20,  4,
		    21,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0, 21,  4,
		    21,  0, 19, 26, 28,  0, 25, 26, 22,  0, 19, 26, 28,  0, 25, 26, 22,  0, 21,  4,
		    21,  0, 21,  0,  0,  0,  0,  0, 21,  0, 21,  0,  0,  0,  0,  0, 21,  0, 21,  4,
		    25, 26, 24, 26, 26, 18, 26, 26, 24, 18, 24, 26, 26, 18, 26, 26, 24, 26, 24, 30,
		     9,  8,  8,  8,  8, 29,  8,  8,  8, 29,  8,  8,  8, 29,  8,  8,  8,  8,  8, 12};
	
	protected int level_2[] = { 
			19, 26, 26, 26, 26, 26, 26, 26, 26, 18, 18, 18, 18, 26, 26, 26, 26, 18, 18, 22,
		    21, 03, 10, 10, 10, 10, 10, 10, 14, 17, 16, 16, 20, 11, 10, 10, 06, 17, 16, 20,
		    21, 05, 19, 26, 18, 18, 18, 18, 18,  a, 16, 16,  g, 26, 18, 22, 05, 17, 16, 20,
		    21, 05, 21, 07, 17, 16, 24, 24, 16, 16, 16, 16, 20, 07, 17, 20, 05, 17, 16, 20,
		    21, 05, 21, 05, 17, 20,  3,  6, 17, 16, 16, 16, 20, 05, 17, 20, 05, 17, 16, 20,
		    21, 05, 21, 05, 17, 20,  9, 12, 17, 16, 16, 16, 20, 05, 17, 20, 05, 17, 16, 20,
		    21, 05, 21, 05, 17, 16, 18, 18, 16, 16, 24, 24, 28, 05, 17, 20, 05, 17, 16, 20,
		    21, 05, 21, 05, 17, 16, 16, 16, 16, 20, 11, 10, 10, 04, 17, 20, 05, 17, 16, 20,
		    21, 05, 21, 05, 17, 16, 16,  d, 16, 16, 18, 18, 22, 05, 17, 20, 13, 17, 16, 20,
		    21, 05, 21, 05, 17, 16, 16, 24, 24, 24, 24, 24, 20, 05, 17, 16, 18, 16, 16, 20,
		    21, 05, 21, 05, 17, 16, 20, 11, 10,  8, 10, 14, 21, 05, 17, 16, 16, 16, 16, 20,
		    21, 05, 21, 05, 17, 16, 16, 18, 18, 26, 18, 18, 20, 13, 17, 16, 16, 16, 16, 20,
		    21, 13, 21, 13, 17, 16, 16, 16, 20, 07, 17, 16, 16, 18, 16, 16, 24, 24, 24, 20,
		    17, 18, 16, 18, 16, 16, 24, 24, 28, 05, 25, 24, 24,  f, 16, 20, 03, 02, 06, 21,
		    17, 16, 16, 16, 16, 20, 11, 10, 10, 00, 10, 10, 14, 17, 16, 20,  1, 00, 04, 21,
		    17, 16, 16,  e, 16, 16, 18, 18, 22, 05, 19, 18, 18, 16, 16, 20,  9,  8, 12, 21,
		    17, 24,  c, 16, 16, 16, 16, 16, 20, 13, 17,  b, 16, 16, 16, 16, 18, 18, 18, 20,
		    21, 07, 25, 24, 24, 24, 16, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 16, 20,
		    21,  9, 10, 10, 10, 10, 21, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 14, 17, 20,
		    25, 26, 26, 26, 26, 26, 24, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 24, 28};
	
	
	void dibujarLaberinto(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < scrsize; y += blocksize) {
            for (x = 0; x < scrsize; x += blocksize) {

                g2d.setColor(colorLaberinto);
               g2d.setStroke(new BasicStroke(2));

                if ((screendata[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + blocksize - 1);
                }

                if ((screendata[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + blocksize - 1, y);
                }

                if ((screendata[i] & 4) != 0) {
                    g2d.drawLine(x + blocksize - 1, y, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((screendata[i] & 8) != 0) {
                    g2d.drawLine(x, y + blocksize - 1, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((screendata[i] & 16) != 0) {
                    g2d.setColor(dotcolor);
                    g2d.fillOval(x + 10, y + 10, 4, 4);
                }
                
                if ((screendata[i] & 32) != 0) {
                    g2d.drawImage(cherry, x, y, null);
                }
                i++;
            }
        }
        
    }
	

}
