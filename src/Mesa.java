import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class Javali {

    private static int LATEST_ID = 0;
    private Cozinheiro cozinheiro;
    private int id;

    public Javali(Cozinheiro cozinheiro){
        id = getAndIncrementLastestId();
        this.cozinheiro = cozinheiro;
    }

    public int getID() {
        return id;
    }

    public synchronized static int getAndIncrementLastestId(){
        return LATEST_ID++;
    }

}


public class Mesa {

    private List<Javali> javalis = new ArrayList<Javali>();
    private int maxSize;

    public Mesa(int maxSize){
        this.maxSize = maxSize;
    }

    public synchronized void colocaJavali(Javali j) throws InterruptedException {
        while(javalis.size()>=maxSize){
            wait();
        }
        javalis.add(j);
        notifyAll();
    }

    public synchronized Javali retiraJavali() throws InterruptedException {
        while(javalis.size()==0){
            wait();
        }
        Javali j = javalis.remove(0);
        notifyAll();
        return j;
    }

    public static void main(String[] args) {
        Mesa m = new Mesa(10);
        //MesaBlockingQueue m = new MesaBlockingQueue(10);

        Cozinheiro c1 = new Cozinheiro("Cozinheiro 1", m);
        Cozinheiro c2 = new Cozinheiro("Cozinheiro 1", m);
        Cozinheiro c3 = new Cozinheiro("Cozinheiro 1", m);

        Glutao g1 = new Glutao("Glutao 1", m);
        Glutao g2 = new Glutao("Glutao 1", m);
        Glutao g3 = new Glutao("Glutao 1", m);

        c1.start();
        c2.start();
        c3.start();

        g1.start();
        g2.start();
        g3.start();
    }

}
/*
public class MesaBlockingQueue extends Mesa {
    private BlockingQueue<Javali> javalis;
    public MesaBlockingQueue(int maxSize){
        super(maxSize);
        javalis = new ArrayBlockingQueue<Javali>(maxSize);
    }
    public synchronized void colocaJavali(Javali j) throws InterruptedException {
        javalis.put(j);
    }
    public synchronized Javali retiraJavali() throws InterruptedException {
        return javalis.take();
    }
}
*/

class Cozinheiro extends Thread{

    private String nome;
    private Mesa mesa;

    public Cozinheiro(String nome, Mesa mesa){
        this.nome = nome;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(1000); //cozinha
                Javali j = new Javali(this);
                System.out.println(nome + " está a cozinhar o Javali " + j.getID());
                mesa.colocaJavali(j);
            } catch (InterruptedException e) {}
        }
    }
}

class Glutao extends Thread{

    private String nome;
    private Mesa mesa;

    public Glutao(String nome, Mesa mesa) {
        this.nome = nome;
        this.mesa = mesa;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        while(true){
            try {
                Javali j = mesa.retiraJavali();
                sleep(1000); //comer
                System.out.println(nome + " está a comer o Javali " + j.getID());
            } catch (InterruptedException e) {}
        }
    }

}

class Semaphore {

    int count;

    public synchronized void semWait(){
        boolean interrupted = false;
        while (count == 0) {
            try { wait(); }
            catch (InterruptedException ie) {interrupted = true;}
        }
        count --;
        if (interrupted) Thread.currentThread().interrupt();

    }

    public synchronized void semPost(){
        count ++;
        notifyAll();
    }

}

class CyclicBarrier {
    private int numberWaiters;
    private int currentWaiters = 0;
    private int passedWaiters = 0;

    public synchronized void barrierWait() throws InterruptedException {
        currentWaiters ++;
        while (currentWaiters < numberWaiters) {
            wait();
        }
        if(passedWaiters == 0)
            notifyAll();
        passedWaiters ++;
        if(passedWaiters == numberWaiters){
            passedWaiters = 0;
            currentWaiters = 0;
        }
    }
}
