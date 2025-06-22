package br.com.rborges.api.controller;

import br.com.rborges.api.model.Paciente;
import br.com.rborges.api.model.PacienteResponseDTO;
import br.com.rborges.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<String> cadastrarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteExistente = pacienteRepository.findByEmail(paciente.getEmail());
        if(pacienteExistente.isPresent()) throw new RuntimeException("Paciente ja existe.");
        pacienteRepository.saveAndFlush(paciente);
        return ResponseEntity.ok("Paciente casdastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPaciente( ){
        List<Paciente> listaAtualizada = this.pacienteRepository.findAll();
        return ResponseEntity.ok(listaAtualizada);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarPacientePorId(@PathVariable String id, @RequestBody Paciente paciente) {
        Paciente existente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não existe."));

        if (paciente.getDescricao() != null) {
            existente.setDescricao(paciente.getDescricao());
        }

        pacienteRepository.save(existente);

        return ResponseEntity.ok("Descrição atualizada com sucesso.");
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarPacientePorId(@PathVariable String id){
        Optional<Paciente> pacienteEntity = pacienteRepository.findById(id);
        if(pacienteEntity.isEmpty())throw new RuntimeException("Paciente nao existe.");
        pacienteRepository.deleteById(id);
        return ResponseEntity.ok("Paciente excluido com sucesso...");
    }



}


