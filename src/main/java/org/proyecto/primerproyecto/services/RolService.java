package org.proyecto.primerproyecto.services;

import org.proyecto.primerproyecto.models.Rol;
import org.proyecto.primerproyecto.repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }
    public Optional<Rol> findById(Long id) {
        return this.rolRepository.findById(id);
    }
    public Rol update(Rol rol) {
        return this.rolRepository.save(rol);
    }
    public void delete(Long id) {
        this.rolRepository.deleteById(id);
    }
    public Optional<Rol> findByRol(String rol) {
        return this.rolRepository.findByRol(rol);
    }
}
