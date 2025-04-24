package ge.tsu.recipe.home;

import ge.tsu.recipe.category.CategoryService;
import ge.tsu.recipe.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Pageable pageable, Model model) {
        // Override sorting logic!
        Sort sort = Sort.by(Sort.Order.by("createTime").with(Sort.Direction.DESC));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        model.addAttribute("recipes", recipeService.getAllRecipes(pageable));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("totalPages", recipeService.totalNumberOfRecipes() / pageable.getPageSize());
        return "index";
    }
}