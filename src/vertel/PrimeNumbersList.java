package vertel;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class PrimeNumbersList {
    private List<Integer> primeNumbers;

    public PrimeNumbersList(){
        primeNumbers= new ArrayList<>();
    }

    public synchronized void analyseNumber(int i){
        if(i==1){
            return;
        }
        for(int j=2; j<i; j++){
            if(i%j==0){
                return;
            }
        }
        primeNumbers.add(i);
    }

    public void getPrimeNumbers() {
        Collections.sort(primeNumbers);
        for(Integer prime: primeNumbers){
            System.out.print(prime+" ");
        }
    }
}
