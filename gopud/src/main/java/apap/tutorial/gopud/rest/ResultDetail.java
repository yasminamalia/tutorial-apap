package apap.tutorial.gopud.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDetail {
    @JsonProperty("results")
    List<RecipeDetail> recipes;

    public List<RecipeDetail> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDetail> recipes) {
        this.recipes = recipes;
    }
}
