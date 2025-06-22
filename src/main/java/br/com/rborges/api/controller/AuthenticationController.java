package br.com.rborges.api.controller;


import br.com.rborges.api.repositories.UsuarioRepository;
import br.com.rborges.api.security.TokenService;
import br.com.rborges.api.model.AuthenticationDTO;
import br.com.rborges.api.model.LoginResponseDTO;
import br.com.rborges.api.model.RegisterDTO;
import br.com.rborges.api.model.Usuario;
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

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
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