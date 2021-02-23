package vertel.MacacoInfinito;

import java.util.Random;

public class Macaco extends Thread{
    private MesaShakespeare mesa;
    private int id;
    private static final int STRINGLENGTH = 1000 ;


    public Macaco(int id, MesaShakespeare mesa){
        this.id=id;
        this.mesa= mesa;
    }


    private String generateString (){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for ( int i = 0 ; i< STRINGLENGTH ; i++){
            if ( r .nextInt( 5 ) == 0 ) {
                sb .append( ' ' );
            } else {
                sb .append( Character . toChars ( r .nextInt( 26 ) + 'a' ));
            }
        }
        return sb .toString();
    }


    @Override
    public void run() {
        try{
            while(true){
                mesa.addFrase(generateString());
                System.out.println("Macaco "+id+" adicionou uma frase à mesa");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
