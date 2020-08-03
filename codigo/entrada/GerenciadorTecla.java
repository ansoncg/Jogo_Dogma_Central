package codigo.entrada;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/* Cuida das operações do teclado. */
public class GerenciadorTecla implements KeyListener {
   
   private boolean teclas[];
   public boolean tecla_A, tecla_T, tecla_C, tecla_G, tecla_U, tecla_R;
   public boolean apertado = false;

   public GerenciadorTecla () {
      teclas = new boolean[1024];
   }

   public void atualizar () {
     tecla_A = teclas[KeyEvent.VK_A]; 
     tecla_T = teclas[KeyEvent.VK_T]; 
     tecla_C = teclas[KeyEvent.VK_C]; 
     tecla_G = teclas[KeyEvent.VK_G]; 
     tecla_U = teclas[KeyEvent.VK_U]; 
     tecla_R = teclas[KeyEvent.VK_R]; 
   }

   @Override
   public void keyPressed (KeyEvent e) {
      teclas[e.getKeyCode ()] = true;   
      if (teclas[KeyEvent.VK_A] || teclas[KeyEvent.VK_T] || teclas[KeyEvent.VK_C] || teclas[KeyEvent.VK_G] || teclas[KeyEvent.VK_U]) 
         apertado = true;
   }

   @Override
   public void keyReleased (KeyEvent e) {
      teclas[e.getKeyCode ()] = false;   
      apertado = false;
   }

   @Override
   public void keyTyped (KeyEvent e) {

   }
}
