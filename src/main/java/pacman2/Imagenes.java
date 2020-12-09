package pacman2;

import java.awt.Image;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Imagenes {

	public static Image loadImage(String resourceName) {
		try {
			URI uri = ClassLoader.getSystemResource(resourceName).toURI();
			String mainPath = Paths.get(uri).toString();
			return ImageIO.read(new File(mainPath));
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	
}
