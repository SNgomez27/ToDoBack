package org.proyecto.primerproyecto.repositories;

import org.proyecto.primerproyecto.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

 public interface RolRepository extends JpaRepository<Rol, Long> {
     Optional<Rol> findByRol(String rol);
}
