package ge.tsu.recipe;

import ge.tsu.recipe.recipe.Recipe;
import ge.tsu.recipe.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SupplyDummyDataOnStartup implements CommandLineRunner {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (recipeRepository.count() == 0) {
            Recipe r1 = new Recipe();
            r1.setTitle("Spaghetti Bolognese");
            r1.setDescription("A delicious classic Italian pasta dish with meat sauce.");
            r1.setIngredients("Spaghetti, ground beef, tomato sauce, onion, garlic, olive oil, herbs");
            r1.setInstructions("Cook spaghetti. Prepare the sauce by cooking beef with onion and garlic, add tomato sauce and herbs. Combine with pasta.");
            r1.setAuthor("Chef Luigi");
            r1.setCookingTime(30);
            r1.setServingSize(4);
            r1.setDifficulty("Medium");

            Recipe r2 = new Recipe();
            r2.setTitle("Chicken Curry");
            r2.setDescription("A spicy and flavorful chicken curry made with aromatic spices.");
            r2.setIngredients("Chicken, curry powder, coconut milk, onion, garlic, ginger, spices");
            r2.setInstructions("Sauté onion, garlic, and ginger. Add chicken and spices. Cook with coconut milk until tender.");
            r2.setAuthor("Chef Priya");
            r2.setCookingTime(45);
            r2.setServingSize(4);
            r2.setDifficulty("Medium");

            Recipe r3 = new Recipe();
            r3.setTitle("Pancakes");
            r3.setDescription("Fluffy homemade pancakes perfect for breakfast.");
            r3.setIngredients("Flour, milk, eggs, baking powder, sugar, salt, butter");
            r3.setInstructions("Mix dry ingredients. Add wet ingredients. Cook on griddle until golden brown.");
            r3.setAuthor("Chef Emily");
            r3.setCookingTime(20);
            r3.setServingSize(2);
            r3.setDifficulty("Easy");

            recipeRepository.save(r1);
            recipeRepository.save(r2);
            recipeRepository.save(r3);
        }
    }
}