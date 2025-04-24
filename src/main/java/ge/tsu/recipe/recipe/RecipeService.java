package ge.tsu.recipe.recipe;

import ge.tsu.recipe.category.Category;
import ge.tsu.recipe.category.CategoryRepository;
import ge.tsu.recipe.image.Image;
import ge.tsu.recipe.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageService imageService;

    public List<RecipeDTO> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable).stream().map(RecipeDTO::fromRecipe).toList();
    }

    public List<RecipeDTO> getRecipesByCategory(Long categoryId, Pageable pageable) {
        return recipeRepository.findByCategoryId(categoryId, pageable).stream().map(RecipeDTO::fromRecipe).toList();
    }

    public RecipeDTO findRecipeById(Long recipeId) {
        Recipe recipe = findById(recipeId);
        return RecipeDTO.fromRecipe(recipe);
    }

    public Recipe findById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    @Transactional
    public RecipeDTO saveRecipe(RecipeForm recipeForm, List<MultipartFile> imageFiles) {
        Recipe recipe = new Recipe();
        recipe.setAuthor(recipeForm.getAuthor());
        recipe.setTitle(recipeForm.getTitle());
        recipe.setIngredients(recipeForm.getIngredients());
        recipe.setInstructions(recipeForm.getInstructions());
        recipe.setCookingTime(recipeForm.getCookingTime());
        recipe.setServingSize(recipeForm.getServingSize());
        recipe.setDifficulty(recipeForm.getDifficulty());

        // Set category if provided
        if (recipeForm.getCategoryId() != null) {
            Optional<Category> category = categoryRepository.findById(recipeForm.getCategoryId());
            category.ifPresent(recipe::setCategory);
        }

        recipe = recipeRepository.save(recipe);

        // Save image files
        if (imageFiles != null && !imageFiles.isEmpty()) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                if (imageFile.isEmpty()) {
                    continue;
                }
                String imagePath = imageService.saveImageFile(imageFile);
                Image image = new Image();
                image.setRecipe(recipe);
                image.setPath(imagePath);
                images.add(image);
            }
            recipe.setImages(images);
        }
        recipe = recipeRepository.save(recipe);

        return RecipeDTO.fromRecipe(recipe);
    }

    public long totalNumberOfRecipes() {
        return recipeRepository.count();
    }

    public long totalNumberOfRecipesByCategory(Long categoryId) {
        return recipeRepository.countByCategoryId(categoryId);
    }

    public List<RecipeDTO> searchRecipes(String query, Pageable pageable) {
        return recipeRepository.searchRecipes(query, pageable)
                .stream()
                .map(RecipeDTO::fromRecipe)
                .toList();
    }

    public long countSearchResults(String query) {
        return recipeRepository.countSearchResults(query);
    }
}