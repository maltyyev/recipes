package my.domain.services.impl;

import my.domain.commands.IngredientCommand;
import my.domain.converters.Ingredient2IngredientCommand;
import my.domain.converters.IngredientCommand2Ingredient;
import my.domain.models.Ingredient;
import my.domain.models.Recipe;
import my.domain.repositories.IngredientRepository;
import my.domain.repositories.RecipeRepository;
import my.domain.repositories.UnitOfMeasureRepository;
import my.domain.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by maltyyev on 15.01.18 23:43
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private final Ingredient2IngredientCommand ingredient2IngredientCommand;
    private final IngredientCommand2Ingredient ingredientCommand2Ingredient;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(Ingredient2IngredientCommand ingredient2IngredientCommand,
                                 IngredientCommand2Ingredient ingredientCommand2Ingredient,
                                 RecipeRepository recipeRepository,
                                 IngredientRepository ingredientRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredient2IngredientCommand = ingredient2IngredientCommand;
        this.ingredientCommand2Ingredient = ingredientCommand2Ingredient;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findByIdAndPresentIsTrue(recipeId);

        if (!optionalRecipe.isPresent())
            throw new RuntimeException("LOSER");

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredient2IngredientCommand.convert(ingredient)).findFirst();

        if (!optionalIngredientCommand.isPresent())
            throw new RuntimeException("LOSER");

        return optionalIngredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());

        if (!optionalRecipe.isPresent()) {
            return new IngredientCommand();
        } else {
            Recipe recipe = optionalRecipe.get();

            Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (optionalIngredient.isPresent()) {
                Ingredient ingredientFound = optionalIngredient.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("LOSER")));
            } else {
                Ingredient ingredient = ingredientCommand2Ingredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            return savedIngredientOptional.isPresent() ?
                    ingredient2IngredientCommand.convert(savedIngredientOptional.get()) :
                    new IngredientCommand(savedRecipe.getId());
        }
    }

    @Override
    @Transactional
    public Ingredient deleteIngredientById(Long id) {
        Ingredient toDelete = findById(id);
        toDelete.setPresent(false);
        return ingredientRepository.save(toDelete);
    }

    @Override
    @Transactional
    public Ingredient findById(Long id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findByIdAndPresentIsTrue(id);

        if (!ingredientOptional.isPresent())
            throw new RuntimeException("LOSER");

        return ingredientOptional.get();
    }
}
