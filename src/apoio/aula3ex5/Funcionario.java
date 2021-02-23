package apoio.aula3ex5;

import java.util.concurrent.Semaphore;

public class Funcionario extends Thread {
    int id;
    Semaphore semaphore;
    public Funcionario(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            for(;;) {
                System.out.println("Funcionário " + id + " tentou adquirir o semáforo.");
                semaphore.acquire();

                long tempo = (long) (5000 + (Math.random() * 3000));
                System.out.println("Funcionário " + id + " adquiriu o semáforo. Vai dormir durante " + tempo);
                sleep(tempo);
                System.out.println("Funcionário " + id + " acabou a execução do trabalho.");
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
