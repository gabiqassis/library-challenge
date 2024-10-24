package dev.gabiqassis.hering.domain.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;


@Validated
public record AuthorUpdateRequest(
        @NotBlank(message = "O campo nome é obrigatório") String name,
        @Email(message = "E-mail inválido") String email,
        @NotBlank(message = "O campo país de origem é obrigatório") String countryOrigin
) {
}