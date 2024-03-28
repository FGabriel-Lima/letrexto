import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Letrexto {
  private String palavra;

  public Letrexto(){
    this.palavra = gerarPalavra();
  }

  public void exibirMenu(){
    System.out.println("=====================");
    System.out.println("BEM-VINDO AO LETREXTO");
    System.out.println("=====================");
    System.out.println();
    System.out.println("Instruções:");
    System.out.println("- O computador escolherá uma palavra aleatória de cinco letras.");
    System.out.println("- Você tem seis tentativas para advinhar a palavra.");
    System.out.println("- Após cada palpite, você receberá um feedback sobre o seu palpite:");
    System.out.println("\t - Um '+' indica uma letra correta na posição correta");
    System.out.println("\t - Um '-' indica uma letra correta na posição errada");
    System.out.println("\t - Um '*' indica uma letra incorreta");
    System.out.println();
    System.out.println("Vamos começar!");
  }

  public String receberPalpite(String pala){
    String ret = "";
    for(int i = 0; i < palavra.length(); i++){
      ret += procuraLetra(pala.charAt(i), i);
    }
    return ret;
  }

  public String procuraLetra(char letra, int index){
    if(letra == palavra.charAt(index)){
      return "+";
    }
    for(int i = 0; i < palavra.length(); i++){
      if(letra == palavra.charAt(i)){
        return "-";
      }
    }
    return "*";
  }

  public String getPalavra(){
    return palavra;
  }

  private String gerarPalavra(){
    LocalTime horaAtual = LocalTime.now();

    List<String> palavras = lerPalavrasDoArquivo("src/br-utf8.txt");

    Random random = new Random(horaAtual.toSecondOfDay());

    String palavraAleatoria = palavras.get(random.nextInt(palavras.size()));

    return palavraAleatoria;
  }

  public boolean palavraValida(String palavra){
    List<String> palavras = lerPalavrasDoArquivo("src/br-utf8.txt");
    for(String p : palavras){
      if(p.equals(palavra)){
        return true;
      }
    }
    return false;
  }

  private static List<String> lerPalavrasDoArquivo(String caminhoDoArquivo){
    List<String> palavras = new ArrayList<>();
    BufferedReader leitor = null;

    try{
      leitor = new BufferedReader(new FileReader(caminhoDoArquivo));
      String linha;

      while((linha = leitor.readLine()) != null){
        palavras.add(linha);
      }
    }catch(IOException e){
      e.printStackTrace();
    }finally{
      if(leitor != null){
        try{
          leitor.close();
        }catch(IOException e){
          e.printStackTrace();
        }
      }
    }
    return palavras;
  }
}
