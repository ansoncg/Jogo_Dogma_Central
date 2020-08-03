package codigo.visor;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;

public class Visor {
   public int largura = 0, altura = 0;
   public String nome = "";

   private JFrame moldura = null; 
   private Canvas tela = null;
   
   /* Construtor do visor */
   public Visor (String nome, int largura, int altura) {
      this.nome = nome;
      this.altura = altura;
      this.largura = largura;
      criaTela ();
   }

   /* Getter da tela */
   public Canvas getTela () {
      return tela;
   }

   /* Getter da moldura. */
   public JFrame getMoldura () {
      return moldura;
   }

   /* Configura as variaveis da moldura e da tela */ 
   private void criaTela () {
      moldura = new JFrame (nome); // Cria objeto.
      moldura.setSize (largura, altura); // Insere altura e largura.
      moldura.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // Acaba o programa quando fechar a janela.
      moldura.setResizable (false); // Não deixa mudar de tamanho.
      moldura.setLocationRelativeTo (null); // Janela começa no centro.
      moldura.setVisible (true); // Exibe a janela.
      tela = new Canvas (); // Cria a tela.

      /* Coloca as dimensões na tela. */
      tela.setPreferredSize (new Dimension (largura, altura));
      tela.setMaximumSize (new Dimension (largura, altura));
      tela.setMinimumSize (new Dimension (largura, altura));
      tela.setFocusable (false);

      moldura.add (tela); // Adiciona a tela na moldura.
      moldura.pack (); 
   }

}
