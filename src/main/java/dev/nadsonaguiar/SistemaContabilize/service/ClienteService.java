package dev.nadsonaguiar.SistemaContabilize.service;

import dev.nadsonaguiar.SistemaContabilize.dto.requestDTO.ClienteRequestDTO;
import dev.nadsonaguiar.SistemaContabilize.dto.responseDTO.ClienteResponseDTO;
import dev.nadsonaguiar.SistemaContabilize.mapper.ClienteMapper;
import dev.nadsonaguiar.SistemaContabilize.model.ClienteModel;
import dev.nadsonaguiar.SistemaContabilize.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper     = clienteMapper;
    }

    // Cria um cliente
    public ClienteResponseDTO criar(ClienteRequestDTO dto){
        ClienteModel model = clienteMapper.toModel(dto);
        ClienteModel saved = clienteRepository.save(model);
        return clienteMapper.toResponseDTO(saved);
    }

    // Lista todos os clientes
    public List<ClienteResponseDTO> listarTodos(){
        List<ClienteModel> clientes = clienteRepository.findAll();
        return clienteMapper.toResponseDTOList(clientes);
    }

    //Busca cliente por ID
    public ClienteModel buscarPorId(Long id){
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // Busca cliente por ID e retorna DTO
    public ClienteResponseDTO buscarPorIdDto(Long id){
        ClienteModel cliente = buscarPorId(id);
        return clienteMapper.toResponseDTO(cliente);
    }

    // Atualiza dados de um cliente
    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto){
        ClienteModel model = buscarPorId(id);
        clienteMapper.updateModel(dto, model);
        ClienteModel update = clienteRepository.save(model);
        return clienteMapper.toResponseDTO(update);
    }

    // Deleta um cliente (se não tiver processos vinculados)
    public void deletar(Long id){
        ClienteModel cliente = buscarPorId(id);
        // Valida se o cliente tem processos
        if(cliente.getProcessos() != null && !cliente.getProcessos().isEmpty()){
            throw new RuntimeException("Não é possível deletar cliente com processos vinculados");
        }
        clienteRepository.delete(cliente);
    }

}
