package dev.gabiqassis.hering.service;

import dev.gabiqassis.hering.domain.dto.CountryDTO;

public interface CountryService {
    CountryDTO searchCountry(String termo);
}
