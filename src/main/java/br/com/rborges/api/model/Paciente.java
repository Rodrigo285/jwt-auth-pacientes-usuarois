package br.com.rborges.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Table(name = "pacientes")
@Entity(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "name", length = 50 )
    private String name;

    @Column(nullable = false,  name = "descricao", length = 50)
    private String descricao;

    @Column(nullable = false,  length = 50)
    private String email;

    public Paciente(PacienteRequestDTO data){
        this.descricao = data.descricao();
        this.name = data.name();
    }

    public String getEmail(){
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;

    }
}
