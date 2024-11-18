package org.proyecto.primerproyecto.services;

import org.proyecto.primerproyecto.models.Usuario;
import org.proyecto.primerproyecto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }
    public Optional<Usuario> getUserById(Long id) {
        return this.userRepository.findById(id);
    }
    public Usuario update(Usuario usuario) {
        return this.userRepository.save(usuario);
    }
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
    public Optional<Usuario> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
