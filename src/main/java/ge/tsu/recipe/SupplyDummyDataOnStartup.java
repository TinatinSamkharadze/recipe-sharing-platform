package ge.tsu.recipe;

import ge.tsu.recipe.category.Category;
import ge.tsu.recipe.category.CategoryRepository;
import ge.tsu.recipe.recipe.Recipe;
import ge.tsu.recipe.recipe.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
//@RequiredArgsConstructor
public class SupplyDummyDataOnStartup implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public SupplyDummyDataOnStartup(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Only add data if the database is empty
        if (categoryRepository.count() > 0) {
            return;
        }

        // Create categories
        List<Category> categories = createCategories();

        // Create recipes
        createRecipes(categories);
    }

    private List<Category> createCategories() {
        Category breakfast = new Category();
        breakfast.setName("Breakfast");
        breakfast.setDescription("Morning meals to start your day right");

        Category lunch = new Category();
        lunch.setName("Lunch");
        lunch.setDescription("Midday meals that are filling and nutritious");

        Category dinner = new Category();
        dinner.setName("Dinner");
        dinner.setDescription("Evening meals to enjoy with family and friends");

        Category dessert = new Category();
        dessert.setName("Dessert");
        dessert.setDescription("Sweet treats to end your meal");

        return categoryRepository.saveAll(Arrays.asList(breakfast, lunch, dinner, dessert));
    }

    private void createRecipes(List<Category> categories) {
        // Breakfast recipe
        Recipe pancakes = new Recipe();
        pancakes.setTitle("Fluffy Pancakes");
        pancakes.setAuthor("Chef John");
        pancakes.setIngredients("2 cups all-purpose flour\n1 tablespoon baking powder\n2 tablespoons sugar\n1/2 teaspoon salt\n2 eggs\n1 1/2 cups milk\n2 tablespoons melted butter\n1 teaspoon vanilla extract");
        pancakes.setInstructions("1. In a large bowl, sift together flour, baking powder, sugar, and salt.\n2. In another bowl, beat the eggs and then add milk, melted butter, and vanilla.\n3. Pour the wet ingredients into the dry ingredients and mix until just combined.\n4. Heat a griddle or frying pan over medium heat. Pour 1/4 cup batter for each pancake.\n5. Cook until bubbles form on the surface, then flip and cook until golden brown.\n6. Serve with maple syrup and fresh berries.");
        pancakes.setCookingTime(20);
        pancakes.setServingSize(4);
        pancakes.setDifficulty("EASY");
        pancakes.setCategory(categories.get(0)); // Breakfast

        // Dinner recipe
        Recipe pasta = new Recipe();
        pasta.setTitle("Spaghetti Carbonara");
        pasta.setAuthor("Chef Maria");
        pasta.setIngredients("1 pound spaghetti\n8 ounces pancetta or bacon, diced\n4 garlic cloves, minced\n4 large eggs\n1 cup freshly grated Parmesan cheese\n1 teaspoon black pepper\nSalt to taste\nChopped parsley for garnish");
        pasta.setInstructions("1. Bring a large pot of salted water to boil. Add spaghetti and cook until al dente.\n2. While pasta cooks, fry the pancetta in a large skillet until crispy.\n3. Add minced garlic to the pancetta and cook for 30 seconds.\n4. In a bowl, whisk together eggs, Parmesan, and black pepper.\n5. Drain pasta, reserving 1/2 cup of pasta water.\n6. Add hot pasta to the skillet with pancetta. Remove from heat.\n7. Quickly stir in the egg mixture, tossing continuously.\n8. Add pasta water as needed to create a creamy sauce.\n9. Season with salt and garnish with parsley before serving.");
        pasta.setCookingTime(30);
        pasta.setServingSize(4);
        pasta.setDifficulty("MEDIUM");
        pasta.setCategory(categories.get(2)); // Dinner

        // Dessert recipe
        Recipe chocolateCake = new Recipe();
        chocolateCake.setTitle("Decadent Chocolate Cake");
        chocolateCake.setAuthor("Baker Emma");
        chocolateCake.setIngredients("2 cups all-purpose flour\n2 cups sugar\n3/4 cup unsweetened cocoa powder\n2 teaspoons baking soda\n1 teaspoon salt\n2 eggs\n1 cup buttermilk\n1/2 cup vegetable oil\n2 teaspoons vanilla extract\n1 cup hot coffee");
        chocolateCake.setInstructions("1. Preheat oven to 350°F (175°C). Grease and flour two 9-inch round cake pans.\n2. In a large bowl, combine flour, sugar, cocoa powder, baking soda, and salt.\n3. Add eggs, buttermilk, oil, and vanilla. Beat on medium speed for 2 minutes.\n4. Stir in hot coffee (batter will be thin).\n5. Pour batter into prepared pans.\n6. Bake for 30-35 minutes or until a toothpick inserted in center comes out clean.\n7. Cool in pans for 10 minutes, then remove to wire racks to cool completely.\n8. Frost with your favorite chocolate frosting.");
        chocolateCake.setCookingTime(45);
        chocolateCake.setServingSize(12);
        chocolateCake.setDifficulty("MEDIUM");
        chocolateCake.setCategory(categories.get(3)); // Dessert

        recipeRepository.saveAll(Arrays.asList(pancakes, pasta, chocolateCake));
    }
}