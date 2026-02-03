package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.MovimentacaoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.MovimentacaoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovimentacaoMapper {

    // Converte Model para DTO de resposta
    public MovimentacaoResponseDTO toResponseDTO(MovimentacaoModel model){
        MovimentacaoResponseDTO dto = new MovimentacaoResponseDTO();
        dto.setId(model.getId());
        dto.setTipo(model.getTipo());
        dto.setDescricao(model.getDescricao());
        dto.setData(model.getData());
        dto.setUsuarioNome(model.getUsuario().getNome());

        // Tarefa pode ser null (ex: PROCESSO_CRIADO não tem tarefa)
        if(model.getTarefa() != null){
            dto.setTarefaId(model.getTarefa().getId());
            dto.setTarefaNome(model.getTarefa().getAtividade().getNome());
        }
        return dto;
    }

    // Converte lista de Models para lista de DTOs
    public List<MovimentacaoResponseDTO> toResponseDTOList(List<MovimentacaoModel> models){
        return models.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}
