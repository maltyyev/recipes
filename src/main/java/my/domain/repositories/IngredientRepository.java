package my.domain.repositories;

import my.domain.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by maltyyev on 15.01.18 23:45
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Iterable<Ingredient> findAllByRecipe_IdAndPresentIsTrue(Long recipeId);
    Optional<Ingredient> findByIdAndPresentIsTrue(Long id);
}
