package br.com.rborges.api.model;

import java.time.LocalDateTime;

public record ConsultaDTO(String pacienteId, LocalDateTime dataHora, String descricao) {

}


