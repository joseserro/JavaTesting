import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class TestThreads {
    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i<10000;i++) {
            int finalI = i;
            Runnable r = () -> {
                System.out.println("Sou um runnable dentro da thread (" + finalI + ")");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread t = new Thread(r);
            threads.add(t);
        }
        for(Thread t : threads) {
            t.start();
        }
        for(Thread t : threads) {
            t.join();
        }
        System.out.println("Tempo de execução: " + (System.currentTimeMillis() - time)/1000 + " segundos.");
    }
}
