package dev.gabiqassis.hering.service.impl;

import dev.gabiqassis.hering.domain.response.ReversedResponse;
import dev.gabiqassis.hering.service.NumberReverserService;
import org.springframework.stereotype.Service;


@Service
public class NumberReverserServiceImpl implements NumberReverserService {

    @Override
    public ReversedResponse reverseNumber(Integer number) {
        if (number < 100 || number > 999) {
            throw new IllegalArgumentException("O número deve ser um inteiro de 3 dígitos.");
        }
        int centena = number / 100;
        int dezena = (number % 100) / 10;
        int unidade = number % 10;
        int invertido = unidade * 100 + dezena * 10 + centena;
        return new ReversedResponse(invertido, number);
    }
}