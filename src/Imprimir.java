class ImprimirNumero implements Runnable{
    private Imprimir p;
    public ImprimirNumero(Imprimir p){
        this.p=p;
    }
    public void run(){
        for(int i=0;i<26;i++){
            p.printNumber();
        }
    }
}

class ImprimirLetra implements Runnable{
    private Imprimir p;
    public ImprimirLetra(Imprimir p){
        this.p=p;
    }
    public void run(){
        for(int i=0;i<26;i++){
            p.printChar();
        }
    }
}

public class Imprimir {
    public static void main(String[] args) {
        Imprimir p = new Imprimir();
        Thread printnumber = new Thread(new ImprimirNumero(p), "print number");
        Thread printchar = new Thread(new ImprimirLetra(p), "print letter");
        printnumber.start();
        printchar.start();
    }

    private int i=1;
    private char j='A';
    public synchronized void printNumber(){
        System.out.print(String.valueOf(i)+" "+String.valueOf(i+1));
        i=i+2;
        notifyAll();
        try{
            wait();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public synchronized void printChar(){
        System.out.print(" " + j + " ");
        j++;
        notifyAll();
        try{
            if(j<='Z'){
                wait();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}