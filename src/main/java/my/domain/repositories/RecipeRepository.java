package my.domain.repositories;

import my.domain.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by maltyyev on 17.12.17.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findByIdAndPresentIsTrue(Long id);
    Iterable<Recipe> findAllByPresentIsTrue();
}
