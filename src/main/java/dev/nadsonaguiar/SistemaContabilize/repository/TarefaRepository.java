package dev.nadsonaguiar.SistemaContabilize.repository;

import dev.nadsonaguiar.SistemaContabilize.Enum.StatusTarefa;
import dev.nadsonaguiar.SistemaContabilize.model.TarefaModel;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    // Busca tarefas do usuário com status específicos
    List<TarefaModel> findByResponsavelIdAndStatusInOrderByDataPrevistaAsc(
        Long usuarioId,
        List<StatusTarefa> status
    );

    // Busca tarefas de um processo com status específicos
    List<TarefaModel> findByProcessoIdAndStatusIn(Long processoId,
                                                  List<StatusTarefa> status );

}
