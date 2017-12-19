package my.domain.converters;

import lombok.Synchronized;
import my.domain.commands.IngredientCommand;
import my.domain.models.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by maltyyev on 20.12.17
 */
@Component
public class IngredientCommand2Ingredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommand2UnitOfMeasure uomConverter;

    public IngredientCommand2Ingredient(UnitOfMeasureCommand2UnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {

        final Ingredient ingredient = new Ingredient();
        ingredient.setUom(uomConverter.convert(source.getUom()));
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        return ingredient;

    }
}
