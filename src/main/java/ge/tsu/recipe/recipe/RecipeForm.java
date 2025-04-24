package ge.tsu.recipe.recipe;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class RecipeForm {
    @NotBlank(message = "Author must not be blank")
    @Size(max = 50, message = "Author size must not exceed {max}")
    private String author;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title size must not exceed {max}")
    private String title;

    @NotBlank(message = "Ingredients must not be blank")
    private String ingredients;

    @NotBlank(message = "Instructions must not be blank")
    private String instructions;

    @NotNull(message = "Cooking time must be specified")
    @Min(value = 1, message = "Cooking time must be at least 1 minute")
    private Integer cookingTime;

    @NotNull(message = "Serving size must be specified")
    @Min(value = 1, message = "Serving size must be at least 1")
    private Integer servingSize;

    @NotBlank(message = "Difficulty must be specified")
    private String difficulty;

    private Long categoryId;

    private List<MultipartFile> images;
}