public class Useless {
    public boolean closed = true;
    public synchronized void u1() {
        try {
            while (closed)
                wait();
        } catch (InterruptedException i) {}
    }
    public synchronized void u2() {
        if (closed) {
            closed = false;
            notify();
        }
    }
}