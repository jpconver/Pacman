package pacman2;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class IniciarJuego {

	public static void main(String[] args) throws InterruptedException {
		JFrame ventana = new JFrame("Pacman 2.0");
		ventana.setSize(500, 500);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		Juego juego = new Juego();
		juego.laberinto.iniciarNivel();
		ventana.add(juego);
		ventana.addKeyListener(juego);
		while(true) {
			juego.mover();
			juego.repaint();
			Thread.sleep(10);
		}
		
		
	}

}
