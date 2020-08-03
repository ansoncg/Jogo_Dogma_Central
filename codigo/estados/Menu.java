package codigo.estados;

import codigo.graficos.Elemento;
import java.awt.Graphics;
import codigo.jogo.Jogo;

public class Menu extends Estado {
   
   private int mouseX, mouseY;

   public Menu (Jogo jogo) {
      super (jogo);
   }

   @Override 
   public int atualizar () {
      mouseX = (int) jogo.getGerenciadorMouse ().getMouseX ();
      mouseY = (int) jogo.getGerenciadorMouse ().getMouseY ();
      if (mouseX > 1045 && mouseX < 1225 && mouseY > 525 && mouseY < 590 && jogo.getGerenciadorMouse ().estaApertado ())  
         jogo.dificuldade = "normal";
      if (mouseX > 1045 && mouseX < 1225 && mouseY > 605 && mouseY < 670 && jogo.getGerenciadorMouse ().estaApertado ())  
         jogo.dificuldade = "dificil";
      if (mouseX > 525 && mouseX < 710 && mouseY > 570 && mouseY < 635 && jogo.getGerenciadorMouse ().estaApertado ())  
         return 2;
      return 0;
   }

   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.menu, 0, 0, null);
      switch (jogo.dificuldade) {
         case "normal":
            graficos.drawImage (Elemento.escolha, 1042, 525, null);
            break;
         case "dificil":
            graficos.drawImage (Elemento.escolha, 1042, 605, null);
            break;
      }
      if (mouseX > 525 && mouseX < 710 && mouseY > 570 && mouseY < 635) 
         graficos.drawImage (Elemento.ponto, 603, 635, null);
      else if (mouseX > 1045 && mouseX < 1225 && mouseY > 525 && mouseY < 590)  
            graficos.drawImage (Elemento.ponto, 1015, 545, null);
      else if (mouseX > 1045 && mouseX < 1225 && mouseY > 605 && mouseY < 670)  
            graficos.drawImage (Elemento.ponto, 1015, 625, null);
   }

}
