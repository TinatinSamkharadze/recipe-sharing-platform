package ge.tsu.recipe.about;

import ge.tsu.recipe.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    private final CategoryService categoryService;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public AboutController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("appVersion", appVersion);
        return "about";
    }
}