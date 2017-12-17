package my.domain.controllers;

import lombok.extern.slf4j.Slf4j;
import my.domain.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by maltyyev on 16.12.17.
 */
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        log.debug("Mapping the \"/ path\"");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

}
