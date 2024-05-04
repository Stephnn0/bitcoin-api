package com.tradingcryptos.coinbaseapi.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tradingcryptos.coinbaseapi.models.CryptoPrice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;


@RestController
@RequestMapping("/api/prices")
public class CoinBaseController {

    private final WebClient webClient;

    public CoinBaseController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.coinbase.com/v2/").build();
    }

    @GetMapping("/bitcoin")
    public Mono<String> getBitcoinPrice() {
        return webClient.get()
                .uri("prices/spot?currency=USD")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/cryptos")
    public Flux<CryptoPrice> getCryptoPrices(@RequestParam List<String> symbols) {
        return Flux.fromIterable(symbols)
                .flatMap(symbol ->
                        webClient.get()
                                .uri("prices/{symbol}-USD/spot", symbol)
                                .retrieve()
                                .bodyToMono(String.class)
                                .map(json -> {
                                    // Parse the JSON string and extract relevant fields
                                    JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
                                    JsonObject data = jsonObject.getAsJsonObject("data");
                                    String amount = data.get("amount").getAsString();
                                    String base = data.get("base").getAsString();
                                    String currency = data.get("currency").getAsString();

                                    // Create and return a new CryptoPrice object
                                    return new CryptoPrice(amount, base, currency);
                                })
                );
    }


    @GetMapping("/cryptosraw")
    public Flux<String> getCryptoPricesNoMapping(@RequestParam List<String> symbols) {
        return Flux.fromIterable(symbols)
                .flatMap(symbol ->
                        webClient.get()
                                .uri("prices/{symbol}-USD/spot", symbol)
                                .retrieve()
                                .bodyToMono(String.class)
                );
    }

}
