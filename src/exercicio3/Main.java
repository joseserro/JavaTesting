package exercicio3;

public class Main {
    public static void main(String[] args) {
        //Mesa mesa = new Mesa(10);
        MesaBlockingQueue mesa = new MesaBlockingQueue(10);

        Cozinheiro c1 = new Cozinheiro("Cozinheiro 1", mesa);
        Cozinheiro c2 = new Cozinheiro("Cozinheiro 2",mesa);
        Cozinheiro c3 = new Cozinheiro("Cozinheiro 3",mesa);

        Glutao g1 = new Glutao("Glutão 1", mesa);
        Glutao g2 = new Glutao("Glutão 2",mesa);
        Glutao g3 = new Glutao("Glutão 3",mesa);

        c1.start();
        c2.start();
        c3.start();

        g1.start();
        g2.start();
        g3.start();
    }

}
