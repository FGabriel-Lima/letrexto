import java.util.Scanner;

public class Runner {
  public static void main(String[] args) {
    Letrexto let = new Letrexto();
    let.exibirMenu();

    Scanner in = new Scanner(System.in);

    String palavra;
    String resul = "";
    int numTentativas = 1;
    while(numTentativas <= 6){
      System.out.println();
      System.out.print("Digite o palpite #" + numTentativas + " ");
      palavra = in.nextLine();
      if(palavra.length() == 5){
        if(let.palavraValida(palavra)){
          resul = let.receberPalpite(palavra);
          numTentativas++;
          System.out.println();
          System.out.println("Palpite: " + palavra);
          System.out.println("Resultado: " + resul);
          if(resul.equals("+++++")){
            System.out.println();
            System.out.println("Parabéns! Você advinhou a palavra '" + palavra + "' corretamente");
            System.out.println();
            System.out.println("Obrigado por jogar Letrexto!");
            break;
          }
        }
      }
    }
    System.out.println("A palavra era: " + let.getPalavra());

    in.close();
  }
}
