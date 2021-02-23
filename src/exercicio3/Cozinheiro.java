package exercicio3;

public class Cozinheiro extends Thread {
    // Attributes
    private String nome;
    private Mesa mesa;

    // Constructor
    public Cozinheiro(String nome, Mesa mesa) {
        this.nome = nome;
        this.mesa = mesa;
    }

    // Methods
    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                Javali j = new Javali(this);
                System.out.println(nome + " preparou o javali(" + j.getId() + ")");
                mesa.colocaJavali(j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
