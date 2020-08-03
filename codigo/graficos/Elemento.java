package codigo.graficos;

import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Elemento {

   public static BufferedImage hor, vert, horV, A, G, C, T, U, codons, ponto, fundo, ponte, liga, indicador, menu , 
   restoRepl, _3linha, _5linha, erro, dnaG, dnaP, perdeu, restoTrans, rnaP, rnaG, restoTrad, continuar, vazioG, escolha,
   explicaRepl, explicaTransc, explicaTrad, slideReplThis, slideRplJogar, slideReplEnzima, slideReplDNA, slideTradCod,
   slideTradJogar, slideTradProt, slideTradRib, slideTrcJogar, slideTrcRNA, slideTrcThis, referencia,
   Val, Ala, Asp, Glu, Gly, Phe, Leu, Ser, Tyr, Cys, Trp, Pro, His, Gln, Arg, Ile, Met, Thr, Asn, Lys, Fim;
   public static String dnaSeq, dnaSeqDif;

   public static void iniciarElementos () {

      // Desenhos
      codons = CarregadorImagens.carregaImagem ("../../recursos/imagens/codons.png"); 
      ponto = CarregadorImagens.carregaImagem ("../../recursos/imagens/dot.png"); 
      fundo = CarregadorImagens.carregaImagem ("../../recursos/imagens/fundo.jpg"); 
      ponte = CarregadorImagens.carregaImagem ("../../recursos/imagens/ponte.png"); 
      liga = CarregadorImagens.carregaImagem ("../../recursos/imagens/Liga2.png"); 
      indicador = CarregadorImagens.carregaImagem ("../../recursos/imagens/indicador.jpg"); 
      menu = CarregadorImagens.carregaImagem ("../../recursos/imagens/menu.png"); 
      restoRepl = CarregadorImagens.carregaImagem ("../../recursos/imagens/restoRepl.png"); 
      _3linha = CarregadorImagens.carregaImagem ("../../recursos/imagens/3'.png"); 
      _5linha = CarregadorImagens.carregaImagem ("../../recursos/imagens/5'.png"); 
      erro = CarregadorImagens.carregaImagem ("../../recursos/imagens/erro.png"); 
      dnaP = CarregadorImagens.carregaImagem ("../../recursos/imagens/dnaP.jpg"); 
      dnaG = CarregadorImagens.carregaImagem ("../../recursos/imagens/dnaG.jpg"); 
      perdeu = CarregadorImagens.carregaImagem ("../../recursos/imagens/perdeu.png"); 
      rnaP = CarregadorImagens.carregaImagem ("../../recursos/imagens/rnaP.jpg"); 
      rnaG = CarregadorImagens.carregaImagem ("../../recursos/imagens/rnaG.jpg"); 
      restoTrans = CarregadorImagens.carregaImagem ("../../recursos/imagens/restoTrans.png"); 
      continuar = CarregadorImagens.carregaImagem ("../../recursos/imagens/continuar.png"); 
      restoTrad = CarregadorImagens.carregaImagem ("../../recursos/imagens/restoTrad.png"); 
      vazioG = CarregadorImagens.carregaImagem ("../../recursos/imagens/vazioG.jpg"); 
      escolha = CarregadorImagens.carregaImagem ("../../recursos/imagens/escolha.png"); 
      explicaRepl = CarregadorImagens.carregaImagem ("../../recursos/imagens/explicaRepl.png"); 
      explicaTransc = CarregadorImagens.carregaImagem ("../../recursos/imagens/explicaTransc.png"); 
      explicaTrad = CarregadorImagens.carregaImagem ("../../recursos/imagens/explicaTrad.png"); 
      slideReplDNA = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideReplDNA.png"); 
      slideReplEnzima = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideReplEnzima.png"); 
      slideReplThis = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideReplThis.png"); 
      slideRplJogar = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideRplJogar.png"); 
      slideTrcRNA = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTrcRNA.png"); 
      slideTrcThis = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTrcThis.png"); 
      slideTrcJogar = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTrcJogar.png"); 
      slideTradRib = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTradRib.png"); 
      slideTradCod = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTradCod.png"); 
      slideTradJogar = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTradJogar.png"); 
      slideTradProt = CarregadorImagens.carregaImagem ("../../recursos/imagens/slideTradProt.png"); 
      referencia = CarregadorImagens.carregaImagem ("../../recursos/imagens/referencia.png"); 

      // Bases
      A = CarregadorImagens.carregaImagem ("../../recursos/imagens/A.png"); 
      G = CarregadorImagens.carregaImagem ("../../recursos/imagens/G.png"); 
      T = CarregadorImagens.carregaImagem ("../../recursos/imagens/T.png"); 
      C = CarregadorImagens.carregaImagem ("../../recursos/imagens/C.png"); 
      U = CarregadorImagens.carregaImagem ("../../recursos/imagens/U.png"); 

      // Aminoacidos
      Val = CarregadorImagens.carregaImagem ("../../recursos/imagens/Val.png"); 
      Ala = CarregadorImagens.carregaImagem ("../../recursos/imagens/Ala.png"); 
      Asp = CarregadorImagens.carregaImagem ("../../recursos/imagens/Asp.png"); 
      Glu = CarregadorImagens.carregaImagem ("../../recursos/imagens/Glu.png"); 
      Gly = CarregadorImagens.carregaImagem ("../../recursos/imagens/Gly.png"); 
      Phe = CarregadorImagens.carregaImagem ("../../recursos/imagens/Phe.png"); 
      Leu = CarregadorImagens.carregaImagem ("../../recursos/imagens/Leu.png"); 
      Ser = CarregadorImagens.carregaImagem ("../../recursos/imagens/Ser.png"); 
      Tyr = CarregadorImagens.carregaImagem ("../../recursos/imagens/Tyr.png"); 
      Cys = CarregadorImagens.carregaImagem ("../../recursos/imagens/Cys.png"); 
      Trp = CarregadorImagens.carregaImagem ("../../recursos/imagens/Trp.png"); 
      Pro = CarregadorImagens.carregaImagem ("../../recursos/imagens/Pro.png"); 
      His = CarregadorImagens.carregaImagem ("../../recursos/imagens/His.png"); 
      Gln = CarregadorImagens.carregaImagem ("../../recursos/imagens/Gln.png"); 
      Arg = CarregadorImagens.carregaImagem ("../../recursos/imagens/Arg.png"); 
      Ile = CarregadorImagens.carregaImagem ("../../recursos/imagens/Ile.png"); 
      Met = CarregadorImagens.carregaImagem ("../../recursos/imagens/Met.png"); 
      Thr = CarregadorImagens.carregaImagem ("../../recursos/imagens/Thr.png"); 
      Asn = CarregadorImagens.carregaImagem ("../../recursos/imagens/Asn.png"); 
      Lys = CarregadorImagens.carregaImagem ("../../recursos/imagens/Lys.png"); 
      Fim = CarregadorImagens.carregaImagem ("../../recursos/imagens/Fim.png"); 


      // Sequencias
      dnaSeq = leSequencia ("recursos/sequencias/seq.txt");
      dnaSeqDif = leSequencia ("recursos/sequencias/seqDificil.txt");
   } 

   public static String getSeq (String dificuldade) {
      switch (dificuldade) {
         case "normal":
            return dnaSeq;
         case "dificil":
            return dnaSeqDif;
      }
      return "ATGATG";
   }

   private static String leSequencia (String caminho) {
      String seq = "";
      try {
         File dnaSeqFile = new File (caminho);
         Scanner leitor = new Scanner (dnaSeqFile);
         seq = leitor.nextLine ();
         leitor.close ();
      }
      catch (FileNotFoundException e) {
         System.out.println ("Arquivo não encontrado");
      }
      return seq;
   }

}
