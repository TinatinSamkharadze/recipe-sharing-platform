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

    @Query(value = "SELECT * FROM RECIPES r WHERE " +
            "LOWER(r.TITLE) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.AUTHOR) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.INGREDIENTS) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.INSTRUCTIONS) LIKE LOWER(CONCAT('%', :query, '%'))",
            nativeQuery = true)
    Page<Recipe> searchRecipes(@Param("query") String query, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM RECIPES r WHERE " +
            "LOWER(r.TITLE) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.AUTHOR) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.INGREDIENTS) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(r.INSTRUCTIONS) LIKE LOWER(CONCAT('%', :query, '%'))",
            nativeQuery = true)
    long countSearchResults(@Param("query") String query);
}