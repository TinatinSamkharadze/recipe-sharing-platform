package ge.tsu.recipe.category;

import ge.tsu.recipe.recipe.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findByCategoryId(Long categoryId, Pageable pageable);
    long countByCategoryId(Long categoryId);
}