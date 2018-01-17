package my.domain.converters;

import lombok.Synchronized;
import my.domain.commands.RecipeCommand;
import my.domain.models.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by maltyyev on 20.12.17
 */
@Component
public class RecipeCommand2Recipe implements Converter<RecipeCommand, Recipe> {

    private CategoryCommand2Category categoryConverter;
    private IngredientCommand2Ingredient ingredientConverter;
    private NotesCommand2Notes notesConverter;

    public RecipeCommand2Recipe(CategoryCommand2Category categoryConverter,
                                IngredientCommand2Ingredient ingredientConverter,
                                NotesCommand2Notes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        final Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setTitle(source.getTitle());
        recipe.setHint(source.getHint());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(source.getNotes());

        source.getCategories().forEach(categoryCommand ->
                recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        source.getIngredients().forEach(ingredientCommand ->
                recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));

        return recipe;

    }
}
