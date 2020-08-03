package codigo.estados;

import java.awt.Graphics;
import codigo.jogo.Jogo;

public abstract class Estado {
   
   private static Estado estadoAtual = null;
   protected Jogo jogo = null;
   public int mouseX, mouseY;

   public Estado (Jogo jogo) {
      this.jogo = jogo;
      mouseX = 0;
      mouseY = 0;
      jogo.dificuldade = "normal";
   }

   /* Set e get do Estado. */
   public static void setEstado (Estado estado) {
      estadoAtual = estado;
   } 

   public static Estado getEstado () {
      return estadoAtual;
   }

   /* Elementos que todo estado precisa ter. */
   public abstract int atualizar ();   
   public abstract void desenhar (Graphics graficos);

}
