package codigo.jogo;

import codigo.visor.Visor;
import codigo.graficos.CarregadorImagens;
import codigo.graficos.Elemento;
import codigo.estados.*;
import codigo.entrada.GerenciadorTecla;
import codigo.entrada.GerenciadorMouse;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;

public class Jogo implements Runnable {

   private Visor visor = null; // Objeto com a moldura e tela do jogo.
   private Thread threadJogo;
   private boolean rodando = false; // Diz se o jogo deve ser executado.

   private int largura = 0, altura = 0; // Dimensões.
   public String nome = "Dogma Central"; // Titulo.
   public String dificuldade;
   
   private BufferStrategy bs = null;
   private Graphics graficos = null;

   /* Estados do jogo. */
   private Estado menu; // Estado 1.
   private Estado exReplicacao; // Estado 2.
   private Estado replicacao; // Estado 3.
   private Estado exTranscricao; // Estado 4.
   private Estado transcricao; // Estado 5. 
   private Estado exTraducao; // Estado 6.
   private Estado traducao; // Estado 7. 
   private Estado referencia; // Estado 8.
   
   /* Entrada */
   private GerenciadorTecla gerenciadorTecla = null;
   private GerenciadorMouse gerenciadorMouse = null;

   /* Construtor.*/
   public Jogo (String nome, int largura, int altura) {
      this.altura = altura;
      this.largura = largura;
      this.nome = nome;
      gerenciadorTecla = new GerenciadorTecla (); // Objeto que gerencia o input do teclado.
      gerenciadorMouse = new GerenciadorMouse (); // Gerencia mouse.
   }

   /* Getter do objeto do input. */
   public GerenciadorTecla getGerenciadorTecla () {
      return gerenciadorTecla;
   }

   public GerenciadorMouse getGerenciadorMouse () {
      return gerenciadorMouse;
   }
   
   /* Atualiza as variaveis do jogo. */
   private void atualizar () {

      /* Testando por input */
      gerenciadorTecla.atualizar ();

      /* Se estiver em algum estado, chama o atualizar especifico 
       * daquele estado. Testa por mudanças nos estados. */
      if (Estado.getEstado () != null)
         switch (Estado.getEstado ().atualizar ()) {
            case 1:
               Estado.setEstado (menu);
               break;
            case 2:
               Estado.setEstado (exReplicacao);
               break;
            case 3:
               Estado.setEstado (replicacao);
               break;
            case 4:
               Estado.setEstado (exTranscricao);
               break;
            case 5:
               Estado.setEstado (transcricao);
               break;
            case 6:
               Estado.setEstado (exTraducao);
               break;
            case 7:
               Estado.setEstado (traducao);
               break;
            case 8:
               Estado.setEstado (referencia);
               break;
         }
   }

   /* Desenha na tela. */
   private void desenhar () {
      bs = visor.getTela ().getBufferStrategy (); 

      if (bs == null) { // Se não tem buffer ainda.
         visor.getTela ().createBufferStrategy (3); // Cria os buffers (3).
         return;
      }
      graficos = bs.getDrawGraphics ();

      // Inicio desenho.
      
      /* Se estiver em algum estado, chama o desenhar especifico
       * daquele estado. */
      if (Estado.getEstado () != null)
         Estado.getEstado ().desenhar (graficos);
      
      // Fim desenho.
      
      bs.show (); // Mostra realmente o que foi desenhado.
      Toolkit.getDefaultToolkit ().sync (); // Evita travamentos ao desenhar.
      graficos.dispose ();
   }

   /* Inializa as variaveis do jogo. */
   public void iniciarVars () {
      // Adicionando entrada de teclado e mouse.
      visor = new Visor (nome, largura, altura);
      visor.getMoldura ().addKeyListener (gerenciadorTecla);
      visor.getMoldura ().addMouseListener (gerenciadorMouse);
      visor.getMoldura ().addMouseMotionListener (gerenciadorMouse);
      visor.getTela ().addMouseListener (gerenciadorMouse);
      visor.getTela ().addMouseMotionListener (gerenciadorMouse);

      Elemento.iniciarElementos (); 

      // Criando estados.
      menu = new Menu (this);   
      exReplicacao = new exReplicacao (this);
      replicacao = new Replicacao (this); 
      exTranscricao = new exTranscricao (this);
      transcricao = new Transcricao (this); 
      exTraducao = new exTraducao (this);
      traducao = new Traducao (this);
      referencia = new Referencia (this);

      Estado.setEstado (menu); // Insere o estado inicial.
   }

   /* Laço principal do jogo. run é sempre chamado no start
    * de um thread. */
   @Override
   public void run () {
      iniciarVars (); // talvez mudar para iniciar

      int fps = 60;
      double tempoPorAtt = 1000000000 / fps; // Periodo em nanosegundos.
      double variacao = 0; // Diferença entre dois momentos para saber quando atualizar.
      long agora = 0;
      long ultimoTempo = System.nanoTime (); // Pegando o tempo do momento em nano segundos.

      //long cronometro = 0; // AUXILIAR - FPS
      //int atualizacoes = 0; // AUXILIAR - FPS

      while (rodando) {
         agora = System.nanoTime ();  // Pegando o tempo de agora.

         /* Pegando a diferença entre o tempo antes e agora. Dividindo pelo tempo
          * de espera que é necessário até a proxima atualização. */
         variacao += (agora - ultimoTempo) / tempoPorAtt;

         //cronometro += agora - ultimoTempo; // AUXILIAR - FPS

         ultimoTempo = agora;

         if (variacao >= 1) { // Se passou de 1 quer dizer que é hora de atualizar e redesenhar.
            atualizar ();
            desenhar ();
            variacao--; // Volta a 0.

           // atualizacoes++; // AUXILIAR - FPS
         }

         /* // AUXILIAR - FPS
         if (cronometro >= 1000000000) {
            System.out.printf ("FPS: %d\n", atualizacoes);
            atualizacoes = 0;
            cronometro = 0;
         } */

      }
      parar (); // Para o jogo.
   }

   /* Começa o jogo */
   public synchronized void iniciar () {
      if (rodando) // Testa se já não está rodando.
         return;
      rodando = true;
      threadJogo = new Thread (this); // Cria o thread.
      threadJogo.start (); // Inicia o thread. Chamará o run.
   }
   
   /* Para o jogo */
   public synchronized void parar () {
      if (!rodando) // Testa se já não está parado.
         return;
      rodando = false;
      try { // Para o thread.
         threadJogo.join ();
      } catch (InterruptedException e) { ; }
   }

}
