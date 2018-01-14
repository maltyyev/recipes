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
        guacamoleRecipe.setTitle("Perfect Guacamole");
        guacamoleRecipe.setDescription("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity to balance the richness of the avocado. Then comes chopped cilantro, chiles, onion, and tomato, if you want.\n" +
                "\n" +
                "The trick to making perfect guacamole is using good, ripe avocados. Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(30);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setHint("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips.");

        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

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

        guacamoleRecipe.setSource("Simply Recipes");
        guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole");

        recipes.add(guacamoleRecipe);

        return recipes;
    }

}
