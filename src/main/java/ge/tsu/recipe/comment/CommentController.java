package ge.tsu.recipe.comment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/recipe/{recipeId}/comment")
    public String addComment(@PathVariable Long recipeId,
                             @Valid @ModelAttribute("commentForm") CommentForm commentForm,
                             RedirectAttributes redirectAttributes) {
        try {
            commentService.addComment(recipeId, commentForm);
            redirectAttributes.addFlashAttribute("successMessage", "Comment added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add comment: " + e.getMessage());
        }
        return "redirect:/recipe/" + recipeId;
    }
}