package codigo.estados;

import java.awt.Graphics;
import codigo.graficos.Elemento;
import codigo.entidades.Sequencia;
import codigo.jogo.Jogo;

/* Implementação de um estado do jogo. */
public class Replicacao extends Estado {

   private Sequencia dnaMolde;
   private Sequencia dnaNovo;
   private String codingStrand;
   private String templateStrand;
   private boolean perdeu, ganhou, reset;
  
   public Replicacao (Jogo jogo) {
      super (jogo);
      reset ();
      reset = true;
   }

   /* Nesse estado o jogo tem que atualizar esses componentes. */
   @Override
   public int atualizar () {
      if (reset) {
         reset ();
         reset = false;
      }
      if (!perdeu && !ganhou) {
         dnaMolde.atualizar ();
         switch (dnaNovo.atualizar ()) {
            case 1:
               ganhou = true;
               break;
            case -1:
               perdeu = true;
               break;
         }
      }
      else if (perdeu) {
         if (jogo.getGerenciadorTecla ().tecla_R)
            reset = true;
      }
      else if (ganhou) {
         mouseX = (int) jogo.getGerenciadorMouse ().getMouseX ();
         mouseY = (int) jogo.getGerenciadorMouse ().getMouseY ();
         if (mouseX > 785 && mouseX < 1000 && mouseY > 320 && mouseY < 370 && jogo.getGerenciadorMouse ().estaApertado ()) {  
            reset = true;
            return 4;
         }
      }
      return 0;
   }

   /* Nesse estado o jogo tem que desenhar esses componentes. */
   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.fundo, 0, 0, null);
      graficos.drawImage (Elemento.restoRepl, 0, 20, null);
      graficos.drawImage (Elemento._5linha, 0, 380, null); 
      graficos.drawImage (Elemento._3linha, 1220, 380, null); 
      graficos.drawImage (Elemento._3linha, 0, 633, null); 
      graficos.drawImage (Elemento._5linha, 1220, 633, null); 
      graficos.drawImage (Elemento.dnaG, 0, 612, null); 
      dnaMolde.desenhar (graficos);
      dnaNovo.desenhar (graficos);
      if (perdeu)
         graficos.drawImage (Elemento.perdeu, 665, 270, null);
      if (ganhou) {
         graficos.drawImage (Elemento.continuar, 665, 270, null);
         if (mouseX > 785 && mouseX < 1000 && mouseY > 320 && mouseY < 370)   
            graficos.drawImage (Elemento.ponto, 870, 368, null);
      }
   }

   private void reset () {
      codingStrand = Elemento.getSeq (jogo.dificuldade);
      templateStrand = Sequencia.getTemplateStrand (codingStrand);
      dnaMolde = new Sequencia (jogo, "DNA", templateStrand, true, 0, 560); 
      dnaNovo = new Sequencia (jogo, "DNA", templateStrand, false, 0, 460); 
      switch (jogo.dificuldade) {
         case "normal":
            dnaMolde.setVelocidade (1.0f);
            dnaNovo.setVelocidade (1.0f);
            break;
         case "dificil":
            dnaMolde.setVelocidade (2.0f);
            dnaNovo.setVelocidade (2.0f);
            break;
      }
      perdeu = false;
      ganhou = false;
   }
}
