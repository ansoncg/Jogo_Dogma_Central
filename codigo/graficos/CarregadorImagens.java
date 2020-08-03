package codigo.graficos;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class CarregadorImagens {

   public static BufferedImage carregaImagem (String caminho) {
      try {
         return ImageIO.read (CarregadorImagens.class.getResource (caminho));
      } catch (IOException e) {
        System.exit (1);
      }
      return null;
   }
}
