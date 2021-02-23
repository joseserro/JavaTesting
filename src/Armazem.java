import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Armazem {
    private LinkedList<Encomenda> encomendas;
    private Carrinha carrinhaAtual;
    public Armazem(LinkedList<Encomenda> encomendas) {
        this.encomendas = encomendas;
    }

    public synchronized void setCarrinhaAtual(Carrinha carrinha) {
        while(carrinhaAtual != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        this.carrinhaAtual = carrinha;
    }

    public synchronized void libertarArmazem() {
        this.carrinhaAtual = null;
        notifyAll();
    }

    public Encomenda retrieveEncomenda() {
        return encomendas.removeFirst();
    }

    public static void main(String[] args) {
        LinkedList<Encomenda> encomendas = new LinkedList<>();
        for(int i = 0; i < 50; i++) {
            encomendas.add(new Encomenda(i));
        }
        Armazem armazem = new Armazem(encomendas);

        for(int i = 0; i < 10; i++) {
            new Carrinha(i, armazem).start();
        }
    }
}

class Carrinha extends Thread {
    private Armazem armazem;
    private int id;
    private List<Encomenda> encomendas = new ArrayList<>();
    public Carrinha(int id, Armazem armazem) {
        this.id = id;
        this.armazem = armazem;
    }

    @Override
    public void run() {
        armazem.setCarrinhaAtual(this); //fica aqui à espera
        try {
            while(this.encomendas.size() < 5) {
                Encomenda encomenda = armazem.retrieveEncomenda();
                this.encomendas.add(encomenda);
                System.out.println("Carrinha " + id + ": Retirada encomenda " + encomenda.getId());
                sleep(100);
            }
        } catch (InterruptedException e) {}
        armazem.libertarArmazem();
    }
}

class Encomenda {
    private int id;
    public Encomenda(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}