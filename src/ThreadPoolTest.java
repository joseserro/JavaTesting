import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static final int NUM_PALAVRAS_POR_THREAD = 2000;

    public static void main(String[] args) throws Exception {

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        Scanner scanner = new Scanner(new File("lowercase_books.txt"));

        //string builder para ir buscar todas as linhas
        StringBuilder todasPalavrasBuilder = new StringBuilder();
        while(scanner.hasNextLine()) {
            todasPalavrasBuilder.append(scanner.nextLine());
        }

        //todasPalavras
        String todasPalavras = todasPalavrasBuilder.toString();
        String[] palavras = todasPalavras.split(" ");
        Resultado resultado = new Resultado(palavras.length);

        long time = System.currentTimeMillis();

        int numTotalTarefas = (palavras.length / NUM_PALAVRAS_POR_THREAD) + 1;
        int palavraAtual = 0;
        for(int i = 0; i < numTotalTarefas; i++) {
            int num = NUM_PALAVRAS_POR_THREAD;
            if(palavras.length < (palavraAtual + NUM_PALAVRAS_POR_THREAD)) {
                num = palavras.length - palavraAtual;
            }
            String[] palavrasSeparadas = new String[num];
            for(int j = palavraAtual; j < palavraAtual + num; j++) {
                palavrasSeparadas[j - palavraAtual] = palavras[j];
            }

            threadPool.submit(new Capitalizador(i, palavrasSeparadas, resultado));
        }

        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);

        if(!threadPool.isTerminated()) {
            System.out.println("Não terminou tudo!");
        }


        System.out.println("Processadas " + resultado.getNum() +
                " palavras (" + resultado.getResult().length() + " bytes) em " + (System.currentTimeMillis() - time) + "ms");

        FileWriter myWriter = new FileWriter("output.txt");
        myWriter.write(resultado.getResult());
        myWriter.close();

    }
}

class Resultado {
    private String[] resultado;
    public Resultado(int num) {
        resultado = new String[num];
    }

    public synchronized void setWord(int indice, String palavra) {
        resultado[indice] = palavra;
    }

    public String getResult() {
        return String.join(" ", resultado);
    }

    public int getNum() {
        return resultado.length;
    }
}

class Capitalizador implements Runnable {
    private int indice;
    private String[] palavrasSeparadas;
    private Resultado resultado;

    public Capitalizador(int indice, String[] palavrasSeparadas, Resultado resultado) {
        this.indice = indice;
        this.palavrasSeparadas = palavrasSeparadas;
        this.resultado = resultado;
    }

    @Override
    public void run() {
        int indiceAtual = this.indice * ThreadPoolTest.NUM_PALAVRAS_POR_THREAD;
        for(String palavra : palavrasSeparadas) {
            String palavraCapitalizada = palavra.substring(0,1).toUpperCase() + palavra.substring(1);
            resultado.setWord(indiceAtual, palavraCapitalizada);
            indiceAtual++;
        }
        //System.out.println("Processadas palavras de " + this.indice * ThreadPoolTest.NUM_PALAVRAS_POR_THREAD + " a " + indiceAtual);
    }

}