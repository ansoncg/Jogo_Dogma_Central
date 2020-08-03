package codigo.estados;

import codigo.graficos.Elemento;
import java.awt.Graphics;
import codigo.jogo.Jogo;

public class exReplicacao extends Estado {
   
   private int mouseX, mouseY;

   public exReplicacao (Jogo jogo) {
      super (jogo);
   }

   @Override 
   public int atualizar () {
      mouseX = (int) jogo.getGerenciadorMouse ().getMouseX ();
      mouseY = (int) jogo.getGerenciadorMouse ().getMouseY ();
      if (mouseX > 510 && mouseX < 725 && mouseY > 655 && mouseY < 700 && jogo.getGerenciadorMouse ().estaApertado ())
         return 3;
      return 0;
   }

   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.explicaRepl, 0, 0, null);
      if (mouseX > 510 && mouseX < 725 && mouseY > 655 && mouseY < 700)
         graficos.drawImage (Elemento.ponto, 605, 625, null);
      else if (mouseX > 125 && mouseX < 310 && mouseY > 585 && mouseY < 610)
         graficos.drawImage (Elemento.slideReplThis, 0, 0, null);
      else if (mouseX > 430 && mouseX < 630 && mouseY > 585 && mouseY < 610)
         graficos.drawImage (Elemento.slideRplJogar, 0, 0, null);
      else if (mouseX > 760 && mouseX < 890 && mouseY > 585 && mouseY < 610)
         graficos.drawImage (Elemento.slideReplEnzima, 0, 0, null);
      else if (mouseX > 1045 && mouseX < 1120 && mouseY > 585 && mouseY < 610)
         graficos.drawImage (Elemento.slideReplDNA, 0, 0, null);
   }
}
