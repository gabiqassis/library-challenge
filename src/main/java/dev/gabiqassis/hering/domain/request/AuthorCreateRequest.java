package dev.gabiqassis.hering.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.gabiqassis.hering.domain.enums.GenderEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
public record AuthorCreateRequest(
        @NotBlank(message = "O campo nome é obrigatório") String name,
        GenderEnum gender,
        @Email(message = "E-mail inválido") String email,
        @JsonFormat(pattern = "dd-MM-yyyy") @NotNull(message = "O campo data de nascimento é obrigatório") Date birthDate,
        @NotBlank(message = "O campo país de origem é obrigatório") String countryOrigin,
        String cpf
) {
}