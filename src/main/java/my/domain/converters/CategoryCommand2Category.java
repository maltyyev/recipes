package my.domain.converters;

import lombok.Synchronized;
import my.domain.commands.CategoryCommand;
import my.domain.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommand2Category implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null)
            return null;

        final Category cat = new Category();
        cat.setId(source.getId());
        cat.setDescription(source.getDescription());
        return cat;

    }
}
