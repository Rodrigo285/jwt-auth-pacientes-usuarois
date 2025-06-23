package br.com.rborges.api.controller;

import br.com.rborges.api.model.Consulta;
import br.com.rborges.api.model.ConsultaDTO;
import br.com.rborges.api.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody ConsultaDTO dto) {
        Consulta consulta = consultaService.agendarConsulta(dto);
        return ResponseEntity.ok("Consulta agendada com ID: " + consulta.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelar(@PathVariable String id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.ok("Consulta cancelada");
    }
}

