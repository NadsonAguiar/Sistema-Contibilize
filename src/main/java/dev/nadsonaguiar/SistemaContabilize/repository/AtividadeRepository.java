package dev.nadsonaguiar.SistemaContabilize.repository;

import dev.nadsonaguiar.SistemaContabilize.model.AtividadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeModel, Long> {}
