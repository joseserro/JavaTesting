package apoio.moleculas;

import java.util.concurrent.CyclicBarrier;

public class ProdutorHidrogenio extends Thread {

    Molecula molecula;

    public ProdutorHidrogenio(Molecula molecula) {
        this.molecula = molecula;
    }

    @Override
    public void run() {

    }
}
