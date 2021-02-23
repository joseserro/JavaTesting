package unused.ex2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ex2 extends Thread {

    private  CyclicBarrier barrier;
    private  String nome;
    private int count;

    public Ex2(CyclicBarrier barrier, String nome){
        this.barrier=barrier;
        this.nome=nome;
        this.count=0;
    }

    public void run() {
        try {
            while(count<5){
                int duration=new Random().nextInt(5) + 1;

                count = duration + count;
                if(count>5){
                    System.out.println(Thread.currentThread().getName() + " está no run");
                    int n = 5 + duration - count;
                    System.out.println(n+ "s de Tempo de sleep " + Thread.currentThread().getName());
                    Thread.sleep(n*1000);
                    System.out.println("Para "+Thread.currentThread().getName() + " estão "+ barrier.getNumberWaiting() + " é o número de threads na barreira");
                }else{
                    System.out.println(Thread.currentThread().getName() + " está no run");
                    System.out.println(duration + "s de Tempo de sleep" + Thread.currentThread().getName());
                    Thread.sleep(duration*1000);
                    System.out.println("Para "+Thread.currentThread().getName() + " estão "+ barrier.getNumberWaiting() + " é o número de threads na barreira");
                }
            }
            System.out.println(Thread.currentThread().getName() + " is calling await()");
            barrier.await();
            System.out.println(" Todas as threads já estão na barreira");

        } catch (InterruptedException | BrokenBarrierException  e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(5);
        Thread first = new Ex2( barrier, "PARTY-1");
        Thread second = new Ex2( barrier, "PARTY-2");
        Thread third = new Ex2( barrier, "PARTY-3");
        Thread fourth = new Ex2( barrier, "PARTY-4");
        Thread five = new Ex2( barrier, "PARTY-5");

        first.start();
        second.start();
        third.start();
        fourth.start();
        five.start();
        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}





