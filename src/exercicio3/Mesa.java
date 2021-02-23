package exercicio3;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    // Attributes
    private List<Javali> javalis = new ArrayList<Javali>();
    private int maxSize;

    // Constructor
    public Mesa(int maxSize) {
        this.maxSize = maxSize;
    }

    // Methods
    public synchronized void colocaJavali(Javali j) throws InterruptedException {
        while (javalis.size() >= maxSize) {
            wait();
        }
        javalis.add(j);
        notifyAll();
    }

    public synchronized Javali retiraJavali() throws InterruptedException {
        while (javalis.size() == 0) {
            wait();
        }
        Javali j = javalis.remove(0);
        notifyAll();
        return j;
    }
}