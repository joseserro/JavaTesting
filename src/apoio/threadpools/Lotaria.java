package apoio.threadpools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lotaria {
    public static void main(String[] args) throws InterruptedException {

        long time = System.currentTimeMillis();

        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Num Threads: " + numThreads);
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);

        List<Bola> listaBolas = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            Bola bola = new Bola(i);
            listaBolas.add(bola);
            pool.submit(bola);
        }

        pool.shutdown();
        pool.awaitTermination(20, TimeUnit.SECONDS);

        listaBolas.sort((b1, b2) -> b2.valor - b1.valor);

        System.out.println(listaBolas);

        System.out.println(System.currentTimeMillis() - time);
    }
}

class Bola implements Runnable {
    int valor = 0;
    int id;
    public Bola(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 35; i++) {
                valor += (Math.random() * 100);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Bola " + id + " - valor: " + valor;
    }
}
