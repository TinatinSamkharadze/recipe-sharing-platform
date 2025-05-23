package ge.tsu.recipe;

import ge.tsu.recipe.recipe.Recipe;
import ge.tsu.recipe.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SupplyDummyDataOnStartup implements CommandLineRunner {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void run(String... args) throws Exception {
        if (recipeRepository.count() == 0) {
            createRecipesForLocale(Locale.ENGLISH);
            createRecipesForLocale(new Locale("es"));
            createRecipesForLocale(new Locale("fr"));
        }
    }

    private void createRecipesForLocale(Locale locale) {
        String localeSuffix = locale.getLanguage().equals("en") ? "" : "_" + locale.getLanguage();

        Recipe r1 = new Recipe();
        r1.setTitle(getMessageForLocale("sample.recipe1.title", null, locale));
        r1.setDescription(getMessageForLocale("sample.recipe1.description", null, locale));
        r1.setIngredients("Spaghetti, ground beef, tomato sauce, onion, garlic, olive oil, herbs");
        r1.setInstructions("Cook spaghetti. Prepare the sauce by cooking beef with onion and garlic, add tomato sauce and herbs. Combine with pasta.");
        r1.setAuthor(getMessageForLocale("sample.recipe1.author", null, locale));
        r1.setCookingTime(30);
        r1.setServingSize(4);
        r1.setDifficulty("Medium");

        Recipe r2 = new Recipe();
        r2.setTitle(getMessageForLocale("sample.recipe2.title", null, locale));
        r2.setDescription(getMessageForLocale("sample.recipe2.description", null, locale));
        r2.setIngredients("Chicken, curry powder, coconut milk, onion, garlic, ginger, spices");
        r2.setInstructions("Sauté onion, garlic, and ginger. Add chicken and spices. Cook with coconut milk until tender.");
        r2.setAuthor(getMessageForLocale("sample.recipe2.author", null, locale));
        r2.setCookingTime(45);
        r2.setServingSize(4);
        r2.setDifficulty("Medium");

        Recipe r3 = new Recipe();
        r3.setTitle(getMessageForLocale("sample.recipe3.title", null, locale));
        r3.setDescription(getMessageForLocale("sample.recipe3.description", null, locale));
        r3.setIngredients("Flour, milk, eggs, baking powder, sugar, salt, butter");
        r3.setInstructions("Mix dry ingredients. Add wet ingredients. Cook on griddle until golden brown.");
        r3.setAuthor(getMessageForLocale("sample.recipe3.author", null, locale));
        r3.setCookingTime(20);
        r3.setServingSize(2);
        r3.setDifficulty("Easy");


        if (locale.getLanguage().equals("en")) {
            recipeRepository.save(r1);
            recipeRepository.save(r2);
            recipeRepository.save(r3);
        }
    }

    private String getMessageForLocale(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, key, locale);
    }
}