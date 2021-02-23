public class Producer extends Thread {
    private NumberContainer numberContainer;
    private int id;
    public Producer(NumberContainer nc, int id) {
        numberContainer = nc;
        this.id = id;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            numberContainer.put(i);
            System.out.println("Producer #" +
                    this.id + " put: " + i);
        }
    }
}
