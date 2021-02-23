import java.util.Random;

public class TestRunnable {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for(int i = 0; i<10;i++) {
            int finalI = i;
            Runnable r = () -> {
                System.out.println("Sou um runnable (" + finalI + ")");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            r.run();
        }
        System.out.println("Tempo de execução: " + (System.currentTimeMillis() - time)/1000 + " segundos.");
    }
}
