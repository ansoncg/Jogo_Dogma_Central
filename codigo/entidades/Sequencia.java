package codigo.entidades;

import java.awt.Graphics;
import codigo.jogo.Jogo;
import codigo.graficos.Elemento;

/* Entidade referente a sequencia de bases. */
public class Sequencia extends Entidade {

   private Jogo jogo;
  
   private String dnaSeq;
   private char[] basesChar; // Vetor de char contendo as bases.

   private char baseAtual = 'x'; // Qual base é a atual a ser processada.
   private char baseApertada = 'x'; // Qual base foi recebida de entrada.
   
   private int quantBases = 13; // Quantas bases tem que ser desenhadas na tela na fita incompleta.
   private int inicio = 0; // Numero da base que sera a primeira a ser desenhada no momento.
   private static final int tamLetra = 55; // Tamanho do desenho da base.
   private int tamanhoSeq;
   
   private float velocidade = 1.0f; // Velocidade que a sequencia anda para esquerda.

   private float quantAndou = 0.0f; // Quanto a sequencia ja andou para esquerda.
   private boolean liberado = true; // Determina se é possivel uma nova entrada.

   private boolean completa; // Determina se é uma nova fita ou já completa.
   private String tipo; // Determina se é uma fita de DNA ou RNA.
   private int quantErro = 0; // Guarda quantos erros foram feitos.

   public Sequencia (Jogo jogo, String tipo, String dnaSeq, boolean completa, float x, float y) {
      super (x, y);
      this.jogo = jogo;
      this.dnaSeq = dnaSeq;
      basesChar = dnaSeq.toCharArray ();
      tamanhoSeq = dnaSeq.length ();
      baseAtual = basesChar[quantBases + 1];
      this.completa = completa;
      this.tipo = tipo;
   }

   public int atualizar () {
      if (!completa) {
         if (inicio >= quantBases + 1 || quantErro >= 3)
            return -1;
         if (!jogo.getGerenciadorTecla ().apertado)
            liberado = true;

         if (liberado) {
            if (jogo.getGerenciadorTecla ().tecla_A) {
               baseApertada = 'T';
               liberado = false;
            } 
            else if (jogo.getGerenciadorTecla ().tecla_C) {
               baseApertada = 'G';
               liberado = false;
            }
            else if (jogo.getGerenciadorTecla ().tecla_G) {
               baseApertada = 'C';
               liberado = false;
            }
            else if (jogo.getGerenciadorTecla ().tecla_T && tipo == "DNA") {
               baseApertada = 'A';
               liberado = false;
            }
            else if (jogo.getGerenciadorTecla ().tecla_T && tipo == "RNA") {
               baseApertada = 'X';
               liberado = false;
            }
            else if (jogo.getGerenciadorTecla ().tecla_U && tipo == "RNA") {
               baseApertada = 'A';
               liberado = false;
            }
            else if (jogo.getGerenciadorTecla ().tecla_U && tipo == "DNA") {
               baseApertada = 'X';
               liberado = false;
            }
            if (baseAtual == baseApertada) {
               quantBases++;
               if (quantBases == tamanhoSeq - 1)
                  return 1;
               baseAtual = basesChar[quantBases + 1];
            }
            else if (baseApertada != 'x') 
               quantErro++;
            baseApertada = 'x';
         }
      }
      x -= velocidade;
      quantAndou += velocidade;
      return 0;
   }

   public void desenhar (Graphics graficos) {
      int i;
      inicio = (int) (quantAndou / tamLetra) ;
      if (completa) { // Se é um sequencia já completa.
         for (i = inicio; i < tamanhoSeq && (x + i*tamLetra) < 1280; i++) {
            switch (basesChar[i]) {
               case 'A':
                  graficos.drawImage (Elemento.A, (int) x + i*tamLetra, (int) y, null); 
                  break;
               case 'C':
                  graficos.drawImage (Elemento.C, (int) x + i*tamLetra, (int) y, null); 
                  break;
               case 'G':
                  graficos.drawImage (Elemento.G, (int) x + i*tamLetra, (int) y, null); 
                  break;
               case 'T':
                  graficos.drawImage (Elemento.T, (int) x + i*tamLetra, (int) y, null); 
                  break;
               case 'U':
                  graficos.drawImage (Elemento.U, (int) x + i*tamLetra, (int) y, null); 
                  break;
            }
         }
         if (i < 1280) {
            if (tipo == "DNA")
               graficos.drawImage (Elemento.vazioG, (int) x + i*tamLetra + 7, 612, null); 
            else 
               graficos.drawImage (Elemento.vazioG, (int) x + i*tamLetra + 7, 526, null); 
         }
      }
      else { // Caso não seja completa, depende da entrada do jogador.
         for (i = inicio; i < tamanhoSeq && (x + i*tamLetra) < 1280; i++) {
            if (quantBases >= i) {
               graficos.drawImage (Elemento.ponte, (int) x + i*tamLetra + 22, (int) y + 55, null); 
               if (tipo == "DNA")
                  graficos.drawImage (Elemento.dnaP, (int) x + i*tamLetra, (int) y - 25, null); 
               else 
                  graficos.drawImage (Elemento.rnaP, (int) x + i*tamLetra, (int) y - 25, null); 
               switch (basesChar[i]) {
                  case 'A':
                     if (tipo == "DNA")
                        graficos.drawImage (Elemento.T, (int) x + i*tamLetra, (int) y, null); 
                     else
                        graficos.drawImage (Elemento.U, (int) x + i*tamLetra, (int) y, null); 
                     break;
                  case 'C':
                     graficos.drawImage (Elemento.G, (int) x + i*tamLetra, (int) y, null); 
                     break;
                  case 'G':
                     graficos.drawImage (Elemento.C, (int) x + i*tamLetra, (int) y, null); 
                     break;
                  case 'T':
                     graficos.drawImage (Elemento.A, (int) x + i*tamLetra, (int) y, null); 
                     break;
               }
            }
         }
      }
      desenhaErro (graficos);
   }

   /* Pega cadeia complementar. */
   public static String getTemplateStrand (String codingStrand) {
      char dnaSeq[] = codingStrand.toCharArray ();
      for (int i = 0; i < codingStrand.length (); i++) {
         switch (dnaSeq[i]) {
            case 'A':
               dnaSeq[i] = 'T';
               break;
            case 'T':
               dnaSeq[i] = 'A';
               break;
            case 'G':
               dnaSeq[i] = 'C';
               break;
            case 'C':
               dnaSeq[i] = 'G';
               break;
         }
      }
      return new String (dnaSeq);
   }

   public static String getRNA (String codingStrand) {
      char seq[] = codingStrand.toCharArray ();
      for (int i = 0; i < codingStrand.length (); i++)
         if (seq[i] == 'T')
            seq[i] = 'U';
      return new String (seq);      
   }

   private void desenhaErro (Graphics graficos) {
      for (int i = 0; i < quantErro; i++) 
         graficos.drawImage (Elemento.erro, 788 + i*64, 179, null); 
   }

   public void setVelocidade (float velocidade) {
      this.velocidade = velocidade;
   }

   public float getVelocidade () {
      return velocidade;
   }

   public String getSeq () {
      return dnaSeq;
   }

   public int getX () {
      return (int) x;
   }

   public int getInicio () {
      return inicio;
   }
}
