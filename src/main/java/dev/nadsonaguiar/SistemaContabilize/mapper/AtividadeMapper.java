package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.AtividadeRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.AtividadeResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.AtividadeModel;
import dev.nadsonaguiar.SistemaContabilize.model.TarefaModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtividadeMapper {
    // Converte DTO de requisição para Model
    public AtividadeModel toModel(AtividadeRequestDTO dto) {
        AtividadeModel atividadeModel = new AtividadeModel();
        atividadeModel.setNome(dto.nome());
        return atividadeModel;
    }

    // Converte Model para DTO de resposta
    public AtividadeResponseDTO toResponseDTO(AtividadeModel model) {
        return new AtividadeResponseDTO(
                model.getId(),
                model.getNome()
        );
    }

    // Converte lista de Models para lista de DTOs
    public List<AtividadeResponseDTO> toResponseDTOList(List<AtividadeModel> models) {
        return models.stream()
                .map(this::toResponseDTO)
                .toList();
    }



}
