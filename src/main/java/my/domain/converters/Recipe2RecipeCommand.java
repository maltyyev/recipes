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
public class Recipe2RecipeCommand implements Converter<Recipe, RecipeCommand> {

    private Category2CategoryCommand categoryConverter;
    private Ingredient2IngredientCommand ingredientConverter;
    private Notes2NotesCommand notesConverter;

    public Recipe2RecipeCommand(Category2CategoryCommand categoryConverter,
                                Ingredient2IngredientCommand ingredientConverter,
                                Notes2NotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;

        final RecipeCommand recipe = new RecipeCommand();

        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        source.getCategories().forEach(category ->
                recipe.getCategories().add(categoryConverter.convert(category)));
        source.getIngredients().forEach(ingredient ->
                recipe.getIngredients().add(ingredientConverter.convert(ingredient)));

        return recipe;

    }
}
