package br.com.rborges.api.model;

public record PacienteResponseDTO(String id, String name, String descricao) {
    public PacienteResponseDTO(Paciente paciente){
        this(paciente.getId(), paciente.getName(), paciente.getDescricao());
    }
}
