package br.com.rborges.api.repositories;

import br.com.rborges.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
    Optional<Paciente> findByEmail(String email);
}
