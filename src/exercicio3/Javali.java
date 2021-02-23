package exercicio3;

public class Javali {
    private static int idCount = 1;

    // Attributes
    private int id;
    private Cozinheiro cozinheiro;

    // Constructor
    public Javali(Cozinheiro cozinheiro) {
        this.id = giveID();
        this.cozinheiro = cozinheiro;
    }

    // Methods
    private synchronized int giveID() {
        return idCount++;
    }

    public int getId() {
        return id;
    }
}
