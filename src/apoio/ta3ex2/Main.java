package apoio.ta3ex2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barreira = new CyclicBarrier(4);
        ThreadTeste um = new ThreadTeste(barreira, 1);
        ThreadTeste dois = new ThreadTeste(barreira, 2);
        ThreadTeste tres = new ThreadTeste(barreira, 3);
        ThreadTeste quatro = new ThreadTeste(barreira, 4);

        um.start();
        dois.start();
        tres.start();
        quatro.start();
    }
}

class ThreadTeste extends Thread {
    int id;
    long time;
    CyclicBarrier barreira;
    public ThreadTeste(CyclicBarrier barreira, int id) {
        this.id = id;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        try {
            time = System.currentTimeMillis();
            long tempo = (long) (1000 + Math.random() * 4000);
            System.out.println("Thread " + id + " vai dormir " + tempo);
            sleep(tempo);
            barreira.await(10, TimeUnit.SECONDS);
            System.out.println("Thread " + id + " após await, saiu após " + (System.currentTimeMillis() - time));
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Thread " + id + " fez timeout.");
        }
    }
}
