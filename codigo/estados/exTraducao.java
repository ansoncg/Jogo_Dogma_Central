package codigo.estados;

import codigo.graficos.Elemento;
import java.awt.Graphics;
import codigo.jogo.Jogo;

public class exTraducao extends Estado {
   
   private int mouseX, mouseY;

   public exTraducao (Jogo jogo) {
      super (jogo);
   }

   @Override 
   public int atualizar () {
      mouseX = (int) jogo.getGerenciadorMouse ().getMouseX ();
      mouseY = (int) jogo.getGerenciadorMouse ().getMouseY ();
      if (mouseX > 535 && mouseX < 750 && mouseY > 610 && mouseY < 660 && jogo.getGerenciadorMouse ().estaApertado ())
         return 7;
      return 0;
   }

   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.explicaTrad, 0, 0, null);
      if (mouseX > 535 && mouseX < 750 && mouseY > 610 && mouseY < 660)
         graficos.drawImage (Elemento.ponto, 625, 585, null);
      else if (mouseX > 260 && mouseX < 375 && mouseY > 475 && mouseY < 510)
         graficos.drawImage (Elemento.slideTradCod, 0, 0, null);
      else if (mouseX > 545 && mouseX < 735 && mouseY > 475 && mouseY < 510)
         graficos.drawImage (Elemento.slideTradRib, 0, 0, null);
      else if (mouseX > 905 && mouseX < 1055 && mouseY > 475 && mouseY < 510)
         graficos.drawImage (Elemento.slideTradProt, 0, 0, null);
      else if (mouseX > 540 && mouseX < 735 && mouseY > 555 && mouseY < 585)
         graficos.drawImage (Elemento.slideTradJogar, 0, 0, null);
   }
}


