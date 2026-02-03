package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.TarefaRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.TarefaResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.AtividadeModel;
import dev.nadsonaguiar.SistemaContabilize.model.ProcessoModel;
import dev.nadsonaguiar.SistemaContabilize.model.TarefaModel;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefaMapper {

    // Converte DTO de requisição para Model
    // ATENÇÃO: Você precisa buscar Atividade, Processo e Usuario separadamente!
    public TarefaModel toModel(TarefaRequestDTO dto, AtividadeModel atividade, ProcessoModel processo,
                                    UsuarioModel responsavel, UsuarioModel criador) {
        TarefaModel model = new TarefaModel();
        model.setAtividade(atividade);
        model.setProcesso(processo);
        model.setResponsavel(responsavel);
        model.setCriador(criador);
        model.setDataInicio(dto.getDataInicio());
        model.setDataPrevista(dto.getDataPrevista());
        model.setDescricao(dto.getDescricao());
        // dataCriacao e status são preenchidos automaticamente pelo @PrePersist
        return model;
    }

    // Converte Model para DTO de resposta
    public TarefaResponseDTO toResponseDTO(TarefaModel model) {
        TarefaResponseDTO dto = new TarefaResponseDTO();
        dto.setId(model.getId());
        dto.setAtividadeNome(model.getAtividade().getNome());
        dto.setProcessoId(model.getProcesso().getId());
        dto.setProcessoTipo(model.getProcesso().getTipoProcesso());
        dto.setResponsavelId(model.getResponsavel().getId());
        dto.setResponsavelNome(model.getResponsavel().getNome());
        dto.setStatus(model.getStatusAtual());
        dto.setDataInicio(model.getDataInicio());
        dto.setDataPrevista(model.getDataPrevista());
        dto.setDataCriacao(model.getDataCriacao());
        dto.setDataConclusao(model.getDataConclusao());
        dto.setDescricao(model.getDescricao());
        return dto;
    }

    // Converte lista de Models para lista de DTOs
    public List<TarefaResponseDTO> toResponseDTOList(List<TarefaModel> models) {
        return models.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
