import pt.upskill.projeto2.financemanager.categories.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("categories")));
        List<Category> categoriesTest = new ArrayList<>();

        Category ca1 = new Category("HOME");
        ca1.addTag("PURCHASE");
        Category ca2 = new Category("CAR");
        ca2.addTag("GASOLINA");
        Category ca3 = new Category("EXTRA");

        categoriesTest.add(ca1);
        categoriesTest.add(ca2);
        categoriesTest.add(ca3);

        outputStream.writeObject(categoriesTest);

        //stack use
        Stack stack = new Stack();
        stack.push(0);
        int a = stack.peek();
        int b = stack.pop();

        //producer use
        Producer producer = new Producer(new NumberContainer(), 0);
        producer.run();
    }
}
