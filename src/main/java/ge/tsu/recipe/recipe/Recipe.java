package ge.tsu.recipe.recipe;

import ge.tsu.recipe.category.Category;
import ge.tsu.recipe.comment.Comment;
import ge.tsu.recipe.image.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RECIPES")
@Getter
@Setter
public class Recipe {


    public Recipe(String title, String description, String ingredients, String instructions, String author) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.author = author;

    }


    public Recipe() {

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHOR", nullable = false, length = 50)
    private String author;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "INGREDIENTS", nullable = false)
    private String ingredients;

    @Lob
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Lob
    @Column(name = "INSTRUCTIONS", nullable = false)
    private String instructions;

    @Column(name = "COOKING_TIME", nullable = false)
    private Integer cookingTime;

    @Column(name = "SERVING_SIZE", nullable = false)
    private Integer servingSize;

    @Column(name = "DIFFICULTY", nullable = false)
    private String difficulty;

    @Column(name = "EXCERPT", nullable = false)
    private String excerpt;

    @Column(name = "CREATE_TIME", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


    @OneToMany(mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Image> images = new ArrayList<Image>();

    @OneToMany(mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        excerpt = StringUtils.abbreviate(instructions, 150);
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

    public void setId(Long id) {
        this.id = id;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public Integer getCookingTime() {
        return cookingTime;
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

    public Category getCategory() {
        return category;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}