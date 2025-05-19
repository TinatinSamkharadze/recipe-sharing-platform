package ge.tsu.recipe.recipe;

import ge.tsu.recipe.comment.CommentDTO;
import ge.tsu.recipe.image.ImageDTO;
import ge.tsu.recipe.util.TimeFormatter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class RecipeDTO {
    private Long id;
    private String title;
    private String author;
    private String ingredients;
    private String instructions;
    private Integer cookingTime;
    private Integer servingSize;
    private String difficulty;
    private String excerpt;
    private LocalDateTime createTime;
    private String prettyCreateTime;
    private String categoryName;
    private Long categoryId;
    private List<ImageDTO> images;
    private List<CommentDTO> comments;

    public static RecipeDTO fromRecipe(Recipe recipe) {
        var recipeDTO = new RecipeDTO();
        BeanUtils.copyProperties(recipe, recipeDTO);

        if (recipe.getCategory() != null) {
            recipeDTO.setCategoryName(recipe.getCategory().getName());
            recipeDTO.setCategoryId(recipe.getCategory().getId());
        }

        recipeDTO.setImages(recipe.getImages().stream().map(ImageDTO::fromImage).collect(Collectors.toList()));
        recipeDTO.setComments(recipe.getComments().stream().map(CommentDTO::fromComment).collect(Collectors.toList()));
        recipeDTO.setPrettyCreateTime(TimeFormatter.prettyFormat(recipe.getCreateTime()));
        return recipeDTO;

    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getPrettyCreateTime() {
        return prettyCreateTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setPrettyCreateTime(String prettyCreateTime) {
        this.prettyCreateTime = prettyCreateTime;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}