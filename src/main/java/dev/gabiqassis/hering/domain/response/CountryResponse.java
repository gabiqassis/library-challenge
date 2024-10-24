package dev.gabiqassis.hering.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CountryResponse(
        @JsonProperty("gentilico") String demonym,
        @JsonProperty("nome_pais") String countryName,
        @JsonProperty("nome_pais_int") String internationalCountryName,
        @JsonProperty("sigla") String acronym) {
}