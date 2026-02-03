package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.TarefaDashboardDTO;
import dev.nadsonaguiar.SistemaContabilize.model.TarefaModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefaDashboardMapper {

    // Converte Model para DTO de Dashboard(versão simplificada para a tela de compromissos)
        public TarefaDashboardDTO toDTO(TarefaModel model){
            return new TarefaDashboardDTO(
                    model.getId(),
                    model.getAtividade().getNome(),
                    model.getProcesso().getId(),
                    model.getProcesso().getTipoProcesso(),
                    model.getDataInicio(),
                    model.getDataPrevista(),
                    model.getStatusAtual(),
                    model.getDescricao()
            );
        }

        public List<TarefaDashboardDTO> toDTOList(List<TarefaModel> models){
            return  models
                    .stream()
                    .map(this::toDTO)
                    .toList();
        }
}
