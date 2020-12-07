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
	public Image pacman, pacman1, pacman1up, pacman1left, pacman1right, pacman1down;
	public Image pacman2up, pacman2down, pacman2left, pacman2right;
	public Image pacman3up, pacman3down, pacman3left, pacman3right;
	private Image cherry = Imagenes.loadImage("sprites/cherry.png");
	
	public Laberinto(int[] screendata) {
		this.screendata = screendata;
		
	}

	public void laberinto() {
	}
	
	final int blocksize = 30;
	final int nrofblocks = 16;
	protected final int scrsize = nrofblocks * blocksize;
	private Color colorLaberinto = new Color(0, 0, 255);
	private final Color dotcolor = new Color(255, 255, 255);
	//private int r;
	List<Integer> frutas = Arrays.asList(16, 16, 16, 16, 16, 16, 32);
	Random aleatorio = new Random();
	int a = frutas.get(aleatorio.nextInt(frutas.size()));
	int b = frutas.get(aleatorio.nextInt(frutas.size()));
	int c = frutas.get(aleatorio.nextInt(frutas.size()));
	int d = frutas.get(aleatorio.nextInt(frutas.size()));
	int e = frutas.get(aleatorio.nextInt(frutas.size()));
	int f = frutas.get(aleatorio.nextInt(frutas.size()));
	int g = frutas.get(aleatorio.nextInt(frutas.size()));
	

	protected int datosLaberinto[] = { // Funciona segun cantidad de bloques (nrofblocks = 15)
			19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
	        17, 16, 16, 16, 16, a, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 27, 24, 24, 17, 16, 16, 16, 16, 16, 16, g, 16, 16, 20,
	        17, 16, 4, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 20, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 20, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 18, 18, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, d, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, f, 16, 20,
	        17, 16, 16, e, 16, 16, 16, 9, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, c, 16, 16, 16, 16, 16, 16, 16, 16, b, 16, 16, 16, 20,
	        25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28};
	
	
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
                    g2d.fillOval(x + 11, y + 11, 3, 3);
                }
                
                if ((screendata[i] & 32) != 0) {
                    g2d.drawImage(cherry, x, y, null);
                }
                i++;
            }
        }
        
    }
	

}
