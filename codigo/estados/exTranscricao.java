package codigo.estados;

import codigo.graficos.Elemento;
import java.awt.Graphics;
import codigo.jogo.Jogo;

public class exTranscricao extends Estado {
   
   private int mouseX, mouseY;

   public exTranscricao (Jogo jogo) {
      super (jogo);
   }

   @Override 
   public int atualizar () {
      mouseX = (int) jogo.getGerenciadorMouse ().getMouseX ();
      mouseY = (int) jogo.getGerenciadorMouse ().getMouseY ();
      if (mouseX > 535 && mouseX < 750 && mouseY > 595 && mouseY < 645 && jogo.getGerenciadorMouse ().estaApertado ())
         return 5;
      return 0;
   }

   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.explicaTransc, 0, 0, null);
      if (mouseX > 535 && mouseX < 750 && mouseY > 595 && mouseY < 645)
         graficos.drawImage (Elemento.ponto, 626, 570, null);
      else if (mouseX > 205 && mouseX < 415 && mouseY > 525 && mouseY < 555)
         graficos.drawImage (Elemento.slideTrcThis, 0, 0, null);
      else if (mouseX > 545 && mouseX < 740 && mouseY > 525 && mouseY < 555)
         graficos.drawImage (Elemento.slideTrcJogar, 0, 0, null);
      else if (mouseX > 870 && mouseX < 950 && mouseY > 525 && mouseY < 555)
         graficos.drawImage (Elemento.slideTrcRNA, 0, 0, null);
   }
}


