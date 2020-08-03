package codigo.entrada;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GerenciadorMouse implements MouseListener, MouseMotionListener {

   private boolean apertado;
   private int mouseX, mouseY;
   public GerenciadorMouse () {
      apertado = false;
      mouseX = 0;
      mouseY = 0;
   }

   public boolean estaApertado () {
      return apertado;
   }

   public int getMouseX () {
      return mouseX;
   }

   public int getMouseY () {
      return mouseY;
   }

   public void negaClick () {
      apertado = false;
   }

   @Override
   public void mouseMoved (MouseEvent e) {
      mouseX = e.getX ();
      mouseY = e.getY ();
   }

   @Override
   public void mousePressed (MouseEvent e) {
      if (e.getButton () == MouseEvent.BUTTON1)
         apertado = true;
   }

   @Override
   public void mouseReleased (MouseEvent e) {
      if (e.getButton () == MouseEvent.BUTTON1)
         apertado = false;
   }

   @Override
   public void mouseClicked (MouseEvent e) {

   }
   
   @Override
   public void mouseEntered (MouseEvent e) {

   }

   @Override
   public void mouseDragged (MouseEvent e) {

   }


   @Override
   public void mouseExited (MouseEvent e) {

   }
}
