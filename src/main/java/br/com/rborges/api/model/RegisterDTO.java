package br.com.rborges.api.model;

public record RegisterDTO(String login, String password, UsuarioRole role) {
}
