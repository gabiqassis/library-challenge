package dev.gabiqassis.hering.controller.impl;

import dev.gabiqassis.hering.controller.CountryController;
import dev.gabiqassis.hering.domain.mapper.CountryMapper;
import dev.gabiqassis.hering.domain.response.CountryResponse;
import dev.gabiqassis.hering.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryControllerImpl implements CountryController {

    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @Override
    public ResponseEntity<CountryResponse> buscarPais(String termo) {
        return ResponseEntity.ok(countryMapper.map(countryService.searchCountry(termo)));
    }
}