package apoio.moleculas;

import java.util.concurrent.*;

public class ProdutorMolecula {
    int numeroOxigenio;
    int numeroHidrogenio;
    CyclicBarrier barreira = new CyclicBarrier(3);
    Semaphore semaphoreOxigenio = new Semaphore(1);
    Semaphore semaphoreHidrogenio = new Semaphore(2);

    public static int numeroMoleculas;

    public void addHidrogenio() throws InterruptedException {
        semaphoreHidrogenio.acquire();
        if(numeroHidrogenio < 2) {
            numeroHidrogenio++;
            barreira.await();
            numeroHidrogenio = 0;
        } else {

        }
    }

    public void addOxigenio() throws BrokenBarrierException, InterruptedException {
        if(numeroOxigenio < 1) {
            numeroOxigenio++;
            barreira.await();
            numeroMoleculas++;
        }
    }
}
