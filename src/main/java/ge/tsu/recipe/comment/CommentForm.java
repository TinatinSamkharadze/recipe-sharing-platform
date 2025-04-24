package ge.tsu.recipe.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentForm {
    @NotBlank(message = "Author must not be blank")
    @Size(max = 50, message = "Author name must not exceed {max} characters")
    private String author;

    @NotBlank(message = "Comment text must not be blank")
    @Size(max = 1000, message = "Comment must not exceed {max} characters")
    private String text;
}