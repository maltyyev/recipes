package my.domain.repositories;

import my.domain.models.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by maltyyev on 17.12.17.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
