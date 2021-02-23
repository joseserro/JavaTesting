package vertel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeNumber extends Thread {
    private PrimeNumbersList primeNumbersList;
    private int id;
    private int inicio;
    private int max;


    public PrimeNumber(int id,int inicio, int max, PrimeNumbersList primeNumbersList){
        this.id=id;
        this.primeNumbersList=primeNumbersList;
        this.max=max;
        this.inicio=inicio;
    }


    @Override
    public void run() {
        for(int i=inicio; i<max; i++){
            primeNumbersList.analyseNumber(i);
            //System.out.println("A thread "+id+" analisou o numero "+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool= Executors.newFixedThreadPool(4);
        PrimeNumbersList primeNumbersList= new PrimeNumbersList();
        int max=102;
        int first=1;
        int last=max/4;
        for(int i=1; i<5; i++){
            PrimeNumber thread= new PrimeNumber(i,first, last, primeNumbersList);
            threadPool.submit(thread);
            first=last+1;
            last+=max/4;
        }
        threadPool.shutdown();
        threadPool.awaitTermination(4, TimeUnit.SECONDS);
        primeNumbersList.getPrimeNumbers();

    }
}
