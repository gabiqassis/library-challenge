package dev.gabiqassis.hering.domain.response;

import java.time.LocalDate;


public record LiteraryWorkResponse(
        Long id,
        String name,
        String description,
        LocalDate publicationDate,
        LocalDate exhibitionDate
) {
}