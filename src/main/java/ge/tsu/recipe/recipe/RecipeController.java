package ge.tsu.recipe.recipe;

import ge.tsu.recipe.category.CategoryService;
import ge.tsu.recipe.comment.CommentForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;
    @Autowired
    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }


    @GetMapping("/recipe/{id}")
    public String recipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        model.addAttribute("commentForm", new CommentForm());
        return "recipe";
    }

    @GetMapping("/recipe/new")
    public String createNewRecipe(Model model) {
        if (!model.containsAttribute("recipeForm")) {
            model.addAttribute("recipeForm", new RecipeForm());
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("difficultyLevels", new String[]{"EASY", "MEDIUM", "HARD"});
        return "recipe_new";
    }

    @PostMapping("/recipe/new")
    public String createNewRecipe(@Valid @ModelAttribute("recipeForm") RecipeForm form,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeForm", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeForm", result);
            return "redirect:/recipe/new";
        }

        try {
            RecipeDTO recipe = recipeService.saveRecipe(form, form.getImages());
            String redirectUrl = UriComponentsBuilder.fromPath("/recipe/{id}")
                    .buildAndExpand(recipe.getId())
                    .toUriString();
            return String.format("redirect:%s", redirectUrl);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    String.format("An error occurred: %s", e.getMessage())
            );
        }
        return "redirect:/recipe/new";
    }
}