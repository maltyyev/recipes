package my.domain.services.impl;

import my.domain.commands.RecipeCommand;
import my.domain.converters.Recipe2RecipeCommand;
import my.domain.converters.RecipeCommand2Recipe;
import my.domain.models.Recipe;
import my.domain.repositories.RecipeRepository;
import my.domain.services.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by maltyyev on 20.12.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    private static final String NEW_DESCRIPTION = "NEW DESCRIPTION";

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    Recipe2RecipeCommand recipe2RecipeCommand;
    @Autowired
    RecipeCommand2Recipe recipeCommand2Recipe;

    @Test
    @Transactional
    public void testSavedObject() throws Exception {

        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe newRecipe = recipes.iterator().next();
        RecipeCommand newRecipeCommand = recipe2RecipeCommand.convert(newRecipe);

        newRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(newRecipeCommand);

        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(newRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(newRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(newRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());

    }

}