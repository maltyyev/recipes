package my.domain.services.impl;

import lombok.extern.slf4j.Slf4j;
import my.domain.commands.RecipeCommand;
import my.domain.converters.Recipe2RecipeCommand;
import my.domain.converters.RecipeCommand2Recipe;
import my.domain.models.Recipe;
import my.domain.repositories.RecipeRepository;
import my.domain.services.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by maltyyev on 17.12.17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommand2Recipe recipeCommand2Recipe;
    private final Recipe2RecipeCommand recipe2RecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommand2Recipe recipeCommand2Recipe,
                             Recipe2RecipeCommand recipe2RecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommand2Recipe = recipeCommand2Recipe;
        this.recipe2RecipeCommand = recipe2RecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAllByPresentIsTrue().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findByIdAndPresentIsTrue(id);

        if (!recipeOptional.isPresent())
            throw new RuntimeException("YOU'RE A LOSER");

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipe2RecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommand2Recipe.convert(command);
        detachedRecipe.setPresent(true);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipe2RecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public Recipe deleteRecipeById(Long id) {
        Recipe toDelete = findById(id);
        toDelete.setPresent(false);
        return recipeRepository.save(toDelete);
    }
}
