import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Stack {
    private int pos = 0;
    private int stack[]= new int[100];

    public synchronized void push(int i) {
        pos++; //Operação não-atómica (3 passos)
        stack[pos]=i;
    }

    public int pop() {
        if (pos>0){
            return(stack[pos--]);
        } else
            return 0;
    }

    public int peek() {
        return stack[pos];
    }

    void use(){
        addName("");
        addEmployee("");

        Lock l = new Lock() {
            @Override
            public void lock() {

            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {

            }

            @Override
            public Condition newCondition() {
                return null;
            }
        };
        l.lock();
        try {
            //
        } finally {
            l.unlock();
        }
    }

    String lastName;
    int nameCount;
    List<String> nameList = new ArrayList<>();
    String company;
    int employeeCount;

    public void addName(String name) {
        synchronized (this){
            lastName = name;
            nameCount++;
        }
        nameList.add(name);
    }
    public void addEmployee(String name){
        synchronized(company) {
            employeeCount++;
        }
        nameList.add(name);
    }


}