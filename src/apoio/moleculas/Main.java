package apoio.moleculas;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {

        for(int i = 0; i < 20; i++) {
            Molecula molecula = new Molecula();
        }
        for(int i = 0; i < 10; i++) {
            new ProdutorOxigenio(barreira).start();
        }


    }
}
