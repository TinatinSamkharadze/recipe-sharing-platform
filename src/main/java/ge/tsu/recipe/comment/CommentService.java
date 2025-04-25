package ge.tsu.recipe.comment;

import ge.tsu.recipe.recipe.Recipe;
import ge.tsu.recipe.recipe.RecipeService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
//@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeService recipeService;
    public CommentService(CommentRepository commentRepository, RecipeService recipeService) {
        this.commentRepository = commentRepository;
        this.recipeService = recipeService;
    }
    public List<CommentDTO> getCommentsByRecipeId(Long recipeId) {
        return commentRepository.findByRecipeIdOrderByCreateTimeDesc(recipeId)
                .stream()
                .map(CommentDTO::fromComment)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDTO addComment(Long recipeId, CommentForm commentForm) {
        Recipe recipe = recipeService.findById(recipeId);

        Comment comment = new Comment();
        comment.setAuthor(commentForm.getAuthor());
        comment.setText(commentForm.getText());
        comment.setRecipe(recipe);

        comment = commentRepository.save(comment);
        return CommentDTO.fromComment(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}