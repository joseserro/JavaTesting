package vertel.MacacoInfinito;

import java.util.ArrayList;

import java.util.List;

public class Shakespeare extends Thread{
    private List<String> book;
    private List<String> frasesAnalisar= new ArrayList<>(3);
    private MesaShakespeare mesa;
    private List<Macaco> macacos;


    public Shakespeare(MesaShakespeare  mesa, List<Macaco> macacos){
        book= new ArrayList<>();
        this.mesa=mesa;
        this.macacos=macacos;
    }


    public void addToBook(String frase){
        book.add(frase);
    }




    public void printBook(){
        System.out.println("Livro de Shakespeare!");
        for(String frase: book){
            System.out.println(frase);
        }
    }





    public void analyseSentences() {
        for(String sentence:frasesAnalisar){
            if(sentence!=null && sentence.contains("o romeu")){
                addToBook(sentence);//adicionar ao livro do shakespeare
                System.out.println("Foi adicionada uma frase ao livro");
            }
        }
    }


    @Override
    public void run() {
        while(book.size()<100){//enquanto nao tem 100 frases
            frasesAnalisar= mesa.takeFrases();
            analyseSentences();
            frasesAnalisar.clear();
        }
        for(Macaco macaco:macacos){
            macaco.interrupt();
        }
        try {
            join();
        } catch (InterruptedException e) {
            System.out.println("--> Shakespeare interrompido enquanto esperava que os macacos acabassem!");
        }
        printBook();
    }



    public static void main(String[] args) {
        MesaShakespeare mesa= new MesaShakespeare();
        List<Macaco> macacos= new ArrayList<>();
        for(int i=1; i<11; i++){
            Macaco macaco=new Macaco(i, mesa);
            macacos.add(macaco);
            macaco.start();
        }
        Shakespeare shakespere= new Shakespeare(mesa, macacos);
        shakespere.start();


    }
}
