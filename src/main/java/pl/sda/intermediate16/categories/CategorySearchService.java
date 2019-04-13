package pl.sda.intermediate16.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchService {

    public List<CategoryDTO> filterCategories(String categoryName) {
        List<Category> categoryList = InMemoryCategoryDAO.getInstance().getCategoryList();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDTOList.add(buildCategoryDTO(category)); //przepisujemy category na categoryDto
        }

        for (CategoryDTO categoryDTO : categoryDTOList) {
            if (categoryDTO.getParentId() == null) {
                continue;
            }
            //fixme mapa zamiast streama
            categoryDTO.setParentCat(
                    categoryDTOList.stream()
                            .filter(c -> c.getId().equals(categoryDTO.getParentId()))
                            .findFirst()
                            .orElse(null)
            );
        }


        for (CategoryDTO categoryDTO : categoryDTOList) {
            if (categoryName == null) {
                return categoryDTOList;
            } else {
                if (categoryName.trim().equals(categoryDTO.getCategoryName())) { //dodane 'categoryName!=null'
                    categoryDTO.getCategoryState().setOpen(true);
                    categoryDTO.getCategoryState().setSelected(true);
                    categoryDTO.getCategoryState().setVisible(true);
                    openParent(categoryDTO.getParentCat());
                }
            }


        }

        return categoryDTOList.stream()
                .filter(e -> e.getCategoryState().isVisible())
                .collect(Collectors.toList());
    }

    private void openParent(CategoryDTO categoryDTO) { //otwieramy wszystkie nadrzędne kategorie
        categoryDTO.getCategoryState().setOpen(true);
        categoryDTO.getCategoryState().setVisible(true);
        if (categoryDTO.getParentId() == null) {
            return;
        }
        openParent(categoryDTO.getParentCat());
    }

    private CategoryDTO buildCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName().trim());
        categoryDTO.setId(category.getId());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setCategoryState(new CategoryState());
        return categoryDTO;
    }
}
