package my.domain.controllers;

import my.domain.commands.IngredientCommand;
import my.domain.commands.RecipeCommand;
import my.domain.commands.UnitOfMeasureCommand;
import my.domain.models.Ingredient;
import my.domain.services.IngredientService;
import my.domain.services.RecipeService;
import my.domain.services.UomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by maltyyev on 15.01.18 23:12
 */
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UomService uomService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UomService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/show")
    public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));
        model.addAttribute("UOMs", uomService.listAll());
        return "recipe/ingredient/show";
    }

    @PostMapping
    @RequestMapping("/ingredient/")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients";
    }

    @RequestMapping("/recipe/{recipeId}/ingredients/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(Long.valueOf(recipeId));
        command.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", command);
        model.addAttribute("UOMs", uomService.listAll());
        return "recipe/ingredient/show";
    }

    @RequestMapping("/ingredients/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String ingredientId) {
        Ingredient deletedIngredient = ingredientService.deleteIngredientById(Long.valueOf(ingredientId));
        return "redirect:/recipe/" + deletedIngredient.getRecipe().getId() + "/ingredients";
    }
}
