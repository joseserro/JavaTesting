import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lotaria {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        List<Bola> bolas = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Bola bola = new Bola(i);
            bolas.add(bola);
            pool.submit(bola);
        }

        pool.shutdown();
        pool.awaitTermination(4, TimeUnit.SECONDS);

        Collections.sort(bolas);

        for(Bola bola : bolas) {
            System.out.println(bola);
        }
    }
}

class Bola implements Runnable, Comparable<Bola> {
    private int valor = 0;
    private int id;

    public Bola(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 35; i++) {
                valor += Math.random() * 100;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return "Bola " + id + ": " + valor;
    }

    @Override
    public int compareTo(Bola o) {
        return o.valor - this.valor;
    }
}