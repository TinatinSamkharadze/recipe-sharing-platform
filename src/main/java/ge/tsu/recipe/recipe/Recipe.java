package ge.tsu.recipe.recipe;


import ge.tsu.recipe.category.Category;
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
    @Column(name = "INSTRUCTIONS", nullable = false)
    private String instructions;

    @Column(name = "COOKING_TIME", nullable = false)
    private Integer cookingTime; // in minutes

    @Column(name = "SERVING_SIZE", nullable = false)
    private Integer servingSize;

    @Column(name = "DIFFICULTY", nullable = false)
    private String difficulty; // EASY, MEDIUM, HARD

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
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        excerpt = StringUtils.abbreviate(instructions, 150);
    }
}