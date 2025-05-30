package ge.tsu.recipe.category;

import ge.tsu.recipe.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public String listAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }
    @GetMapping("/category/{id}")
    public String categoryPage(@PathVariable Long id, Pageable pageable, Model model) {
        Category category = categoryService.findById(id);

        Sort sort = Sort.by(Sort.Order.by("createTime").with(Sort.Direction.DESC));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        model.addAttribute("category", categoryService.getCategoryById(id));
        model.addAttribute("recipes", recipeService.getRecipesByCategory(id, pageable));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("totalPages", recipeService.totalNumberOfRecipesByCategory(id) / pageable.getPageSize());

        return "category";
    }
}