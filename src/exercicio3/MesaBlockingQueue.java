package exercicio3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MesaBlockingQueue extends Mesa {
    // Attributes
    private BlockingQueue<Javali> javalis;

    // Constructor
    public MesaBlockingQueue(int maxSize) {
        super(maxSize);
        this.javalis = new ArrayBlockingQueue<Javali>(maxSize);
    }

    // Methods
    @Override
    public void colocaJavali(Javali j) throws InterruptedException {
        javalis.put(j);
    }

    @Override
    public Javali retiraJavali() throws InterruptedException {
        return javalis.take();
    }
}
