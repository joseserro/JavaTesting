public class SleepMessages extends Thread {
    private Useless u;
    private long startTime;
    public SleepMessages(String name, Useless u, long startTime) {
        setName(name);
        this.startTime = startTime;
        this.u = u;
    }
    public void run() {
        String importantInfo[] = { "Red", "Green", "Blue" };
        for (int i = 0; i < importantInfo.length; i++) {
            u.u1();
            try {
                sleep(4000);
                System.out.println(getName() + "-" + importantInfo[i] + "-"
                        + (System.currentTimeMillis() - startTime) / 1000 + "s");
            } catch (InterruptedException e) {
                u.u2();
            }
        }
    }
    public static void main(String args[]) throws InterruptedException {
        long sTime = System.currentTimeMillis();
        Useless u = new Useless();
        Thread t1 = new SleepMessages("t1",u, sTime);
        Thread t2 = new SleepMessages("t2",u, sTime);
        System.out.println("Here they go!...");
        t1.start();
        t2.start();
        sleep(2000);
        t2.interrupt();
        sleep(4000);
        t1.interrupt();
        sleep(1000);
        u.u2();
        t1.interrupt();
        sleep(1000);
        t2.interrupt();
        t1.join();
        t2.join();
        System.out.println("Main - Main done - "
                + (System.currentTimeMillis() - sTime) / 1000 + "s");
    }
}