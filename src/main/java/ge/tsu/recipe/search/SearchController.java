package ge.tsu.recipe.search;

import ge.tsu.recipe.category.CategoryService;
import ge.tsu.recipe.recipe.RecipeDTO;
import ge.tsu.recipe.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class SearchController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    @Autowired
    public SearchController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Pageable pageable, Model model) {
        List<RecipeDTO> searchResults = recipeService.searchRecipes(query, pageable);

        model.addAttribute("query", query);
        model.addAttribute("recipes", searchResults);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("totalPages", recipeService.countSearchResults(query) / pageable.getPageSize());

        return "search";
    }
}