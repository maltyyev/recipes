package my.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by maltyyev on 16.12.17.
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure uom;

    private Boolean present;

    @PrePersist
    void prePersist() {
        present = true;
    }

    public Ingredient() {}

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
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
