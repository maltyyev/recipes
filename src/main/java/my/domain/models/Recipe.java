package my.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by maltyyev on 16.12.17.
 */
@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String hint;

    @Lob
    private String description;

    @Lob
    private String directions;

    @Lob
    private Byte[] image;

    @Lob
    private String notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    private Boolean present;

    /*public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }*/

    @PrePersist
    void prePersist() {
        present = true;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
    }

    public void addIngredients(Collection<Ingredient> ingredients) {
        ingredients.forEach(ingredient -> ingredient.setRecipe(this));
        this.ingredients.addAll(ingredients);
    }
}
