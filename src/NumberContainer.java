public class NumberContainer {
    private int contents;
    private boolean available = false;
    public synchronized void put(int value) {
        while(available) {
            try {
              wait();
            } catch (InterruptedException ignored) {}
        }
        contents = value;
        available = true;
        notifyAll();
    }
    public synchronized int get() {
        while(!available) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        available = false;
        notifyAll();
        return contents;
    }

    boolean condition;
    public synchronized boolean get(int timeout) {
        long startTime = System.currentTimeMillis();
        try {
            while (!condition) {
                long timeLeft = timeout - (System.currentTimeMillis() - startTime);
                wait(timeLeft);
                if(System.currentTimeMillis() - startTime > timeout){
                    return false;
                }
            }
            condition = false;
        } catch (InterruptedException ignored) {}
        return true;
    }
}
