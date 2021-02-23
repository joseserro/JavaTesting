package vertel.MacacoInfinito;

import java.util.ArrayList;
import java.util.List;

public class MesaShakespeare {
    private List<String> frases= new ArrayList<>(3);

    public synchronized void addFrase(String string) throws InterruptedException {
        while (frases.size() == 3) {
            wait();
            //System.out.println("Mesa está cheia");
        }
        frases.add(string);
        assert frases.size() <= 3 : "Erro: Foram colocadas mais que 3 frases na mesa!";
        if(frases.size() == 3) {
            notifyAll();
        }
    }
    /*
    public synchronized void remFrase() throws InterruptedException {
        wait();
        for(String sentence:frases){
            frases.remove(sentence);
        }
        System.out.println("removed sentences from the table");
        notifyAll();
    }

     */

    public synchronized List<String> takeFrases(){
        while(frases.size() < 3) {
            try {
                wait();

            } catch (InterruptedException e) {}
        }

        List<String> frasesTaken = frases;

        assert frasesTaken.size() == 3 : "Erro: Está a ser devolvida uma lista de Strings com tamanho inferior a 3!";

        frases.clear();
        notifyAll();

        return frasesTaken;
    }


    public List<String> getFrases() {
        return frases;
    }
}
