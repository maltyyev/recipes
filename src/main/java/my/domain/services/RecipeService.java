package my.domain.services;

import my.domain.models.Recipe;

import java.util.Set;

/**
 * Created by maltyyev on 17.12.17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

}