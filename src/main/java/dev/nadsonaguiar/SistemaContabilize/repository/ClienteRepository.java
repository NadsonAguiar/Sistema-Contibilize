package dev.nadsonaguiar.SistemaContabilize.repository;

import dev.nadsonaguiar.SistemaContabilize.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
