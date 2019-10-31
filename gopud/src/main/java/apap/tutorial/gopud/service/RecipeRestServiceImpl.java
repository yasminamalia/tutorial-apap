package apap.tutorial.gopud.service;

import apap.tutorial.gopud.rest.ResultDetail;
import apap.tutorial.gopud.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class RecipeRestServiceImpl implements RecipeRestService{
    private final WebClient webClient;
    private String apiKey = "5c2754799eba468eb2bd2b1f234c8d12";

    public RecipeRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.recipeUrl).build();
    }

    @Override
    public Mono<ResultDetail> getRecipe(String excluedIng, String cuisine) {
        return this.webClient.get().uri(uriBuilder -> uriBuilder.queryParam("excludeIngredients", excluedIng)
        .queryParam("cuisine", cuisine).queryParam("apiKey", apiKey).build())
                .retrieve().bodyToMono(ResultDetail.class);
    }
}
