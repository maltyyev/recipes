package my.domain.converters;

import lombok.Synchronized;
import my.domain.commands.CategoryCommand;
import my.domain.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Category2CategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null)
            return null;

        final CategoryCommand cat = new CategoryCommand();
        cat.setId(source.getId());
        cat.setDescription(source.getDescription());
        return cat;

    }
}
