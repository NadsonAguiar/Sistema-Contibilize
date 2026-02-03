package dev.nadsonaguiar.SistemaContabilize.repository;

import dev.nadsonaguiar.SistemaContabilize.model.ProcessoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long> {

    // Busca processos por cliente
    List<ProcessoModel> findByClienteId(Long clienteId);


}

