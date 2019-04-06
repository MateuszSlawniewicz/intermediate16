package pl.sda.intermediate16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate16.categories.CategoryDTO;
import pl.sda.intermediate16.categories.CategorySearchService;

import java.util.List;

class CategorySearchServiceTest {
    @Test
    void shouldFilterCategories() {
        CategorySearchService categorySearchService = new CategorySearchService();
        List<CategoryDTO> categoryDTOList = categorySearchService.filterCategories("Lektury");
        CategoryDTO c4 = categoryDTOList.stream()
                .filter(c -> c.getId().equals(4))
                .findFirst()
                .orElse(null);
        Assertions.assertTrue(c4.getCategoryState().isOpen());

    }
}