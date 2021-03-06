package my.domain.controllers;

import my.domain.commands.RecipeCommand;
import my.domain.models.Difficulty;
import my.domain.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RecipeController {

    private static final String RECIPE_CREATE_OR_UPDATE = "recipe/createOrUpdate";
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {

        try {
            model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }

        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String getAddRecipePage(Model model) {

        model.addAttribute("recipe", new RecipeCommand());
        model.addAttribute("difficulties", Difficulty.values());
        return RECIPE_CREATE_OR_UPDATE;
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        model.addAttribute("difficulties", Difficulty.values());
        return RECIPE_CREATE_OR_UPDATE;
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {

        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("/recipe/")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.toString());
            });

            return RECIPE_CREATE_OR_UPDATE;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:" + savedCommand.getId() + "/show";
    }
}
