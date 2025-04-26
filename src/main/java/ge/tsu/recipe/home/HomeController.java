package ge.tsu.recipe.home;

import ge.tsu.recipe.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "home";
    }
}