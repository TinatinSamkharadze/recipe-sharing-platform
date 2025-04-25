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

    public @NotBlank(message = "Author must not be blank") @Size(max = 50, message = "Author name must not exceed {max} characters") String getAuthor() {
        return author;
    }

    public @NotBlank(message = "Comment text must not be blank") @Size(max = 1000, message = "Comment must not exceed {max} characters") String getText() {
        return text;
    }

    public void setAuthor(@NotBlank(message = "Author must not be blank") @Size(max = 50, message = "Author name must not exceed {max} characters") String author) {
        this.author = author;
    }



    public void setText(@NotBlank(message = "Comment text must not be blank") @Size(max = 1000, message = "Comment must not exceed {max} characters") String text) {
        this.text = text;
    }
}