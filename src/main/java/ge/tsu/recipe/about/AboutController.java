package ge.tsu.recipe.about;

import ge.tsu.recipe.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);

    private final CategoryService categoryService;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public AboutController(CategoryService categoryService) {
        this.categoryService = categoryService;
        logger.info("AboutController initialized with CategoryService");
    }

    @GetMapping("/about")
    public String about(Model model) {
        logger.debug("About page requested");

        try {
            logger.debug("Loading categories from CategoryService");
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("appVersion", appVersion);

            logger.info("About page data loaded successfully - app version: {}", appVersion);
            return "about";

        } catch (Exception e) {
            logger.error("Error occurred while loading about page data: {}", e.getMessage(), e);
            throw e;
        }
    }
}