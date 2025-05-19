package ge.tsu.recipe.comment;

import ge.tsu.recipe.recipe.Recipe;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Lob
    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "CREATE_TIME", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    private Recipe recipe;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}