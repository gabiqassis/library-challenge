package dev.gabiqassis.hering.repository;

import dev.gabiqassis.hering.domain.dto.CountryDTO;

import java.util.List;

public interface CountryRepository {
    List<CountryDTO> findAll();
}
