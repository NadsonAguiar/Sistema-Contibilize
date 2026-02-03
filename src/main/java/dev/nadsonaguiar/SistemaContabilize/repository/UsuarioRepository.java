package dev.nadsonaguiar.SistemaContabilize.repository;

import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {}
