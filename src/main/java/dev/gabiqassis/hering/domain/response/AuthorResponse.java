package dev.gabiqassis.hering.domain.response;

import dev.gabiqassis.hering.domain.enums.GenderEnum;

public record AuthorResponse(
        Long id,
        String name,
        GenderEnum gender,
        String birthDate,
        String email,
        String cpf,
        String countryOrigin
) {
}