package my.domain.commands;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

    public IngredientCommand() {
    }

    public IngredientCommand(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return (amount.doubleValue() == 0.5 ? "half" : amount.stripTrailingZeros().toString())
                + " "
                + (uom.getDescription().equals("Each") ? "" : uom.getDescription() + " of")
                + " "
                + description;
    }
}
