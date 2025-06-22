package br.com.rborges.api.repositories;


import br.com.rborges.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    UserDetails findByLogin(String login);
    Optional<Usuario> findById(String id);
}

