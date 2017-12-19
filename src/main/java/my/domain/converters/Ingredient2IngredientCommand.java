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
public class Ingredient2IngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasure2UnitOfMeasureCommand uomConverter;

    public Ingredient2IngredientCommand(UnitOfMeasure2UnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null)
            return null;

        final IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));
        return ingredient;


    }
}
