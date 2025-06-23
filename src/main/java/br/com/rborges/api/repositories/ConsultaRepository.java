package br.com.rborges.api.repositories;

import br.com.rborges.api.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, String > {
    List<Consulta> findByPacienteId(String pacienteId);
}
