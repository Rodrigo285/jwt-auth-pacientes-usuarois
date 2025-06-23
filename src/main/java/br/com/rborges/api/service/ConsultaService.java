package br.com.rborges.api.service;

import br.com.rborges.api.model.Consulta;
import br.com.rborges.api.model.ConsultaDTO;
import br.com.rborges.api.model.Paciente;
import br.com.rborges.api.repositories.ConsultaRepository;
import br.com.rborges.api.repositories.PacienteRepository;

public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }


    public Consulta agendarConsulta(ConsultaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDataHora(dto.dataHora());
        consulta.setDescricao(dto.descricao());
        consulta.setCancelada(false);

        return consultaRepository.save(consulta);
    }

    public void cancelarConsulta(String id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setCancelada(true);
        consultaRepository.save(consulta);
    }
}

