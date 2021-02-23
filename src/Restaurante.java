import static java.lang.Thread.sleep;

class Refeicao {

    private String nome;

    public Refeicao(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class Empregado extends Thread {

    private Restaurante r;
    private int id;

    public Empregado(Restaurante r, int id){
        this.r = r;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while(true){
                Refeicao refeicao = r.getRefeicao(id);
                System.out.println((System.currentTimeMillis() - r.inicio) + " Empregado #" + id +  ": Servido o prato: " + refeicao.getNome());
                sleep(6000);
            }
        } catch (InterruptedException e) {}
    }
}


class ChefeDeCozinha extends Thread {

    private Restaurante r;
    private int id;

    public ChefeDeCozinha(Restaurante r, int id){
        this.r = r;
        this.id = id;
    }

    @Override
    public void run() {
        for(int i = 0; i<10; i++){
            try {
                Refeicao refeicao = new Refeicao("Refeicao " + id + "_" + i);
                r.putRefeicao(refeicao);
                System.out.println((System.currentTimeMillis() - r.inicio) + " Chefe #" + id +  ": acabou de cozinhar: " + refeicao.getNome());
            } catch (InterruptedException e) {}
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}

public class Restaurante {

    private Refeicao refeicao;
    public long inicio;

    public Restaurante(long inicio){
        this.inicio = inicio;
    }

    public synchronized void putRefeicao(Refeicao r) throws InterruptedException {
        while(refeicao!=null){
            wait();

        }
        refeicao = r;
        notifyAll();
    }

    public synchronized Refeicao getRefeicao(int id_empregado) throws InterruptedException {
        while(refeicao==null){
            wait();
        }
        Refeicao toReturn = refeicao;
        refeicao = null;
        notifyAll();
        return toReturn;
    }

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante(System.currentTimeMillis());

        ChefeDeCozinha chefe1 = new ChefeDeCozinha(restaurante, 1);
        //ChefeDeCozinha chefe2 = new ChefeDeCozinha(restaurante, 2);

        chefe1.start();

        for(int i = 0; i < 20; i++) {
            Empregado emp1 = new Empregado(restaurante, i);
            emp1.start();
        }
        //Empregado emp2 = new Empregado(restaurante, 2);

        //Ladrao

    }

}
