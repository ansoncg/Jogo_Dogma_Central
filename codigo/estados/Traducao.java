package codigo.estados;

import java.awt.Graphics;
import codigo.jogo.Jogo;
import codigo.entidades.Sequencia;
import codigo.entidades.Aminoacidos;
import codigo.graficos.Elemento;

public class Traducao extends Estado {
   
   private Sequencia rnaMensageiro = null;
   private Aminoacidos amino = null;
   private String codingStrand;
   private String mRNA;
   private boolean perdeu, ganhou, reset;

   public Traducao (Jogo jogo) {
      super (jogo);
      reset ();
      reset = true;
   }
   
   @Override
   public int atualizar () {
      if (reset) {
         reset ();
         reset = false;
      }
      if (!perdeu && !ganhou) {
         rnaMensageiro.atualizar (); 
         switch (amino.atualizar ()) {
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
         if (mouseX > 200 && mouseX < 415 && mouseY > 350 && mouseY < 400 && jogo.getGerenciadorMouse ().estaApertado ()) {  
            reset = true;
            return 8;
         }
      }
      return 0;
   }

   @Override
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.fundo, 0, 0, null);
      graficos.drawImage (Elemento.rnaG, 0, 526, null);
      graficos.drawImage (Elemento.restoTrad, 0, 0, null);
      rnaMensageiro.desenhar (graficos);
      amino.desenhar (graficos);
      if (perdeu)
         graficos.drawImage (Elemento.perdeu, 80, 300, null);
      if (ganhou) {
         graficos.drawImage (Elemento.continuar, 80, 300, null);
         if (mouseX > 200 && mouseX < 415 && mouseY > 350 && mouseY < 400)   
            graficos.drawImage (Elemento.ponto, 285, 398, null);
      }
   }

   private void reset () {
      codingStrand = Elemento.getSeq (jogo.dificuldade);
      mRNA = Sequencia.getRNA (codingStrand);
      rnaMensageiro = new Sequencia (jogo, "RNA", mRNA, true, 0, 550);
      amino = new Aminoacidos (jogo, rnaMensageiro, 700, 15); 
      switch (jogo.dificuldade) {
         case "normal":
            rnaMensageiro.setVelocidade (0.5f);
            break;
         case "dificil":
            rnaMensageiro.setVelocidade (1.0f);
            break;
      }
      perdeu = false;
      ganhou = false;
   }

}

