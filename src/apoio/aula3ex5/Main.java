package apoio.aula3ex5;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true);

        Funcionario um = new Funcionario(1, semaphore);
        Funcionario dois = new Funcionario(2, semaphore);
        Funcionario tres = new Funcionario(3, semaphore);

        um.start();
        dois.start();
        tres.start();
    }
}
