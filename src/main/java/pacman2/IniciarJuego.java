package pacman2;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class IniciarJuego {

	public static void main(String[] args) throws InterruptedException {
		int vidas = 3;
		int fantasmas = 4;
		JFrame ventana = new JFrame("Pacman 2.0");
		ventana.setSize(500, 500);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		Juego juego = new Juego(520,580, vidas, fantasmas);
		ventana.add(juego);
		ventana.addKeyListener(juego);
		ventana.pack();
		Thread threadJuego = new Thread(juego);
		threadJuego.start();
	}

}
