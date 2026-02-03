package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.UsuarioRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.UsuarioResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {

    // Converte DTO de requisição para Model
    public UsuarioModel toModel(UsuarioRequestDTO dto){
        UsuarioModel model = new UsuarioModel();
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());

        return model;
    }

    // Atualiza um Model existente com dados do DTO
    public void updateModel(UsuarioRequestDTO dto, UsuarioModel model){
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
    }

    // Converte Model para DTO de resposta
    public UsuarioResponseDTO toResponseDTO(UsuarioModel model){
        return  new UsuarioResponseDTO(
            model.getId(),
            model.getNome(),
            model.getEmail()
        );
    }

    // Converte lista de Models para lista de DTOs
    public List<UsuarioResponseDTO> toResponseDTOList(List<UsuarioModel> models){
        return models.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}
