package pacman2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Laberinto {
	
	public void laberinto() {

	}
	
	final int blocksize = 30;
	final int nrofblocks = 16;
	private final int scrsize = nrofblocks * blocksize;
	private Color colorLaberinto = new Color(0, 0, 255);
	private final Color dotcolor = new Color(255, 255, 255);
	private final Color dotcolor2 = new Color(255, 0, 0);
	

	final short datosLaberinto[] = { // Funciona segun cantidad de bloques (nrofblocks = 15)
			19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 32, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
	        9, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28};

	public short[] getScreendata() {
		return datosLaberinto;
	}
	
	void dibujarLaberinto(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < scrsize; y += blocksize) {
            for (x = 0; x < scrsize; x += blocksize) {

                g2d.setColor(colorLaberinto);
                g2d.setStroke(new BasicStroke(2));

                if ((getScreendata()[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + blocksize - 1);
                }

                if ((getScreendata()[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + blocksize - 1, y);
                }

                if ((getScreendata()[i] & 4) != 0) {
                    g2d.drawLine(x + blocksize - 1, y, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((getScreendata()[i] & 8) != 0) {
                    g2d.drawLine(x, y + blocksize - 1, x + blocksize - 1,
                            y + blocksize - 1);
                }

                if ((getScreendata()[i] & 16) != 0) {
                    g2d.setColor(dotcolor);
                    g2d.fillOval(x + 11, y + 11, 3, 3);
                }
                
                if ((getScreendata()[i] & 32) != 0) {
                    g2d.setColor(dotcolor2);
                    g2d.fillOval(x + 8, y + 7, 10, 10);
                }
                i++;
            }
        }
    }


}
