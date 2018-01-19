package my.domain.controllers;

import my.domain.commands.RecipeCommand;
import my.domain.exceptions.NotFoundException;
import my.domain.models.Difficulty;
import my.domain.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {

        try {
            model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("ID value should be numeric.");
        }

        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String getAddRecipePage(Model model) {

        model.addAttribute("recipe", new RecipeCommand());
        model.addAttribute("difficulties", Difficulty.values());
        return "recipe/createOrUpdate";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        model.addAttribute("difficulties", Difficulty.values());
        return "recipe/createOrUpdate";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {

        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("/recipe/")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:" + savedCommand.getId() + "/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView pageNotFound(Exception e) {

        ModelAndView modelAndView = new ModelAndView("404error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView badRequest(Exception e) {
        ModelAndView modelAndView = new ModelAndView("400error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
