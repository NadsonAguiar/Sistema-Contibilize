package dev.nadsonaguiar.SistemaContabilize.repository;

import dev.nadsonaguiar.SistemaContabilize.model.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, Long> {

    /**
     * Busca todas as movimentações de um processo,
     * ordenadas da mais recente para a mais antiga.
     */
    List<MovimentacaoModel> findByProcessoIdOrderByDataDesc(Long processoId);

    /**
     * Busca movimentações de uma tarefa específica.
     */
    List<MovimentacaoModel> findByTarefaIdOrderByDataDesc(Long tarefaId);
}
