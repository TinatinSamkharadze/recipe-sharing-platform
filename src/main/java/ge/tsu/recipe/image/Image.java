package ge.tsu.recipe.image;

import ge.tsu.recipe.recipe.Recipe;
import jakarta.persistence.*;

@Entity
@Table(name = "IMAGES")

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PATH", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    private Recipe recipe;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}