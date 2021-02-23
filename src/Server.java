import java.util.Random;
import java.util.concurrent.Semaphore;

public class Server {
}

class Sala {
    private Semaphore semaphore;

    public Sala(){
        semaphore = new Semaphore(2);
    }

    public void entrar() throws InterruptedException {
        semaphore.acquire();
    }

    public void sair(){
        semaphore.release();
    }
}

class Funcionario extends Thread {

    private String nome;
    private Sala sala;

    public Funcionario(String nome, Sala sala){
        this.nome = nome;
        this.sala = sala;
    }

    public static void main(String[] args) {
        Sala sala = new Sala();

        Funcionario f1 = new Funcionario("Funcionario 1", sala);
        Funcionario f2 = new Funcionario("Funcionario 2", sala);
        Funcionario f3 = new Funcionario("Funcionario 3", sala);

        f1.start();
        f2.start();
        f3.start();
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println(nome + " aguarda entrada na sala...");
                sala.entrar();
                System.out.println(nome + " entrou na sala e começou a trabalhar...");
                Random r = new Random();
                int time = r.nextInt(5000)+8000;
                sleep(time);
                System.out.println(nome + " terminou o trabalho e irá agora sair da sala...");
                sala.sair();
            } catch (InterruptedException e) {}
        }
    }

}