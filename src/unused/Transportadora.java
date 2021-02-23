package unused;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Transportadora {

    List<Encomenda> encomendasEmEspera = new ArrayList<Encomenda>();
    CyclicBarrier barrier;

    public Transportadora(){
        barrier = new CyclicBarrier(3);
    }

    public void envia(Encomenda e) throws BrokenBarrierException, InterruptedException {
        barrier.await();
        //se a barreira libertou foi porque 5 threads fizeram o await
        System.out.println("A encomenda " + e.getID() + " foi enviada.");
    }

    public static void main(String[] args) {
        Transportadora t = new Transportadora();

        Cliente c1 = new Cliente("unused.Cliente 1", t);
        Cliente c2 = new Cliente("unused.Cliente 2", t);
        Cliente c3 = new Cliente("unused.Cliente 3", t);

        c1.start();
        c2.start();
        c3.start();

    }

}

class Encomenda {

    private static int LATEST_ID = 0;
    private int id;

    public Encomenda(){
        id = getAndIncrementLastestId();
    }

    public int getID() {
        return id;
    }

    public synchronized static int getAndIncrementLastestId(){
        return LATEST_ID++;
    }

}

class Cliente extends Thread {

    private String nome;
    private Transportadora transportadora;

    public Cliente (String nome, Transportadora transportadora){
        this.nome = nome;
        this.transportadora = transportadora;
    }

    @Override
    public void run() {
        while(true){
            Random random = new Random();
            int time = random.nextInt(3000) + 3000;
            try {
                sleep(time);
            } catch (InterruptedException e) {}
            Encomenda e = new Encomenda();
            try {
                System.out.println(nome + " envia encomenda " + e.getID());
                transportadora.envia(e);
            } catch (BrokenBarrierException brokenBarrierException) {
                brokenBarrierException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}