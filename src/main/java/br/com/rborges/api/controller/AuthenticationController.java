package br.com.rborges.api.controller;


import br.com.rborges.api.model.*;
import br.com.rborges.api.repositories.PacienteRepository;
import br.com.rborges.api.repositories.UsuarioRepository;
import br.com.rborges.api.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("registro")
public class AuthenticationController {

    private  final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private  final TokenService tokenService;
    private  final PacienteRepository pacienteRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService, PacienteRepository pacienteRepository) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario usuario = new Usuario(data.login(), encryptedPassword, data.role());

        if (data.pacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(data.pacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
            usuario.setPaciente(paciente);
        }

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable String id){
        Optional<Usuario> usuarioEntity = usuarioRepository.findById(id);
        if(usuarioEntity.isEmpty())
        throw new RuntimeException("Usuario nao existe. ");
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuario deletado com sucesso...");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id){
        Optional<Usuario> usuarioEntity = usuarioRepository.findById(id);
        if(usuarioEntity.isEmpty())throw new RuntimeException("Usuario nao existe.");
        return ResponseEntity.ok(usuarioEntity.get());
    }
    

        

}