package dev.gabiqassis.hering.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record LiteraryWorkCreateRequest(
        @NotBlank(message = "O campo nome é obrigatório") String name,
        @NotBlank(message = "O campo descricao é obrigatório")
        @Size(max = 240, message = "Descrição não pode ter mais de 240 caracteres")
        String description,
        @JsonFormat(pattern = "dd-MM-yyyy") LocalDate publicationDate,
        @JsonFormat(pattern = "dd-MM-yyyy") LocalDate exhibitionDate,
        List<Long> authors
) {
}