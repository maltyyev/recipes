package my.domain.repositories;

import my.domain.models.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by maltyyev on 17.12.17.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
