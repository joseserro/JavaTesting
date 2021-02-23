package pt.upskill.projeto2.financemanager.categories;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author upSkill 2020
 * <p>
 * ...
 */

public class Category implements Serializable {

    private String name;
    private List<String> tags = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    /**
     * Função que lê o ficheiro categories e gera uma lista de {@link Category} (método fábrica)
     * @param file - Ficheiro onde estão apontadas as categorias possíveis iniciais (por defeito: /account_info/categories)
     * @return uma lista de categorias, geradas ao ler o ficheiro
     */
    public static List<Category> readCategories(File file) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean hasTag(String tag) {
        // TODO Auto-generated method stub
        return tags.contains(tag);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public String getName() {
        return name;
    }


}
