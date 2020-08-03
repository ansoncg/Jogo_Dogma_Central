package codigo.entidades;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import codigo.graficos.Elemento;
import codigo.jogo.Jogo;

public class Aminoacidos extends Entidade {

   private Jogo jogo;
   private Sequencia rnaMensageiro;
   private Map<String,String> tabelaCodons = new HashMap<String,String>();  // Tabela de conversão codon-aminoacido.
   private int xAtual, yAtual, pontoX, pontoY, seqTam, codonAtual = 5, erros = 0, tamAmino = 165, centraliza = 43;
   private String amino;
   private char[] basesChar;

   public Aminoacidos (Jogo jogo, Sequencia rnaMensageiro, float x, float y) {
      super (x, y);
      this.jogo = jogo;
      this.rnaMensageiro = rnaMensageiro;
      criarTabela (tabelaCodons);
      basesChar = rnaMensageiro.getSeq ().toCharArray ();
      seqTam = rnaMensageiro.getSeq ().length () / 3;
   }

   public int atualizar () {
      if (rnaMensageiro.getInicio () / 3 >= codonAtual + 1 || erros >= 3)
         return -1;
      xAtual = (int) jogo.getGerenciadorMouse ().getMouseX ();
      yAtual = (int) jogo.getGerenciadorMouse ().getMouseY ();
      amino = qualAmino ();
      if (jogo.getGerenciadorMouse ().estaApertado () && amino != "Nada") {
         if (amino == pegaAminoAtual (basesChar, codonAtual)) {
            codonAtual++;
            if (seqTam == codonAtual)
               return 1;
         }
         else
            erros++;
         jogo.getGerenciadorMouse ().negaClick ();
      }
      return 0;
   }
   
   public void desenhar (Graphics graficos) {
      graficos.drawImage (Elemento.ponto, pontoX, pontoY, null);
      graficos.drawImage (Elemento.codons, (int) x, (int) y, null);
      desenhaAmino (graficos); 
   }
   
   /* Desenha cadeia de aminoacidos. */
   private void desenhaAmino (Graphics graficos) {
      int i;
      String amn;
      for (i = rnaMensageiro.getInicio () / 3; i < codonAtual; i++) {
         amn = pegaAminoAtual (basesChar, i);
         if (i != 0 && !amn.equals ("Fim"))
            graficos.drawImage (Elemento.liga, rnaMensageiro.getX () + i*tamAmino + centraliza - tamAmino + 78, 653, null);
         switch (amn) {
            case "Val":
               graficos.drawImage (Elemento.Val, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Met":
               graficos.drawImage (Elemento.Met, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Ala":
               graficos.drawImage (Elemento.Ala, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Asp":
               graficos.drawImage (Elemento.Asp, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Glu":
               graficos.drawImage (Elemento.Glu, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Gly":
               graficos.drawImage (Elemento.Gly, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Phe":
               graficos.drawImage (Elemento.Phe, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Leu":
               graficos.drawImage (Elemento.Leu, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Ser":
               graficos.drawImage (Elemento.Ser, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Tyr":
               graficos.drawImage (Elemento.Tyr, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Cys":
               graficos.drawImage (Elemento.Cys, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Trp":
               graficos.drawImage (Elemento.Trp, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Pro":
               graficos.drawImage (Elemento.Pro, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "His":
               graficos.drawImage (Elemento.His, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Gln":
               graficos.drawImage (Elemento.Gln, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Arg":
               graficos.drawImage (Elemento.Arg, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Ile":
               graficos.drawImage (Elemento.Ile, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Thr":
               graficos.drawImage (Elemento.Thr, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Asn":
               graficos.drawImage (Elemento.Asn, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Lys":
               graficos.drawImage (Elemento.Lys, rnaMensageiro.getX () + i*tamAmino + centraliza, 620, null);
               break;
            case "Fim":
               graficos.drawImage (Elemento.Fim, rnaMensageiro.getX () + i*tamAmino + 7, 620, null);
               break;
         }
      }
      if (i != seqTam)
         graficos.drawImage (Elemento.indicador, rnaMensageiro.getX () + i*tamAmino + centraliza - 40, 605, null);
      else
         graficos.drawImage (Elemento.indicador, rnaMensageiro.getX () + (i - 1)*tamAmino + centraliza - 40, 605, null);
      desenhaErro (graficos);
   }

   /* Pega um codon e devolve o aminoacido correspondente. */
   private String pegaAminoAtual (char[] bases, int codonAtual) {
      String codon = "";
      codonAtual *= 3;
      codon += bases[codonAtual++];
      codon += bases[codonAtual++];
      codon += bases[codonAtual];
      return tabelaCodons.get (codon);
   }

   /* Procurando em qual aminoacido o mouse está dentro da tabela. */
   private String qualAmino () {
      if (dentroArea (0, 500, 0, 500)) { // Está dentro da figura.
         if (dentroArea (250, 500, 0, 250)) { // Está no primeiro quadrante.
            if (dentroArea (250, 300, 0, 60)) {
               pontoX = (int) x + 270;
               pontoY = (int) y - 20;
               return "Phe";
            }
            else if (dentroArea (300, 345, 12, 75)) {
               pontoX = (int) x + 325;
               pontoY = (int) y - 10;
               return "Leu";
            }
            else if (dentroArea (345, 410, 50, 105)) {
               pontoX = (int) x + 390;
               pontoY = (int) y + 30;
               return "Ser";
            }
            else if (dentroArea (405, 445, 100, 145)) {
               pontoX = (int) x + 440;
               pontoY = (int) y + 80;
               return "Tyr";
            }
            else if (dentroArea (425, 470, 140, 170)) {
               pontoX = (int) x + 465;
               pontoY = (int) y + 125;
               return "Fim";
            }
            else if (dentroArea (435, 485, 175, 205)) {
               pontoX = (int) x + 480;
               pontoY = (int) y + 160;
               return "Cys";
            }
            else if (dentroArea (442, 490, 211, 230)) {
               pontoX = (int) x + 485;
               pontoY = (int) y + 197;
               return "Fim";
            }
            else if (dentroArea (445, 500, 230, 250)) {
               pontoX = (int) x + 490;
               pontoY = (int) y + 225;
               return "Trp";
            }
         }
         else if (dentroArea (0, 250, 0, 250)) { // Está no segundo quadrante.
            if (dentroArea (0, 70, 160, 250)) {
               pontoX = (int) x - 20;
               pontoY = (int) y + 195;
               return "Val";
            }
            else if (dentroArea (40, 93, 100, 170)) {
               pontoX = (int) x + 10;
               pontoY = (int) y + 105;
               return "Ala";
            }
            else if (dentroArea (85, 127, 70, 110)) {
               pontoX = (int) x + 60;
               pontoY = (int) y + 40;
               return "Asp";
            }
            else if (dentroArea (115, 165, 38, 80)) {
               pontoX = (int) x + 105;
               pontoY = (int) y + 15;
               return "Glu";
            }
            else if (dentroArea (167, 250, 0, 80)) {
               pontoX = (int) x + 175;
               pontoY = (int) y - 15;
               return "Gly";
            }
         }
         else if (dentroArea (0, 250, 250, 500)) { // Está no terceiro quadrante.
            if (dentroArea (0, 60, 250, 285)) {
               pontoX = (int) x - 20;
               pontoY = (int) y + 260;
               return "Arg";
            }
            else if (dentroArea (10, 67, 290, 330)) {
               pontoX = (int) x - 10;
               pontoY = (int) y + 305;
               return "Ser";
            }
            else if (dentroArea (24, 60, 335, 370) || dentroArea (50, 83, 325, 355)) {
               pontoX = (int) x + 5;
               pontoY = (int) y + 345;
               return "Lys";
            }
            else if (dentroArea (45, 98, 358, 405)) {
               pontoX = (int) x + 30;
               pontoY = (int) y + 390;
               return "Asn";
            }
            else if (dentroArea (83, 153, 395, 455)) {
               pontoX = (int) x + 80;
               pontoY = (int) y + 445;
               return "Thr";
            }
            else if (dentroArea (147, 183, 430, 480)) {
               pontoX = (int) x + 135;
               pontoY = (int) y + 475;
               return "Met";
            }
            else if (dentroArea (187, 250, 434, 500)) {
               pontoX = (int) x + 185;
               pontoY = (int) y + 490;
               return "Ile";
            }
         }
         else { // Está no quarto quadrante.
            if (dentroArea (250, 343, 441, 500)) {
               pontoX = (int) x + 295;
               pontoY = (int) y + 485;
               return "Arg";
            }
            else if (dentroArea (337, 384, 420, 466)) {
               pontoX = (int) x + 365;
               pontoY = (int) y + 460;
               return "Gln";
            }
            else if (dentroArea (373, 415, 390, 438)) {
               pontoX = (int) x + 405;
               pontoY = (int) y + 425;
               return "His";
            }
            else if (dentroArea (402, 461, 327, 408)) {
               pontoX = (int) x + 450;
               pontoY = (int) y + 375;
               return "Pro";
            }
            else if (dentroArea (430, 500, 250, 334)) {
               pontoX = (int) x + 485;
               pontoY = (int) y + 295;
               return "Leu";
            }

         }
      }
      pontoX = (int) x + 250;
      pontoY = (int) y + 250;
      return "Nada";
   }

   private void desenhaErro (Graphics graficos) {
      for (int i = 0; i < erros; i++) 
         graficos.drawImage (Elemento.erro, 211 + i*64, 208, null); 
   }

   /* Determina se coordenadas estão dentro de uma area. */
   private boolean dentroArea (int xIni, int xFim, int yIni, int yFim) {
      if (xAtual > (int) x + xIni && xAtual < (int) x + xFim && yAtual > (int) y + yIni && yAtual < (int) y + yFim)
         return true;
      else
         return false;
   }
   
   /* Tabela de conversão de cada codon para um aminoacido. */
   private void criarTabela (Map<String,String> tabelaCodons) {
      tabelaCodons.put ("UUU", "Phe");
      tabelaCodons.put ("UUC", "Phe");
      tabelaCodons.put ("UUA", "Leu");
      tabelaCodons.put ("UUG", "Leu");
      tabelaCodons.put ("UCU", "Ser");
      tabelaCodons.put ("UCC", "Ser");
      tabelaCodons.put ("UCA", "Ser");
      tabelaCodons.put ("UCG", "Ser");
      tabelaCodons.put ("UAU", "Tyr");
      tabelaCodons.put ("UAC", "Tyr");
      tabelaCodons.put ("UAA", "Fim");
      tabelaCodons.put ("UAG", "Fim");
      tabelaCodons.put ("UGU", "Cys");
      tabelaCodons.put ("UGC", "Cys");
      tabelaCodons.put ("UGA", "Fim");
      tabelaCodons.put ("UGG", "Trp");
      tabelaCodons.put ("CUU", "Leu");
      tabelaCodons.put ("CUC", "Leu");
      tabelaCodons.put ("CUA", "Leu");
      tabelaCodons.put ("CUG", "Leu");
      tabelaCodons.put ("CCU", "Pro");
      tabelaCodons.put ("CCC", "Pro");
      tabelaCodons.put ("CCA", "Pro");
      tabelaCodons.put ("CCG", "Pro");
      tabelaCodons.put ("CAU", "His");
      tabelaCodons.put ("CAC", "His");
      tabelaCodons.put ("CAA", "Gln");
      tabelaCodons.put ("CAG", "Gln");
      tabelaCodons.put ("CGU", "Arg");
      tabelaCodons.put ("CGC", "Arg");
      tabelaCodons.put ("CGA", "Arg");
      tabelaCodons.put ("CGG", "Arg");
      tabelaCodons.put ("AUU", "Ile");
      tabelaCodons.put ("AUC", "Ile");
      tabelaCodons.put ("AUA", "Ile");
      tabelaCodons.put ("AUG", "Met");
      tabelaCodons.put ("ACU", "Thr");
      tabelaCodons.put ("ACC", "Thr");
      tabelaCodons.put ("ACA", "Thr");
      tabelaCodons.put ("ACG", "Thr");
      tabelaCodons.put ("AAU", "Asn");
      tabelaCodons.put ("AAC", "Asn");
      tabelaCodons.put ("AAA", "Lys");
      tabelaCodons.put ("AAG", "Lys");
      tabelaCodons.put ("AGU", "Ser");
      tabelaCodons.put ("AGC", "Ser");
      tabelaCodons.put ("AGA", "Arg");
      tabelaCodons.put ("AGG", "Arg");
      tabelaCodons.put ("GUU", "Val");
      tabelaCodons.put ("GUC", "Val");
      tabelaCodons.put ("GUA", "Val");
      tabelaCodons.put ("GUG", "Val");
      tabelaCodons.put ("GCU", "Ala");
      tabelaCodons.put ("GCC", "Ala");
      tabelaCodons.put ("GCA", "Ala");
      tabelaCodons.put ("GCG", "Ala");
      tabelaCodons.put ("GAU", "Asp");
      tabelaCodons.put ("GAC", "Asp");
      tabelaCodons.put ("GAA", "Glu");
      tabelaCodons.put ("GAG", "Glu");
      tabelaCodons.put ("GGU", "Gly");
      tabelaCodons.put ("GGC", "Gly");
      tabelaCodons.put ("GGA", "Gly");
      tabelaCodons.put ("GGG", "Gly");
   }
}
