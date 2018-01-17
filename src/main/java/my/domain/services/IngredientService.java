package my.domain.services;

import my.domain.commands.IngredientCommand;
import my.domain.models.Ingredient;

/**
 * Created by maltyyev on 15.01.18 23:42
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    Ingredient deleteIngredientById(Long id);
    Ingredient findById(Long id);

}
