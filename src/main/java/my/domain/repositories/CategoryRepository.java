package my.domain.repositories;

import my.domain.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by maltyyev on 17.12.17.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
