package ge.tsu.recipe.recipe;

import ge.tsu.recipe.comment.CommentDTO;
import ge.tsu.recipe.image.ImageDTO;
import ge.tsu.recipe.recipe.Recipe;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class RecipeDTO {
    private Long id;
    private String title;
    private String author;
    private String ingredients;
    private String instructions;
    private Integer cookingTime;
    private Integer servingSize;
    private String difficulty;
    private String excerpt;
    private LocalDateTime createTime;
    private String prettyCreateTime;
    private String categoryName;
    private Long categoryId;
    private List<ImageDTO> images;
    private List<CommentDTO> comments;

    public static RecipeDTO fromRecipe(Recipe recipe) {
        var recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(recipe, recipeDTO);

        if (recipe.getCategory() != null) {
            recipeDTO.setCategoryName(recipe.getCategory().getName());
            recipeDTO.setCategoryId(recipe.getCategory().getId());
        }

        recipeDTO.setImages(recipe.getImages().stream().map(ImageDTO::fromImage).collect(Collectors.toList()));
        recipeDTO.setComments(recipe.getComments().stream().map(CommentDTO::fromComment).collect(Collectors.toList()));
        recipeDTO.setPrettyCreateTime(prettyFormat(recipe.getCreateTime()));
        return recipeDTO;
    }
}