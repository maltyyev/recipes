package my.domain.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.domain.models.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String title;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String hint;
    private String description;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private String notes;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Byte[] image;
}
