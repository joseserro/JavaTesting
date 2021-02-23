public class Philosopher extends Thread {
    int id, times_eat=0;
    Fork leftFork, rightFork;
    public Philosopher(int id,Fork leftFork,Fork rightFork){
        this.id=id;
        this.leftFork=leftFork;
        this.rightFork=rightFork;
    }

    private void eating() throws InterruptedException {
        System.out.println("Philosopher("+id+") eating.");
        sleep((long)(100 + Math.random() * 500));
        times_eat++;
    }
    private void thinking() throws InterruptedException {
        sleep((long)(100 + Math.random() * 500));
    }

    public void run(){
        try {
            while (times_eat != 5) {
                thinking();
                leftFork.up(id);
                rightFork.up(id);
                eating();
                leftFork.down(id);
                rightFork.down(id);
            }
        } catch (InterruptedException e){}
        System.out.println("Philosopher("+id+") leaves the room");
    }

    public static void main(String[] args){
        Fork f1=new Fork(1);
        Fork f2=new Fork(2);
        Fork f3=new Fork(3);
        Fork f4=new Fork(4);
        Fork f5=new Fork(5);

        new Philosopher(1,f1,f2).start();
        new Philosopher(2,f2,f3).start();
        new Philosopher(3,f3,f4).start();
        new Philosopher(4,f4,f5).start();
        new Philosopher(5,f5,f1).start();

        System.out.println("All Philosophers started");


    }

}

class Fork {
    private int id;
    private boolean inUse;
    public Fork(int id){
        this.id=id;
        this.inUse=false;
    }
    public synchronized void up(int philosopher_id) {
        while (inUse) {
            System.out.println("Philosopher ("+philosopher_id+") Going to sleep waiting for fork (" + id + ")");
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Philosopher ("+philosopher_id+") picked Fork(" + id + ") up");
        inUse = true;
    }
    public synchronized void down(int philosopher_id){
        System.out.println("Philosopher ("+philosopher_id+") put Fork("+id+") down");
        inUse=false;
        notifyAll();
    }
}