package ge.tsu.recipe.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findByCategoryId(Long categoryId, Pageable pageable);
    long countByCategoryId(Long categoryId);

    @Query("SELECT r FROM Recipe r WHERE " +
            "LOWER(r.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.author) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.instructions) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Recipe> searchRecipes(@Param("query") String query, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Recipe r WHERE " +
            "LOWER(r.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.author) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.instructions) LIKE LOWER(CONCAT('%', :query, '%'))")
    long countSearchResults(@Param("query") String query);
}