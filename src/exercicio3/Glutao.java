package exercicio3;

public class Glutao extends Thread {
    // Attributes
    private String nome;
    private Mesa mesa;

    // Constructor
    public Glutao(String nome, Mesa mesa) {
        this.nome = nome;
        this.mesa = mesa;
    }

    // Methods
    @Override
    public void run() {
        while (true) {
            try {
                Javali j = mesa.retiraJavali();
                sleep(1000);
                System.out.println(nome + " comeu o javali(" + j.getId() + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
