package ge.tsu.recipe.category;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private int recipeCount;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static CategoryDTO fromCategory(Category category) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(category, dto);

        if (category.getRecipes() != null) {
            dto.setRecipeCount(category.getRecipes().size());
        }

        return dto;
    }
}