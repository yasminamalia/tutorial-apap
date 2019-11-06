package apap.tutorial.gopud.restcontroller;

import apap.tutorial.gopud.rest.ResultDetail;
import apap.tutorial.gopud.service.RecipeRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class RecipeRestController {
    @Autowired
    private RecipeRestService recipeRestService;

    @GetMapping("/recipe")
    private Mono<ResultDetail> getRecipe(@RequestParam (value = "excludeIngredients") String excludeIng){
        return recipeRestService.getRecipe(excludeIng, "german");
    }
}
