package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ProcessoRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.MovimentacaoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ProcessoDetalheResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ProcessoResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.TarefaResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.ClienteModel;
import dev.nadsonaguiar.SistemaContabilize.model.ProcessoModel;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProcessoMapper {
    // Converte DTO de requisição para Model
    // ATENÇÃO: Você precisa buscar o Cliente e Usuario separadamente!
    public ProcessoModel toModel(ProcessoRequestDTO dto, ClienteModel cliente, UsuarioModel criador){
        ProcessoModel model = new ProcessoModel();
        model.setCliente(cliente);
        model.setTipoProcesso(dto.getTipoProcesso());
        model.setCriador(criador);
        model.setDataCriacao(LocalDateTime.now());
        return model;
    }

    // Converte Model para DTO de resposta simples
    public ProcessoResponseDTO toResponseDTO(ProcessoModel model){
        ProcessoResponseDTO dto = new ProcessoResponseDTO();
        dto.setId(model.getId());
        dto.setTipoProcesso(model.getTipoProcesso());
        dto.setClienteId(model.getCliente().getId());
        dto.setClienteNome(model.getCliente().getNome());
        dto.setDataCriacao(model.getDataCriacao());
        dto.setCriadoPorNome(model.getCriador().getNome());
        return dto;
    }

    // Converte lista de Models para lista de DTOs
    public List<ProcessoResponseDTO> toResponseDTOList(List<ProcessoModel> models){
        return models.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Converte Model para DTO de detalhes completo
    // (usado na tela de detalhes do processo)
    public ProcessoDetalheResponseDTO toDetalheResponseDTO(ProcessoModel model, List<MovimentacaoResponseDTO> movimentacoes,
                                                           List<TarefaResponseDTO>  tarefasAtivas){
        ProcessoDetalheResponseDTO dto = new ProcessoDetalheResponseDTO();
        // Dados básicos do processo
        dto.setId(model.getId());
        dto.setTipoProcesso(model.getTipoProcesso());
        dto.setDataCriacao(model.getDataCriacao());
        // Dados do cliente
        dto.setClienteId(model.getCliente().getId());
        dto.setClienteNome(model.getCliente().getNome());
        // Quem criou
        dto.setCriadoPorNome(model.getCriador().getNome());
        dto.setMovimentacoes(movimentacoes);
        dto.setTarefasAtivas(tarefasAtivas);
        return dto;
    }
}
