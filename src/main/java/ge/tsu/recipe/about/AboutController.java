package ge.tsu.recipe.about;

import ge.tsu.recipe.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    private final CategoryService categoryService;

    public AboutController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "about";
    }
}