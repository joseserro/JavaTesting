package apoio.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Teste {
    public static void main(String[] args) throws InterruptedException {
        RunnableTeste um = new RunnableTeste(1);
        RunnableTeste dois = new RunnableTeste(2);
        RunnableTeste tres = new RunnableTeste(3);
        RunnableTeste quatro = new RunnableTeste(4);

        /*
        um.run();
        dois.run();
        tres.run();
        quatro.run();
        */

        /*
        new Thread(um).start();
        new Thread(dois).start();
        new Thread(tres).start();
        new Thread(quatro).start();
        */

        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(um);
        pool.submit(dois);
        pool.submit(tres);
        pool.submit(quatro);

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

    }
}

class RunnableTeste implements Runnable {

    int id;
    public RunnableTeste(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            long tempo = (long) (1000 + Math.random() * 4000);
            System.out.println("Runnable " + id + " vai dormir " + tempo);
            Thread.sleep(tempo);
            System.out.println("Runnable " + id + " terminou.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
