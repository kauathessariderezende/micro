package br.edu.atitus.currency_service.controllers;

import br.edu.atitus.currency_service.clients.CurrencyBCClient;
import br.edu.atitus.currency_service.dtos.CurrencyBCResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyBCClient currencyBCClient;

    public CurrencyController(CurrencyBCClient currencyBCClient) {
        this.currencyBCClient = currencyBCClient;
    }

    @GetMapping("/{moeda}")
    public double getCotacao(@PathVariable String moeda) {
        CurrencyBCResponse response = currencyBCClient.getCotacaoMoedaDia(moeda);
        if (response.getValue() != null && !response.getValue().isEmpty()) {
            return response.getValue().get(0).getCotacaoVenda();
        }
        throw new RuntimeException("Cotação não encontrada para " + moeda);
    }
}