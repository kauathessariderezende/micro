package br.edu.atitus.currency_service.controllers;

import br.edu.atitus.currency_service.clients.CurrencyBCClient;
import br.edu.atitus.currency_service.dtos.CurrencyBCResponse;
import br.edu.atitus.currency_service.dtos.CurrencyBCResponse.Currency;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyBCClient currencyBCClient;

    @Test
    public void testGetCotacao() throws Exception {
        Currency currency = new Currency();
        currency.setCotacaoVenda(5.25);

        CurrencyBCResponse response = new CurrencyBCResponse();
        response.setValue(Collections.singletonList(currency));

        Mockito.when(currencyBCClient.getCotacaoMoedaDia("USD")).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/currency/USD"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("5.25"));
    }
}
