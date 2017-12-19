package my.domain.bootstrap;

import lombok.extern.slf4j.Slf4j;
import my.domain.models.*;
import my.domain.repositories.CategoryRepository;
import my.domain.repositories.RecipeRepository;
import my.domain.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by maltyyev on 17.12.17.
 */
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }

    private List<Recipe> getRecipes() {
        log.debug("Getting some recipes");
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("eachUnitOfMeasureOptional");
        }
        Optional<UnitOfMeasure> tablespoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("tablespoonUnitOfMeasureOptional");
        }
        Optional<UnitOfMeasure> teaspoonUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("teaspoonUnitOfMeasureOptional");
        }
        Optional<UnitOfMeasure> dashUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("dashUnitOfMeasureOptional");
        }
        Optional<UnitOfMeasure> pintUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("pintUnitOfMeasureOptional");
        }
        Optional<UnitOfMeasure> cupUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUnitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("cupUnitOfMeasureOptional");
        }

        UnitOfMeasure eachUOM = eachUnitOfMeasureOptional.get();
        UnitOfMeasure tablespoonUOM = tablespoonUnitOfMeasureOptional.get();
        UnitOfMeasure teaspoonUOM = teaspoonUnitOfMeasureOptional.get();
        UnitOfMeasure dashUOM = dashUnitOfMeasureOptional.get();
        UnitOfMeasure pintUOM = pintUnitOfMeasureOptional.get();
        UnitOfMeasure cupUOM = cupUnitOfMeasureOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("americanCategoryOptional");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("mexicanCategoryOptional");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("SHUT DA FUCK UP!!!");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("SHUT DA FUCK UP!!!");

        guacamoleRecipe.setNotes(guacamoleNotes);

        Ingredient[] ingredients = {
                new Ingredient("ripe avocados", new BigDecimal(2), eachUOM),
                new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUOM),
                new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUOM),
                new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUOM),
                new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUOM),
                new Ingredient("Cilantro", new BigDecimal(2), tablespoonUOM),
                new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUOM),
                new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUOM)
        };

        guacamoleRecipe.addIngredients(Arrays.asList(ingredients));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        guacamoleRecipe.setUrl("http://shut.da.fuck.up");

        recipes.add(guacamoleRecipe);

        return recipes;
    }

}
