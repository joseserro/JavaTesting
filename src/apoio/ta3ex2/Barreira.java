package apoio.ta3ex2;

class Barreira {
    private int numberWaiters;
    private int currentWaiters = 0;
    private int passedWaiters = 0;

    public Barreira(int numberWaiters) {
        this.numberWaiters = numberWaiters;
    }

    public synchronized void await() throws InterruptedException {
        currentWaiters ++;
        while (currentWaiters < numberWaiters) {
            wait();
        }
        if(passedWaiters == 0)
            notifyAll();
        passedWaiters ++;
        if(passedWaiters == numberWaiters){
            passedWaiters = 0;
            currentWaiters = 0;
        }
    }
}
