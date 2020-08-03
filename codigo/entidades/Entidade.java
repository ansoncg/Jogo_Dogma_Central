package codigo.entidades;

import java.awt.Graphics;

public abstract class Entidade {

   protected float x = 0.0f, y = 0.0f;

   public Entidade (float x, float y) {
      this.x = x;
      this.y = y;
   }

   public abstract int atualizar ();
   public abstract void desenhar (Graphics graficos);

}
