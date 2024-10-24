package dev.gabiqassis.hering.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CountryDTO(
        @JsonProperty("gentilico") String gentilico,
        @JsonProperty("nome_pais") String nomePais,
        @JsonProperty("nome_pais_int") String nomePaisInt,
        @JsonProperty("sigla") String sigla) {}