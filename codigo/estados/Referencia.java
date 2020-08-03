package codigo.estados;

import codigo.graficos.Elemento;
import java.awt.Graphics;
import codigo.jogo.Jogo;

public class Referencia extends Estado {
   
   private int mouseX, mouseY;

   public Referencia (Jogo jogo) {
      super (jogo);
   }

   @Override 
   public int atualizar () {
      mouseX = (int) jogo.getGerenciadorMouse ().getMouseX ();
      mouseY = (int) jogo.getGerenciadorMouse ().getMouseY ();
      if (mouseX > 440 && mouseX < 790 && mouseY > 550 && mouseY < 650 && jogo.getGerenciadorMouse ().estaApertado ()) {
         jogo.getGerenciadorMouse ().negaClick (); 
         return 1;
      }
      return 0;
   }

   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.referencia, 0, 0, null);
      if (mouseX > 440 && mouseX < 790 && mouseY > 550 && mouseY < 650)
         graficos.drawImage (Elemento.ponto, 600, 520, null);
   }
}
