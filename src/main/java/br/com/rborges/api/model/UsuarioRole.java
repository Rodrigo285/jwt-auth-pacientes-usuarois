package br.com.rborges.api.model;

public enum UsuarioRole {
    ADMIN("admin"),
    PACIENTE("paciente"),
    USER("user");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
