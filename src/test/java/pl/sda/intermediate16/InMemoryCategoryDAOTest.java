package pl.sda.intermediate16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate16.categories.Category;
import pl.sda.intermediate16.categories.InMemoryCategoryDAO;

import java.util.List;

class InMemoryCategoryDAOTest {

    @Test
    void shouldPopulateCategoriesListProperly() {
        InMemoryCategoryDAO inMemoryCategoryDAO = new InMemoryCategoryDAO();
        List<Category> categoryList = inMemoryCategoryDAO.getCategoryList();
        Assertions.assertEquals(4, pickParentId(categoryList));
    }

    private Integer pickParentId(List<Category> categoryList) {
        return categoryList.stream()
                .filter(c -> c.getId().equals(5))
                .findFirst()
                .map(e -> e.getParentId())//uruchomi się tylko kiedy findFirst coś znajdzie
                .orElse(null); // uruchomi się tylko wtedy kiedy nic nie zostanie znalezione
    }
}