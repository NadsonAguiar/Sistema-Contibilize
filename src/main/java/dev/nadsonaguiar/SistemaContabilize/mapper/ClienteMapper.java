package dev.nadsonaguiar.SistemaContabilize.mapper;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ClienteRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ClienteResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.model.ClienteModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    // Converte DTO de requisição para Model
    public ClienteModel toModel(ClienteRequestDTO dto){
        ClienteModel model = new ClienteModel();
        model.setNome(dto.getNome());
        model.setCpf(dto.getCpf());
        model.setEmail(dto.getEmail());
        model.setTelefone(dto.getTelefone());
        return model;
    }
    //Atualiza um Model existente com dados do DTO (usado para edição)
    public void updateModel(ClienteRequestDTO dto, ClienteModel model){
        model.setNome(dto.getNome());
        model.setCpf(dto.getCpf());
        model.setEmail(dto.getEmail());
        model.setTelefone(dto.getTelefone());
    }
    // Converte Model para DTO de resposta
    public ClienteResponseDTO toResponseDTO(ClienteModel model){
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setCpf(model.getCpf());
        dto.setEmail(model.getEmail());
        dto.setTelefone(model.getTelefone());
        // Conta quantos processos o cliente tem (se a lista estiver carregada)
        dto.setTotalProcessos(
                model.getProcessos() != null ? model.getProcessos().size() : 0
        );
        return dto;
    }
    // Converte lista de Models para lista de DTOs
    public List<ClienteResponseDTO> toResponseDTOList(List<ClienteModel> models){
        return models.stream()
                .map(this::toResponseDTO)
                .toList();
    }

}

