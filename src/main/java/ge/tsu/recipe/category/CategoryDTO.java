package ge.tsu.recipe.category;

import org.springframework.beans.BeanUtils;

public class CategoryDTO {
    private Long id;

    public void setRecipeCount(int recipeCount) {
        this.recipeCount = recipeCount;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String description;
    private int recipeCount;


    public static CategoryDTO fromCategory(Category category) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(category, dto);

        if (category.getRecipes() != null) {
            dto.setRecipeCount(category.getRecipes().size());
        }

        return dto;
    }


}