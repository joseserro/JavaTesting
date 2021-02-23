package testemodelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Primos {
    private List<Integer> numerosPrimos = new ArrayList<>();

    private List<Integer> getNumerosPrimosOrdenados() {
        Collections.sort(numerosPrimos);
        return numerosPrimos;
    }

    public synchronized void addPrimo(int primo) {
        numerosPrimos.add(primo);
    }

    private static final int VALOR_MAX = 101;

    public static void main(String[] args) throws InterruptedException {
        List<Integer> primeiroQuarto = new ArrayList<>();
        List<Integer> segundoQuarto = new ArrayList<>();
        List<Integer> terceiroQuarto = new ArrayList<>();
        List<Integer> quartoQuarto = new ArrayList<>();

        for(int i = 2; i < VALOR_MAX; i+=4) {
            primeiroQuarto.add(i);
            if(i + 1 <= VALOR_MAX)
                segundoQuarto.add(i+1);
            if(i + 2 <= VALOR_MAX)
                terceiroQuarto.add(i+2);
            if(i + 3 <= VALOR_MAX)
                quartoQuarto.add(i+3);
        }

        System.out.println(primeiroQuarto);
        System.out.println(segundoQuarto);
        System.out.println(terceiroQuarto);
        System.out.println(quartoQuarto);

        Primos primos = new Primos();

        ExecutorService pool = Executors.newFixedThreadPool(4);

        pool.submit(new CalculadoraPrimos(primeiroQuarto, primos));
        pool.submit(new CalculadoraPrimos(segundoQuarto, primos));
        pool.submit(new CalculadoraPrimos(terceiroQuarto, primos));
        pool.submit(new CalculadoraPrimos(quartoQuarto, primos));

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println(primos.getNumerosPrimosOrdenados());
    }
}

class CalculadoraPrimos implements Runnable {

    private List<Integer> calcular;
    private Primos primos;
    public CalculadoraPrimos(List<Integer> calcular, Primos primos) {
        this.calcular = calcular;
        this.primos = primos;
    }

    @Override
    public void run() {
        for(Integer num : calcular) {
            boolean isPrime = num != 1;
            for(int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                primos.addPrimo(num);
            }
        }
    }
}