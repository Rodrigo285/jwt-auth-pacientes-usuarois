package br.com.rborges.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record PacienteRequestDTO(
        @NotBlank
        String name,

        @NotNull
        String descricao,
        @NotNull
        String email
) {
}
